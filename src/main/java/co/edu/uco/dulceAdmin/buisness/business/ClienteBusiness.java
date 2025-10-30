package co.edu.uco.dulceAdmin.buisness.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.buisness.domain.ClienteDomain;

public interface ClienteBusiness {

	void registarInformacionNuevoCliente (ClienteDomain clienteDomain);
	void eliminarInformacionCliente (UUID id);
	void actualizarInformacionCliente(UUID id, ClienteDomain clienteDomain);
	List<ClienteDomain> findAllUsers();
	List<ClienteDomain> findById(UUID id);
	ClienteDomain encontrarClienteEspecifico (UUID id);
}
