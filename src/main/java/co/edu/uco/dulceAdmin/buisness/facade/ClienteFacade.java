package co.edu.uco.dulceAdmin.buisness.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.dto.ClienteDTO;

public interface ClienteFacade {
	
	void registarInformacionNuevoCliente (ClienteDTO clienteDto);
	void eliminarInformacionCliente (UUID id);
	void actualizarInformacionCliente(UUID id, ClienteDTO clienteDto);
	List<ClienteDTO> findAllUsers();
	List<ClienteDTO> findById(UUID id);
	ClienteDTO encontrarClienteEspecifico (UUID id);
	
}
