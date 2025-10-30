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
import co.edu.uco.dulceAdmin.data.dao.entity.TipoProductoDAO;
import co.edu.uco.dulceAdmin.entity.TipoProductoEntity;

public final class TipoProductoPostgresqlDAO extends SqlConnection implements TipoProductoDAO {

	public TipoProductoPostgresqlDAO(final Connection connection) {
		super(connection);
	}

	private void mapResultSetToTipoProducto(final java.sql.ResultSet resultSet, final TipoProductoEntity entity) {
		try {
			entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			entity.setNombre(resultSet.getString("nombre"));
			entity.setDescripcion(resultSet.getString("descripcion"));
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema tratando de mapear los datos del tipo de producto.";
			var technicalMessage = "Error SQL al mapear el ResultSet a TipoProductoEntity.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<TipoProductoEntity> findAll() {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<TipoProductoEntity> tiposProducto = new ArrayList<>();
		final var sql = new StringBuilder();
		sql.append("SELECT id, nombre, descripcion FROM tipoproducto");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString());
			 var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var tipoProducto = new TipoProductoEntity();
				mapResultSetToTipoProducto(resultSet, tipoProducto);
				tiposProducto.add(tipoProducto);
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta de tipos de producto.";
			var technicalMessage = "Error SQL al ejecutar findAll en TipoProductoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado consultando los tipos de producto.";
			var technicalMessage = "Error inesperado en findAll de TipoProductoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return tiposProducto;
	}

	@Override
	public List<TipoProductoEntity> findByFilter(final TipoProductoEntity filterEntity) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<TipoProductoEntity> tiposProducto = new ArrayList<>();
		final var sql = new StringBuilder();
		final var parameters = new ArrayList<Object>();

		sql.append("SELECT id, nombre, descripcion FROM tipoproducto WHERE 1=1 ");

		final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();
		if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
			sql.append("AND id = ? ");
			parameters.add(filterEntity.getId());
		}
		if (filterEntity.getNombre() != null && !TextHelper.isEmptyWithTrim(filterEntity.getNombre())) {
			sql.append("AND nombre LIKE ? ");
			parameters.add("%" + filterEntity.getNombre().trim() + "%");
		}
		if (filterEntity.getDescripcion() != null && !TextHelper.isEmptyWithTrim(filterEntity.getDescripcion())) {
			sql.append("AND descripcion LIKE ? ");
			parameters.add("%" + filterEntity.getDescripcion().trim() + "%");
		}

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.size(); i++) {
				preparedStatement.setObject(i + 1, parameters.get(i));
			}
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					var tipoProducto = new TipoProductoEntity();
					mapResultSetToTipoProducto(resultSet, tipoProducto);
					tiposProducto.add(tipoProducto);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta filtrada de tipos de producto.";
			var technicalMessage = "Error SQL al ejecutar findByFilter en TipoProductoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado filtrando los tipos de producto.";
			var technicalMessage = "Error inesperado en findByFilter de TipoProductoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return tiposProducto;
	}

	@Override
	public TipoProductoEntity findById(final UUID id) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		TipoProductoEntity tipoProducto = null;
		final var sql = new StringBuilder();
		sql.append("SELECT id, nombre, descripcion FROM tipoproducto WHERE id = ?");

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			preparedStatement.setObject(1, id);
			try (var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					tipoProducto = new TipoProductoEntity();
					mapResultSetToTipoProducto(resultSet, tipoProducto);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = "Se presentó un problema ejecutando la consulta por ID del tipo de producto.";
			var technicalMessage = "Error SQL al ejecutar findById en TipoProductoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			var userMessage = "Se presentó un problema inesperado consultando el tipo de producto por ID.";
			var technicalMessage = "Error inesperado en findById de TipoProductoPostgresqlDAO.";
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
		return tipoProducto;
	}
}
