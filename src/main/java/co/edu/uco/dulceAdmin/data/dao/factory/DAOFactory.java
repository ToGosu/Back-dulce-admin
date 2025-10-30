package co.edu.uco.dulceAdmin.data.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.dulceAdmin.data.dao.entity.ClienteDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.InventarioDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.MetodoPagoDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.ProductoDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.ProductoReciboDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.ReciboDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.RolTrabajadorDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.TipoProductoDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.TrabajadorDAO;
import co.edu.uco.dulceAdmin.data.dao.factory.DAOFactory;
import co.edu.uco.dulceAdmin.data.dao.factory.postgresql.PostgresqlDAOFactory;
import co.edu.uco.dulceAdmin.crosscuting.helper.SqlConnectionHelper;

public abstract class DAOFactory {
	protected Connection connection;
	protected static FactoryEnum factory = FactoryEnum.POSTGRESQL;

	public static DAOFactory getFactory() {

		if (FactoryEnum.POSTGRESQL.equals(factory)) {
			return new PostgresqlDAOFactory();
		} else {

			var userMessage = MessagesEnum.USER_ERROR_SQL_DATASOURCE_NOT_AVAILABLE.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DATASOURCE_NOT_AVAILABLE.getContent();
			throw DulceAdminException.create(userMessage, technicalMessage);
		}

	}
	
	public abstract ClienteDAO getClienteDAO();
	
	public abstract InventarioDAO getInventarioDAO();
	
	public abstract MetodoPagoDAO getMetodoPagoDAO();
	
	public abstract ProductoDAO getProductoDAO();
	
	public abstract ProductoReciboDAO getProductoReciboDAO();
	
	public abstract ReciboDAO getReciboDAO();
	
	public abstract RolTrabajadorDAO getRolTrabajadorDAO();
	
	public abstract TipoProductoDAO getTipoProductoDAO();
	
	public abstract TrabajadorDAO getTrabajadorDAO();
	
	protected abstract void openConnection();

	
	public final void initTransaction() {

		openConnection();

		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);

		try {
			connection.setAutoCommit(false);

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_INIT_TRANSACTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_INIT_TRANSACTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}
	

	public final void commitTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);

		try {
			connection.commit();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_COMMIT_TRANSACTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_COMMIT_TRANSACTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}

	}
	
	
	public final void rollbackTransaction() {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);

		try {
			connection.rollback();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}
	
	public final void closeConnection() {
		SqlConnectionHelper.ensureConnectionIsOpen(connection);

		try {
			connection.close();
		} catch (final SQLException exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw DulceAdminException.create(exception, userMassage, technicalMessage);

		} catch (final Exception exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw DulceAdminException.create(exception, userMassage, technicalMessage);
		}
	}


}
