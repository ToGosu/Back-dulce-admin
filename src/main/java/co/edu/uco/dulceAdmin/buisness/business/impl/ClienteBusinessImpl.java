package co.edu.uco.dulceAdmin.buisness.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.ClienteEntityAssembler;
import co.edu.uco.dulceAdmin.buisness.business.ClienteBusiness;
import co.edu.uco.dulceAdmin.buisness.domain.ClienteDomain;
import co.edu.uco.dulceAdmin.crosscuting.exception.DulceAdminException;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.data.dao.factory.DAOFactory;
import co.edu.uco.dulceAdmin.entity.ClienteEntity;

public final class ClienteBusinessImpl implements ClienteBusiness {

	private final DAOFactory daoFactory;

	private static final Pattern EMAIL_PATTERN = Pattern.compile(
			"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
	);
	private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+$");

	public ClienteBusinessImpl(final DAOFactory daoFactory) {
		if (daoFactory == null) {
			throw DulceAdminException.create("El DAOFactory no puede ser nulo.");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void registarInformacionNuevoCliente(final ClienteDomain clienteDomain) {

		validateTipoIdentificacionExiste(clienteDomain.getIdentificacion().getId());
		validateDomainRules(clienteDomain);
		validateIdentificacionNoExiste(clienteDomain.getIdentificacion().getId(), clienteDomain.getIdentificacion());
		validateEmailNoExiste(clienteDomain.getCorreo());
		validateCelularNoExiste(clienteDomain.getCelular());

		final UUID nuevoId = generarNuevoIdCliente();

		final ClienteEntity clienteEntity = ClienteEntityAssembler.getInstance().toEntity(clienteDomain);
		clienteEntity.setId(nuevoId);

		daoFactory.getClienteDAO().create(clienteEntity);
	}

	@Override
	public void eliminarInformacionCliente(final UUID id) {
		try {
			if (UUIDHelper.getUUIDHelper().isDefaultUUID(id)) {
				throw DulceAdminException.create("El ID del cliente que desea eliminar no es válido.", "Capa de Negocio");
			}

			ClienteEntity filtro = new ClienteEntity();
			filtro.setId(id);
			List<ClienteEntity> lista = daoFactory.getClienteDAO().findByFilter(filtro);

			if (lista.isEmpty()) {
				throw DulceAdminException.create("El cliente con el ID " + id + " no fue encontrado y no puede ser eliminado.",
						"Capa de Negocio");
			}

			daoFactory.getClienteDAO().delete(id);

		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw DulceAdminException.create(exception, "Error inesperado al eliminar el cliente.", "Capa de Negocio");
		}
	}

	@Override
	public void actualizarInformacionCliente(final UUID id, final ClienteDomain clienteDomain) {

		try {
			clienteDomain.setId(id);
			validateDomainRules(clienteDomain);

			validateIdentificacionNoExisteParaOtroCliente(clienteDomain.getIdentificacion().getId(),
					clienteDomain.getIdentificacion(), id);
			validateEmailNoExisteParaOtroCliente(clienteDomain.getCorreo(), id);
			validateCelularNoExisteParaOtroCliente(clienteDomain.getCelular(), id);

			final ClienteEntity entity = ClienteEntityAssembler.getInstance().toEntity(clienteDomain);
			entity.setId(id);

			daoFactory.getClienteDAO().update(entity);

		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw DulceAdminException.create(exception, "Error inesperado al actualizar la información del cliente.",
					"Capa de Negocio");
		}
	}

	@Override
	public List<ClienteDomain> findAllUsers() {
		try {
			List<ClienteEntity> entityList = daoFactory.getClienteDAO().findAll();
			List<ClienteDomain> domainList = new ArrayList<>();

			for (ClienteEntity entity : entityList) {
				domainList.add(ClienteEntityAssembler.getInstance().toDomain(entity));
			}

			return domainList;

		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw DulceAdminException.create(exception, "Error inesperado al consultar todos los clientes.",
					"Capa de Negocio");
		}
	}

	@Override
	public List<ClienteDomain> findById(final UUID id) {
		try {
			ClienteEntity filtro = new ClienteEntity();
			filtro.setId(id);

			List<ClienteEntity> entityList = daoFactory.getClienteDAO().findByFilter(filtro);
			List<ClienteDomain> domainList = new ArrayList<>();

			for (ClienteEntity entity : entityList) {
				domainList.add(ClienteEntityAssembler.getInstance().toDomain(entity));
			}
			return domainList;

		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw DulceAdminException.create(exception, "Error inesperado al consultar cliente por ID.", "Capa de Negocio");
		}
	}

	@Override
	public ClienteDomain encontrarClienteEspecifico(final UUID id) {
		try {
			ClienteEntity filtro = new ClienteEntity();
			filtro.setId(id);

			List<ClienteEntity> entityList = daoFactory.getClienteDAO().findByFilter(filtro);

			if (entityList.isEmpty()) {
				throw DulceAdminException.create("El cliente con el ID " + id + " no fue encontrado.", "Capa de Negocio");
			}

			return ClienteEntityAssembler.getInstance().toDomain(entityList.get(0));

		} catch (final DulceAdminException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw DulceAdminException.create(exception, "Error inesperado al buscar un cliente específico.", "Capa de Negocio");
		}
	}

	// ============================================================
	// VALIDACIONES PRIVADAS
	// ============================================================

	private void validateTipoIdentificacionExiste(final UUID idTipo) {
		if (idTipo == null || UUIDHelper.getUUIDHelper().isDefaultUUID(idTipo)) {
			throw DulceAdminException.create("El tipo de identificación no puede ser nulo o por defecto.");
		}

		TipoIdentificacionEntity filtro = TipoIdentificacionEntity.createDefault();
		filtro.setId(idTipo);

		var resultados = daoFactory.getTipoIdentificacionDAO().findByFilter(filtro);
		if (resultados.isEmpty()) {
			throw DulceAdminException.create("El tipo de identificación con ID '" + idTipo + "' no existe o no es válido.");
		}
	}

	private void validateDomainRules(final ClienteDomain cliente) {
		if (cliente == null) {
			throw DulceAdminException.create("La información del cliente es obligatoria.");
		}

		if (cliente.getIdentificacion() == null
				|| UUIDHelper.getUUIDHelper().isDefaultUUID(cliente.getIdentificacion().getId())) {
			throw DulceAdminException.create("El tipo de identificación es obligatorio y debe ser válido.");
		}

		if (cliente.getIdentificacion() == null
				|| !NUMERIC_PATTERN.matcher(cliente.getIdentificacion()).matches()
				|| cliente.getIdentificacion().length() > 25) {
			throw DulceAdminException.create("El número de identificación no es válido (solo números, máx 25 caracteres).");
		}

		if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty() || cliente.getNombre().length() > 30) {
			throw DulceAdminException.create("El nombre del cliente es obligatorio y debe tener máximo 30 caracteres.");
		}

		if (cliente.getCorreo() == null || !EMAIL_PATTERN.matcher(cliente.getCorreo()).matches()
				|| cliente.getCorreo().length() > 250) {
			throw DulceAdminException.create("El correo electrónico no es válido (formato o longitud incorrectos).");
		}

		if (cliente.getCelular() == null
				|| !NUMERIC_PATTERN.matcher(cliente.getCelular()).matches()
				|| cliente.getCelular().length() > 20) {
			throw DulceAdminException.create("El número de celular no es válido (solo números, máx 20 caracteres).");
		}
	}

	private void validateIdentificacionNoExiste(final UUID tipoId, final String numero) {
		TipoIdentificacionEntity tipo = TipoIdentificacionEntity.createDefault();
		tipo.setId(tipoId);

		ClienteEntity filtro = new ClienteEntity();
		filtro.setTipoIdentificacion(tipo);
		filtro.setIdentificacion(numero);

		if (!daoFactory.getClienteDAO().findByFilter(filtro).isEmpty()) {
			throw DulceAdminException.create("Ya existe un cliente con la misma identificación.");
		}
	}

	private void validateEmailNoExiste(final String email) {
		ClienteEntity filtro = new ClienteEntity();
		filtro.setCorreo(email);

		if (!daoFactory.getClienteDAO().findByFilter(filtro).isEmpty()) {
			throw DulceAdminException.create("Ya existe un cliente con el mismo correo electrónico.");
		}
	}

	private void validateCelularNoExiste(final String celular) {
		ClienteEntity filtro = new ClienteEntity();
		filtro.setCelular(celular);

		if (!daoFactory.getClienteDAO().findByFilter(filtro).isEmpty()) {
			throw DulceAdminException.create("Ya existe un cliente con el mismo número de celular.");
		}
	}

	private void validateIdentificacionNoExisteParaOtroCliente(final UUID tipoId, final String numero, final UUID idClienteActual) {
		TipoIdentificacionEntity tipo = TipoIdentificacionEntity.createDefault();
		tipo.setId(tipoId);

		ClienteEntity filtro = new ClienteEntity();
		filtro.setTipoIdentificacion(tipo);
		filtro.setIdentificacion(numero);

		List<ClienteEntity> resultados = daoFactory.getClienteDAO().findByFilter(filtro);
		if (!resultados.isEmpty() && !resultados.get(0).getId().equals(idClienteActual)) {
			throw DulceAdminException.create("La identificación ingresada ya pertenece a otro cliente.");
		}
	}

	private void validateEmailNoExisteParaOtroCliente(final String email, final UUID idClienteActual) {
		ClienteEntity filtro = new ClienteEntity();
		filtro.setCorreo(email);

		List<ClienteEntity> resultados = daoFactory.getClienteDAO().findByFilter(filtro);
		if (!resultados.isEmpty() && !resultados.get(0).getId().equals(idClienteActual)) {
			throw DulceAdminException.create("El correo electrónico ingresado ya pertenece a otro cliente.");
		}
	}

	private void validateCelularNoExisteParaOtroCliente(final String celular, final UUID idClienteActual) {
		ClienteEntity filtro = new ClienteEntity();
		filtro.setCelular(celular);

		List<ClienteEntity> resultados = daoFactory.getClienteDAO().findByFilter(filtro);
		if (!resultados.isEmpty() && !resultados.get(0).getId().equals(idClienteActual)) {
			throw DulceAdminException.create("El número de celular ingresado ya pertenece a otro cliente.");
		}
	}

	private UUID generarNuevoIdCliente() {
		UUID nuevoId;
		boolean existe;

		do {
			nuevoId = UUIDHelper.getUUIDHelper().getDefault();
			ClienteEntity filtro = new ClienteEntity();
			filtro.setId(nuevoId);
			existe = !daoFactory.getClienteDAO().findByFilter(filtro).isEmpty();
		} while (existe);

		return nuevoId;
	}
}
