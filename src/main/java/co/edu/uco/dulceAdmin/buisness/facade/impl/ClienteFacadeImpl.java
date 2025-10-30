package co.edu.uco.dulceAdmin.buisness.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.buisness.business.impl.ClienteBusinessImpl;
import co.edu.uco.dulceAdmin.buisness.facade.ClienteFacade;
import co.edu.uco.dulceAdmin.data.dao.factory.DAOFactory;
import co.edu.uco.dulceAdmin.dto.ClienteDTO;

public class ClienteFacadeImpl implements ClienteFacade{

	@Override
	public void registarInformacionNuevoCliente(ClienteDTO clienteDto) {
		
		var daoFactory = DAOFactory.getFactory();
		var business = new ClienteBusinessImpl(daoFactory);
		
	}

	@Override
	public void eliminarInformacionCliente(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarInformacionCliente(UUID id, ClienteDTO clienteDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClienteDTO> findAllUsers() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public List<ClienteDTO> findById(UUID id) {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public ClienteDTO encontrarClienteEspecifico(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
