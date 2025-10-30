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
import co.edu.uco.dulceAdmin.data.dao.entity.MetodoPagoDAO;
import co.edu.uco.dulceAdmin.entity.MetodoPagoEntity;

public class MetodoPagoPostgresqlDAO extends SqlConnection implements MetodoPagoDAO{

	public MetodoPagoPostgresqlDAO(final Connection connection) {
		super(connection);
	}

	private void mapResultSetToMetodoPago(final java.sql.ResultSet resultSet, final MetodoPagoEntity entity) {
		try {
			entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			entity.setNombre(resultSet.getString("nombre"));
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema tratando de mapear los datos del método de pago.";
			var technicalMessage = "Error SQL al mapear el ResultSet a MetodoPagoEntity.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<MetodoPagoEntity> findAll() {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<MetodoPagoEntity> metodosPago = new ArrayList<>();
		final var sql = new StringBuilder();
		sql.append("SELECT id, nombre FROM metodopago");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString());
			 var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var metodo = new MetodoPagoEntity();
				mapResultSetToMetodoPago(resultSet, metodo);
				metodosPago.add(metodo);
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta de métodos de pago.";
			var technicalMessage = "Error SQL al ejecutar findAll en MetodoPagoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado consultando los métodos de pago.";
			var technicalMessage = "Error inesperado en findAll de MetodoPagoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return metodosPago;
	}

	@Override
	public List<MetodoPagoEntity> findByFilter(final MetodoPagoEntity filterEntity) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<MetodoPagoEntity> metodosPago = new ArrayList<>();
		final var sql = new StringBuilder();
		final var parameters = new ArrayList<Object>();

		sql.append("SELECT id, nombre FROM metodopago WHERE 1=1 ");

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
					var metodo = new MetodoPagoEntity();
					mapResultSetToMetodoPago(resultSet, metodo);
					metodosPago.add(metodo);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta filtrada de métodos de pago.";
			var technicalMessage = "Error SQL al ejecutar findByFilter en MetodoPagoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado filtrando los métodos de pago.";
			var technicalMessage = "Error inesperado en findByFilter de MetodoPagoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return metodosPago;
	}

	@Override
	public MetodoPagoEntity findById(final UUID id) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		MetodoPagoEntity metodo = null;
		final var sql = new StringBuilder();
		sql.append("SELECT id, nombre FROM metodopago WHERE id = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			preparedStatement.setObject(1, id);
			try (var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					metodo = new MetodoPagoEntity();
					mapResultSetToMetodoPago(resultSet, metodo);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta por ID del método de pago.";
			var technicalMessage = "Error SQL al ejecutar findById en MetodoPagoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado consultando el método de pago por ID.";
			var technicalMessage = "Error inesperado en findById de MetodoPagoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return metodo;
	}
}

