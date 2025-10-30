package co.edu.uco.dulceAdmin.data.dao.entity.postgresql;

import java.sql.Connection;
import co.edu.uco.dulceAdmin.crosscuting.helper.SqlConnectionHelper;

public abstract class SqlConnection {
	
	private Connection connection;
	
	protected SqlConnection(final Connection connection){
		setConnection(connection);
	}
	
	protected Connection getConnection() {
		return connection;
	}

	public void setConnection(final Connection connection) {
		SqlConnectionHelper.ensureConnectionIsOpen(connection);
		
		this.connection = connection;
	}
	
	
}
