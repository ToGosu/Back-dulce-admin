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
import co.edu.uco.dulceAdmin.data.dao.entity.TrabajadorDAO;
import co.edu.uco.dulceAdmin.entity.RolTrabajadorEntity;
import co.edu.uco.dulceAdmin.entity.TrabajadorEntity;

public final class TrabajadorPostgresqlDAO extends SqlConnection implements TrabajadorDAO {

    public TrabajadorPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    private void mapResultSetToTrabajador(final java.sql.ResultSet resultSet, final TrabajadorEntity entity) {
        try {
            entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
            entity.setNombre(resultSet.getString("nombre"));
            entity.setApellido(resultSet.getString("apellido"));
            entity.setIdentificacion(resultSet.getString("identificacion"));
            entity.setNumeroCelular(resultSet.getString("numero"));
            entity.setContraseñaInicioSesion(resultSet.getString("contraseña"));

            var rol = new RolTrabajadorEntity();
            rol.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("roltrabajador_id")));
            entity.setRol(rol);

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema tratando de mapear los datos del trabajador.";
            var technicalMessage = "Error SQL al mapear el ResultSet a TrabajadorEntity.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<TrabajadorEntity> findAll() {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<TrabajadorEntity> trabajadores = new ArrayList<>();
        final var sql = new StringBuilder();

        sql.append("SELECT id, nombre, apellido, identificacion, numero, contraseña, roltrabajador_id FROM trabajador");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var trabajador = new TrabajadorEntity();
                mapResultSetToTrabajador(resultSet, trabajador);
                trabajadores.add(trabajador);
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta de trabajadores.";
            var technicalMessage = "Error SQL al ejecutar findAll en TrabajadorPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando los trabajadores.";
            var technicalMessage = "Error inesperado en findAll de TrabajadorPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return trabajadores;
    }

    @Override
    public List<TrabajadorEntity> findByFilter(final TrabajadorEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<TrabajadorEntity> trabajadores = new ArrayList<>();
        final var sql = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        sql.append("SELECT id, nombre, apellido, identificacion, numero, contraseña, roltrabajador_id FROM trabajador WHERE 1=1 ");

        final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
            sql.append("AND id = ? ");
            parameters.add(filterEntity.getId());
        }
        if (filterEntity.getNombre() != null && !TextHelper.isEmptyWithTrim(filterEntity.getNombre())) {
            sql.append("AND nombre LIKE ? ");
            parameters.add("%" + filterEntity.getNombre().trim() + "%");
        }
        if (filterEntity.getApellido() != null && !TextHelper.isEmptyWithTrim(filterEntity.getApellido())) {
            sql.append("AND apellido LIKE ? ");
            parameters.add("%" + filterEntity.getApellido().trim() + "%");
        }
        if (filterEntity.getIdentificacion() != null && !TextHelper.isEmptyWithTrim(filterEntity.getIdentificacion())) {
            sql.append("AND identificacion LIKE ? ");
            parameters.add("%" + filterEntity.getIdentificacion().trim() + "%");
        }
        if (filterEntity.getNumeroCelular() != null && !TextHelper.isEmptyWithTrim(filterEntity.getNumeroCelular())) {
            sql.append("AND numero LIKE ? ");
            parameters.add("%" + filterEntity.getNumeroCelular().trim() + "%");
        }
        if (filterEntity.getRol() != null && filterEntity.getRol().getId() != null && !defaultUuid.equals(filterEntity.getRol().getId())) {
            sql.append("AND roltrabajador_id = ? ");
            parameters.add(filterEntity.getRol().getId());
        }

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var trabajador = new TrabajadorEntity();
                    mapResultSetToTrabajador(resultSet, trabajador);
                    trabajadores.add(trabajador);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta filtrada de trabajadores.";
            var technicalMessage = "Error SQL al ejecutar findByFilter en TrabajadorPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado filtrando los trabajadores.";
            var technicalMessage = "Error inesperado en findByFilter de TrabajadorPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return trabajadores;
    }

    @Override
    public TrabajadorEntity findById(final UUID id) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        TrabajadorEntity trabajador = null;
        final var sql = new StringBuilder();

        sql.append("SELECT id, nombre, apellido, identificacion, numero, contraseña, roltrabajador_id FROM trabajador WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    trabajador = new TrabajadorEntity();
                    mapResultSetToTrabajador(resultSet, trabajador);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta por ID del trabajador.";
            var technicalMessage = "Error SQL al ejecutar findById en TrabajadorPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final DulceAdminException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando el trabajador por ID.";
            var technicalMessage = "Error inesperado en findById de TrabajadorPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return trabajador;
    }
}
