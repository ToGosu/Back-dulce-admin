package co.edu.uco.dulceAdmin.data.dao.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.messagescatalog.MessagesEnum;

public final class PostgresqlConnection extends SqlConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/dulce-admin";
    private static final String USER = "postgres";
    private static final String PASSWORD = "C2wvVCP18#6@";

    private PostgresqlConnection(Connection connection) {
        super(connection);
    }

    public static PostgresqlConnection open() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            return new PostgresqlConnection(connection);
        } catch (SQLException e) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_FAILED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_FAILED.getContent();
            throw DulceAdminException.create(userMessage, technicalMessage, e);
        }
    }

    public void close() {
        try {
            if (getConnection() != null && !getConnection().isClosed()) {
                getConnection().close();
            }
        } catch (SQLException e) {
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_CLOSING_FAILED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_CLOSING_FAILED.getContent();
            throw DulceAdminException.create(userMessage, technicalMessage, e);
        }
    }
}

