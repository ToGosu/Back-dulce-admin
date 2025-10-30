package co.edu.uco.dulceAdmin.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.helper.DateHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.data.dao.entity.InventarioDAO;
import co.edu.uco.dulceAdmin.entity.InventarioEntity;
import co.edu.uco.dulceAdmin.entity.ProductoEntity;

public final class InventarioPostgresqlDAO extends SqlConnection implements InventarioDAO {

    public InventarioPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    private void mapResultSetToInventario(final java.sql.ResultSet resultSet, final InventarioEntity entity) {
        try {
            entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
            entity.setStock(resultSet.getInt("stock"));
            entity.setEsPerecedero(resultSet.getBoolean("esperecedero"));
            entity.setFechaCreacion(resultSet.getDate("fechacreacion"));
            entity.setFechaVencimiento(resultSet.getDate("fechavencimiento"));

            var producto = new ProductoEntity();
            producto.setPrecio(resultSet.getInt("producto_precio"));
            producto.setNombre(resultSet.getString("producto_nombre"));
            entity.setProducto(producto);

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema tratando de mapear los datos del inventario.";
            var technicalMessage = "Error SQL al mapear el ResultSet a InventarioEntity.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<InventarioEntity> findAll() {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<InventarioEntity> inventarios = new ArrayList<>();
        final var sql = new StringBuilder();

        sql.append("SELECT id, esperecedero, fechacreacion, fechavencimiento, stock, producto_precio, producto_nombre FROM inventario");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var inventario = new InventarioEntity();
                mapResultSetToInventario(resultSet, inventario);
                inventarios.add(inventario);
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta de inventarios.";
            var technicalMessage = "Error SQL al ejecutar findAll en InventarioPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando los inventarios.";
            var technicalMessage = "Error inesperado en findAll de InventarioPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return inventarios;
    }

    @Override
    public List<InventarioEntity> findByFilter(final InventarioEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<InventarioEntity> inventarios = new ArrayList<>();
        final var sql = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        sql.append("SELECT id, esperecedero, fechacreacion, fechavencimiento, stock, producto_precio, producto_nombre FROM inventario WHERE 1=1 ");

        final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
            sql.append("AND id = ? ");
            parameters.add(filterEntity.getId());
        }
        if (filterEntity.getStock() != null && filterEntity.getStock() > 0) {
            sql.append("AND stock = ? ");
            parameters.add(filterEntity.getStock());
        }
        if (filterEntity.getEsPerecedero() != null) {
            sql.append("AND esperecedero = ? ");
            parameters.add(filterEntity.getEsPerecedero());
        }
        if (filterEntity.getFechaCreacion() != null) {
            sql.append("AND fechacreacion = ? ");
            parameters.add(DateHelper.convertToSqlDate(filterEntity.getFechaCreacion()));
        }
        if (filterEntity.getFechaVencimiento() != null) {
            sql.append("AND fechavencimiento = ? ");
            parameters.add(DateHelper.convertToSqlDate(filterEntity.getFechaVencimiento()));
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

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var inventario = new InventarioEntity();
                    mapResultSetToInventario(resultSet, inventario);
                    inventarios.add(inventario);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta filtrada de inventarios.";
            var technicalMessage = "Error SQL al ejecutar findByFilter en InventarioPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado filtrando los inventarios.";
            var technicalMessage = "Error inesperado en findByFilter de InventarioPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return inventarios;
    }

    @Override
    public InventarioEntity findById(final UUID id) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        InventarioEntity inventario = null;
        final var sql = new StringBuilder();

        sql.append("SELECT id, esperecedero, fechacreacion, fechavencimiento, stock, producto_precio, producto_nombre FROM inventario WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    inventario = new InventarioEntity();
                    mapResultSetToInventario(resultSet, inventario);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta por ID del inventario.";
            var technicalMessage = "Error SQL al ejecutar findById en InventarioPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final DulceAdminException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando el inventario por ID.";
            var technicalMessage = "Error inesperado en findById de InventarioPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return inventario;
    }
}
