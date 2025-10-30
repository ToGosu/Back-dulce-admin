package co.edu.uco.dulceAdmin.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.data.dao.entity.ProductoReciboDAO;
import co.edu.uco.dulceAdmin.entity.ProductoEntity;
import co.edu.uco.dulceAdmin.entity.ProductoReciboEntity;
import co.edu.uco.dulceAdmin.entity.ReciboEntity;

public final class ProductoReciboPostgresqlDAO extends SqlConnection implements ProductoReciboDAO {

    public ProductoReciboPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    private void mapResultSetToProductoRecibo(final java.sql.ResultSet resultSet, final ProductoReciboEntity entity) {
        try {
            entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
            entity.setCantidad(resultSet.getInt("cantidad"));

            var producto = new ProductoEntity();
            producto.setNombre(resultSet.getString("producto_nombre"));
            producto.setPrecio(resultSet.getInt("producto_precio"));
            entity.setProducto(producto);

            var recibo = new ReciboEntity();
            recibo.setCodigo(resultSet.getInt("recibo_codigo"));
            entity.setRecibo(recibo);

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema tratando de mapear los datos del producto en el recibo.";
            var technicalMessage = "Error SQL al mapear el ResultSet a ProductoReciboEntity.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<ProductoReciboEntity> findAll() {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<ProductoReciboEntity> productosRecibo = new ArrayList<>();
        final var sql = new StringBuilder();

        sql.append("SELECT id, cantidad, totalproducto, recibo_codigo, producto_precio, producto_nombre FROM reciboproducto");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var productoRecibo = new ProductoReciboEntity();
                mapResultSetToProductoRecibo(resultSet, productoRecibo);
                productosRecibo.add(productoRecibo);
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta de productos en recibos.";
            var technicalMessage = "Error SQL al ejecutar findAll en ProductoReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando los productos en recibos.";
            var technicalMessage = "Error inesperado en findAll de ProductoReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return productosRecibo;
    }

    @Override
    public List<ProductoReciboEntity> findByFilter(final ProductoReciboEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<ProductoReciboEntity> productosRecibo = new ArrayList<>();
        final var sql = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        sql.append("SELECT id, cantidad, totalproducto, recibo_codigo, producto_precio, producto_nombre FROM reciboproducto WHERE 1=1 ");

        final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
            sql.append("AND id = ? ");
            parameters.add(filterEntity.getId());
        }
        if (filterEntity.getCantidad() > 0) {
            sql.append("AND cantidad = ? ");
            parameters.add(filterEntity.getCantidad());
        }
        if (filterEntity.getProducto() != null) {
            if (!TextHelper.isEmptyWithTrim(filterEntity.getProducto().getNombre())) {
                sql.append("AND producto_nombre LIKE ? ");
                parameters.add("%" + filterEntity.getProducto().getNombre().trim() + "%");
            }
            if (filterEntity.getProducto().getPrecio() != null && filterEntity.getProducto().getPrecio() > 0) {
                sql.append("AND producto_precio = ? ");
                parameters.add(filterEntity.getProducto().getPrecio());
            }
        }
        if (filterEntity.getRecibo() != null && filterEntity.getRecibo().getCodigo() != null) {
            sql.append("AND recibo_codigo = ? ");
            parameters.add(filterEntity.getRecibo().getCodigo());
        }

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var productoRecibo = new ProductoReciboEntity();
                    mapResultSetToProductoRecibo(resultSet, productoRecibo);
                    productosRecibo.add(productoRecibo);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta filtrada de productos en recibos.";
            var technicalMessage = "Error SQL al ejecutar findByFilter en ProductoReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado filtrando los productos en recibos.";
            var technicalMessage = "Error inesperado en findByFilter de ProductoReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return productosRecibo;
    }

    @Override
    public ProductoReciboEntity findById(final UUID id) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        ProductoReciboEntity productoRecibo = null;
        final var sql = new StringBuilder();

        sql.append("SELECT id, cantidad, totalproducto, recibo_codigo, producto_precio, producto_nombre FROM reciboproducto WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productoRecibo = new ProductoReciboEntity();
                    mapResultSetToProductoRecibo(resultSet, productoRecibo);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta por ID de producto en recibo.";
            var technicalMessage = "Error SQL al ejecutar findById en ProductoReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final DulceAdminException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando el producto en recibo por ID.";
            var technicalMessage = "Error inesperado en findById de ProductoReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return productoRecibo;
    }
}
