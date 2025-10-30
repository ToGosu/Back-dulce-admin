package co.edu.uco.dulceAdmin.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.helper.DateHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.dulceAdmin.data.dao.entity.ClienteDAO;
import co.edu.uco.dulceAdmin.entity.ClienteEntity;

public class ClientePostgresqlDAO extends SqlConnection implements ClienteDAO{

	public ClientePostgresqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(final ClienteEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("INSERT INTO cliente (");
		sql.append("id, nombre, identificacion, dianacimiento, numero, correo) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setString(2, entity.getNombre());
			preparedStatement.setString(3, entity.getIdentificacion());
			preparedStatement.setDate(4, DateHelper.convertToSqlDate(entity.getFechaNacimiento()));
			preparedStatement.setString(5, entity.getCelular());
			preparedStatement.setString(6, entity.getCorreo());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_INSERT_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_USER.getContent() + exception.getMessage();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public void delete(final UUID id) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = "DELETE FROM cliente WHERE id = ?";

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_DELETE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_USER.getContent() + exception.getMessage();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<ClienteEntity> findByFilter(ClienteEntity filterEntity) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		var parameterList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parameterList);

		try (var preparedStatement = this.getConnection().prepareStatement(sql)) {

			for (int i = 0; i < parameterList.size(); i++) {
				preparedStatement.setObject(i + 1, parameterList.get(i));
			}

			return executeSentenceFindByFilter(preparedStatement);
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent() + exception.getMessage();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<ClienteEntity> findAll() {
		return findByFilter(new ClienteEntity());
	}

	@Override
	public ClienteEntity findById(final UUID id) {
		return findByFilter(new ClienteEntity(id)).stream().findFirst().orElse(new ClienteEntity());
	}

	@Override
	public void update(final ClienteEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

		final var sql = new StringBuilder();
		sql.append("UPDATE cliente SET ");
		sql.append("nombre = ?, ");
		sql.append("identificacion = ?, ");
		sql.append("dianacimiento = ?, ");
		sql.append("numero = ?, ");
		sql.append("correo = ? ");
		sql.append("WHERE id = ?");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			preparedStatement.setString(1, entity.getNombre());
			preparedStatement.setString(2, entity.getIdentificacion());
			preparedStatement.setDate(3, DateHelper.convertToSqlDate(entity.getFechaNacimiento()));
			preparedStatement.setString(4, entity.getCelular());
			preparedStatement.setString(5, entity.getCorreo());
			preparedStatement.setObject(6, entity.getId());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UPDATE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_USER.getContent() + exception.getMessage();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}
	
	private String createSentenceFindByFilter(final ClienteEntity filterEntity, final List<Object> parameterList) {
		final var sql = new StringBuilder();

		sql.append("SELECT id, nombre, identificacion, dianacimiento, numero, correo FROM cliente");

		createWhereClauseFindByFilter(sql, parameterList, filterEntity);
		return sql.toString();
	}
	
	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList, final ClienteEntity filterEntity) {
		var filter = ObjectHelper.getDefault(filterEntity, new ClienteEntity());
		var conditions = new ArrayList<String>();

		addCondition(conditions, parameterList, !UUIDHelper.getUUIDHelper().isDefaultUUID(filter.getId()), "id = ?", filter.getId());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getNombre()), "nombre = ?", filter.getNombre());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getIdentificacion()), "identificacion = ?", filter.getIdentificacion());
		addCondition(conditions, parameterList, filter.getFechaNacimiento() != null, "dianacimiento = ?", DateHelper.convertToSqlDate(filter.getFechaNacimiento()));
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getCelular()), "numero = ?", filter.getCelular());
		addCondition(conditions, parameterList, !TextHelper.isEmptyWithTrim(filter.getCorreo()), "correo = ?", filter.getCorreo());

		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	}

	private void addCondition(final List<String> conditions, final List<Object> params, final boolean condition, final String clause, final Object value) {
		if (condition) {
			conditions.add(clause);
			params.add(value);
		}
	}

	private List<ClienteEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		var listCliente = new ArrayList<ClienteEntity>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				var cliente = new ClienteEntity();
				cliente.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setIdentificacion(resultSet.getString("identificacion"));
				cliente.setFechaNacimiento(resultSet.getDate("dianacimiento"));
				cliente.setCelular(resultSet.getString("numero"));
				cliente.setCorreo(resultSet.getString("correo"));

				listCliente.add(cliente);
			}
		} catch (SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_USER.getContent() + exception.getMessage();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return listCliente;
	}


}
