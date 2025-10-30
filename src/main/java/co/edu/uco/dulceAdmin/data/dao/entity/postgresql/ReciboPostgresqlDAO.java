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
import co.edu.uco.dulceAdmin.data.dao.entity.ReciboDAO;
import co.edu.uco.dulceAdmin.entity.ClienteEntity;
import co.edu.uco.dulceAdmin.entity.MetodoPagoEntity;
import co.edu.uco.dulceAdmin.entity.ReciboEntity;
import co.edu.uco.dulceAdmin.entity.TrabajadorEntity;

public final class ReciboPostgresqlDAO extends SqlConnection implements ReciboDAO {

    public ReciboPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    private void mapResultSetToRecibo(final java.sql.ResultSet resultSet, final ReciboEntity entity) {
        try {
            entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
            entity.setCodigo(resultSet.getInt("codigo"));

            var metodoPago = new MetodoPagoEntity();
            metodoPago.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("metodopago_id")));
            entity.setMetodoPago(metodoPago);

            var trabajador = new TrabajadorEntity();
            trabajador.setIdentificacion(resultSet.getString("trabajador_identificacion"));
            entity.setTrabajador(trabajador);

            var cliente = new ClienteEntity();
            cliente.setIdentificacion(resultSet.getString("cliente_identificacion"));
            entity.setCliente(cliente);

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema tratando de mapear los datos del recibo.";
            var technicalMessage = "Error SQL al mapear el ResultSet a ReciboEntity.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<ReciboEntity> findAll() {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<ReciboEntity> recibos = new ArrayList<>();
        final var sql = new StringBuilder();

        sql.append("SELECT id, codigo, total, metodopago_id, trabajador_identificacion, cliente_identificacion FROM recibo");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString());
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var recibo = new ReciboEntity();
                mapResultSetToRecibo(resultSet, recibo);
                recibos.add(recibo);
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta de recibos.";
            var technicalMessage = "Error SQL al ejecutar findAll en ReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando los recibos.";
            var technicalMessage = "Error inesperado en findAll de ReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return recibos;
    }

    @Override
    public List<ReciboEntity> findByFilter(final ReciboEntity filterEntity) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        final List<ReciboEntity> recibos = new ArrayList<>();
        final var sql = new StringBuilder();
        final var parameters = new ArrayList<Object>();

        sql.append("SELECT id, codigo, total, metodopago_id, trabajador_identificacion, cliente_identificacion FROM recibo WHERE 1=1 ");

        final UUID defaultUuid = UUIDHelper.getUUIDHelper().getDefault();

        if (filterEntity.getId() != null && !defaultUuid.equals(filterEntity.getId())) {
            sql.append("AND id = ? ");
            parameters.add(filterEntity.getId());
        }
        if (filterEntity.getCodigo() != null && filterEntity.getCodigo() > 0) {
            sql.append("AND codigo = ? ");
            parameters.add(filterEntity.getCodigo());
        }
        if (filterEntity.getMetodoPago() != null && filterEntity.getMetodoPago().getId() != null && !defaultUuid.equals(filterEntity.getMetodoPago().getId())) {
            sql.append("AND metodopago_id = ? ");
            parameters.add(filterEntity.getMetodoPago().getId());
        }
        if (filterEntity.getTrabajador() != null && !TextHelper.isEmptyWithTrim(filterEntity.getTrabajador().getIdentificacion())) {
            sql.append("AND trabajador_identificacion = ? ");
            parameters.add(filterEntity.getTrabajador().getIdentificacion());
        }
        if (filterEntity.getCliente() != null && !TextHelper.isEmptyWithTrim(filterEntity.getCliente().getIdentificacion())) {
            sql.append("AND cliente_identificacion = ? ");
            parameters.add(filterEntity.getCliente().getIdentificacion());
        }

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var recibo = new ReciboEntity();
                    mapResultSetToRecibo(resultSet, recibo);
                    recibos.add(recibo);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta filtrada de recibos.";
            var technicalMessage = "Error SQL al ejecutar findByFilter en ReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado filtrando los recibos.";
            var technicalMessage = "Error inesperado en findByFilter de ReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return recibos;
    }

    @Override
    public ReciboEntity findById(final UUID id) {
        SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
        ReciboEntity recibo = null;
        final var sql = new StringBuilder();

        sql.append("SELECT id, codigo, total, metodopago_id, trabajador_identificacion, cliente_identificacion FROM recibo WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    recibo = new ReciboEntity();
                    mapResultSetToRecibo(resultSet, recibo);
                }
            }

        } catch (final SQLException exception) {
            var userMessage = "Se presentó un problema ejecutando la consulta por ID del recibo.";
            var technicalMessage = "Error SQL al ejecutar findById en ReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        } catch (final DulceAdminException exception) {
            throw exception;
        } catch (final Exception exception) {
            var userMessage = "Se presentó un problema inesperado consultando el recibo por ID.";
            var technicalMessage = "Error inesperado en findById de ReciboPostgresqlDAO.";
            throw DulceAdminException.create(exception, userMessage, technicalMessage);
        }
        return recibo;
    }
}
