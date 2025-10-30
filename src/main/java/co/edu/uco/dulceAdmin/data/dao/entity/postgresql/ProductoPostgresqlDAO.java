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
import co.edu.uco.dulceAdmin.data.dao.entity.ProductoDAO;
import co.edu.uco.dulceAdmin.entity.ProductoEntity;
import co.edu.uco.dulceAdmin.entity.TipoProductoEntity;

public final class ProductoPostgresqlDAO extends SqlConnection implements ProductoDAO {

    public ProductoPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    private void mapResultSetToProducto(final java.sql.ResultSet resultSet, final ProductoEntity entity) {
        try {
            entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
            entity.setNombre(resultSet.getString("nombre"));
            entity.setPrecio(resultSet.getInt("precio"));

            var tipo = new TipoProductoEntity();
            tipo.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("tipoproducto_id")));
            entity.setTipo(tipo);

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema tratando de mapear los datos del producto.";
            var technicalMessage = "Error SQL al mapear el ResultSet a ProductoEntity.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<ProductoEntity> findAll() {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<ProductoEntity> productos = new ArrayList<>();
        final var sql = new StringBuilder();

        sql.append("SELECT id, nombre, precio, tipoproducto_id FROM producto");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var producto = new ProductoEntity();
                mapResultSetToProducto(resultSet, producto);
                productos.add(producto);
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta de productos.";
            var technicalMessage = "Error SQL al ejecutar findAll en ProductoPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando los productos.";
            var technicalMessage = "Error inesperado en findAll de ProductoPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return productos;
    }

    @Override
    public List<ProductoEntity> findByFilter(final ProductoEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<ProductoEntity> productos = new ArrayList<>();
        final var sql = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        sql.append("SELECT id, nombre, precio, tipoproducto_id FROM producto WHERE 1=1 ");

        final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
            sql.append("AND id = ? ");
            parameters.add(filterEntity.getId());
        }
        if (filterEntity.getNombre() != null && !TextHelper.isEmptyWithTrim(filterEntity.getNombre())) {
            sql.append("AND nombre LIKE ? ");
            parameters.add("%" + filterEntity.getNombre().trim() + "%");
        }
        if (filterEntity.getPrecio() != null && filterEntity.getPrecio() > 0) {
            sql.append("AND precio = ? ");
            parameters.add(filterEntity.getPrecio());
        }
        if (filterEntity.getTipo() != null && filterEntity.getTipo().getId() != null && !defaultUuid.equals(filterEntity.getTipo().getId())) {
            sql.append("AND tipoproducto_id = ? ");
            parameters.add(filterEntity.getTipo().getId());
        }

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var producto = new ProductoEntity();
                    mapResultSetToProducto(resultSet, producto);
                    productos.add(producto);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta filtrada de productos.";
            var technicalMessage = "Error SQL al ejecutar findByFilter en ProductoPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado filtrando los productos.";
            var technicalMessage = "Error inesperado en findByFilter de ProductoPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return productos;
    }

    @Override
    public ProductoEntity findById(final UUID id) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        ProductoEntity producto = null;
        final var sql = new StringBuilder();

        sql.append("SELECT id, nombre, precio, tipoproducto_id FROM producto WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new ProductoEntity();
                    mapResultSetToProducto(resultSet, producto);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta por ID del producto.";
            var technicalMessage = "Error SQL al ejecutar findById en ProductoPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final DulceAdminException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando el producto por ID.";
            var technicalMessage = "Error inesperado en findById de ProductoPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return producto;
    }
}
