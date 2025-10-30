package co.edu.uco.dulceAdmin.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.data.dao.entity.RolTrabajadorDAO;
import co.edu.uco.dulceAdmin.entity.RolTrabajadorEntity;

public final class RolTrabajadorPostgresqlDAO extends SqlConnection implements RolTrabajadorDAO {

	public RolTrabajadorPostgresqlDAO(final Connection connection) {
		super(connection);
	}

	private void mapResultSetToRolTrabajador(final java.sql.ResultSet resultSet, final RolTrabajadorEntity entity) {
		try {
			entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			entity.setNombre(resultSet.getString("nombre"));
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema tratando de mapear los datos del rol de trabajador.";
			var technicalMessage = "Error SQL al mapear el ResultSet a RolTrabajadorEntity.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<RolTrabajadorEntity> findAll() {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<RolTrabajadorEntity> roles = new ArrayList<>();
		final var sql = new StringBuilder();
		sql.append("SELECT id, nombre FROM roltrabajador");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString());
			 var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var rol = new RolTrabajadorEntity();
				mapResultSetToRolTrabajador(resultSet, rol);
				roles.add(rol);
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta de roles de trabajador.";
			var technicalMessage = "Error SQL al ejecutar findAll en RolTrabajadorPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado consultando los roles de trabajador.";
			var technicalMessage = "Error inesperado en findAll de RolTrabajadorPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return roles;
	}

	@Override
	public List<RolTrabajadorEntity> findByFilter(final RolTrabajadorEntity filterEntity) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<RolTrabajadorEntity> roles = new ArrayList<>();
		final var sql = new StringBuilder();
		final var parameters = new ArrayList<Object>();

		sql.append("SELECT id, nombre FROM roltrabajador WHERE 1=1 ");

		final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();
		if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
			sql.append("AND id = ? ");
			parameters.add(filterEntity.getId());
		}
		if (filterEntity.getNombre() != null && !TextHelper.isEmptyWithTrim(filterEntity.getNombre())) {
			sql.append("AND nombre LIKE ? ");
			parameters.add("%" + filterEntity.getNombre().trim() + "%");
		}

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.size(); i++) {
				preparedStatement.setObject(i + 1, parameters.get(i));
			}
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					var rol = new RolTrabajadorEntity();
					mapResultSetToRolTrabajador(resultSet, rol);
					roles.add(rol);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta filtrada de roles de trabajador.";
			var technicalMessage = "Error SQL al ejecutar findByFilter en RolTrabajadorPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado filtrando los roles de trabajador.";
			var technicalMessage = "Error inesperado en findByFilter de RolTrabajadorPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return roles;
	}

	@Override
	public RolTrabajadorEntity findById(final UUID id) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		RolTrabajadorEntity rol = null;
		final var sql = new StringBuilder();
		sql.append("SELECT id, nombre FROM roltrabajador WHERE id = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			preparedStatement.setObject(1, id);
			try (var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					rol = new RolTrabajadorEntity();
					mapResultSetToRolTrabajador(resultSet, rol);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta por ID del rol de trabajador.";
			var technicalMessage = "Error SQL al ejecutar findById en RolTrabajadorPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado consultando el rol de trabajador por ID.";
			var technicalMessage = "Error inesperado en findById de RolTrabajadorPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return rol;
	}
}
