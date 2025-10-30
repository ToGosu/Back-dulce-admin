package co.edu.uco.dulceAdmin.data.dao.factory.postgresql;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.data.dao.entity.ClienteDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.InventarioDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.MetodoPagoDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.ProductoDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.ProductoReciboDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.ReciboDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.RolTrabajadorDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.TipoProductoDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.TrabajadorDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.ClientePostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.InventarioPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.MetodoPagoPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.ProductoPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.ProductoReciboPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.ReciboPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.RolTrabajadorPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.TipoProductoPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.entity.postgresql.TrabajadorPostgresqlDAO;
import co.edu.uco.dulceAdmin.data.dao.factory.DAOFactory;
import co.edu.uco.dulceAdmin.crosscuting.messagescatalog.MessagesEnum;

public final class PostgresqlDAOFactory extends DAOFactory{

	public PostgresqlDAOFactory() {
		openConnection();
	}
	
	@Override
	protected void openConnection() {
		String url = "jdbc:postgresql://localhost:5432/dulce-admin";
		String user = "postgres";
		String password = "C2wvVCP18#6@";
		
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		} catch (Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
			throw DulceAdminException.create(exception, userMessage, technicalMessage);
		}
	}


	@Override
	public TrabajadorDAO getTrabajadorDAO() {
		// TODO Auto-generated method stub
		return new TrabajadorPostgresqlDAO(connection);
	}

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClientePostgresqlDAO(connection);
	}

	@Override
	public InventarioDAO getInventarioDAO() {
		// TODO Auto-generated method stub
		return new InventarioPostgresqlDAO(connection);
	}

	@Override
	public MetodoPagoDAO getMetodoPagoDAO() {
		// TODO Auto-generated method stub
		return new MetodoPagoPostgresqlDAO(connection);
	}

	@Override
	public ProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return new ProductoPostgresqlDAO(connection);
	}

	@Override
	public ProductoReciboDAO getProductoReciboDAO() {
		// TODO Auto-generated method stub
		return new ProductoReciboPostgresqlDAO(connection);
	}

	@Override
	public ReciboDAO getReciboDAO() {
		// TODO Auto-generated method stub
		return new ReciboPostgresqlDAO(connection);
	}

	@Override
	public RolTrabajadorDAO getRolTrabajadorDAO() {
		// TODO Auto-generated method stub
		return new RolTrabajadorPostgresqlDAO(connection);
	}

	@Override
	public TipoProductoDAO getTipoProductoDAO() {
		// TODO Auto-generated method stub
		return new TipoProductoPostgresqlDAO(connection);
	}


	
	
}
