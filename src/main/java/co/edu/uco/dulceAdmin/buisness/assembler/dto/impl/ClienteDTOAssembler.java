package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ClienteDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.ClienteDTO;

public class ClienteDTOAssembler implements DTOAssembler<ClienteDTO, ClienteDomain>{

	private static final DTOAssembler<ClienteDTO, ClienteDomain> instance = new ClienteDTOAssembler();
	
	private ClienteDTOAssembler() {
		super();
	}
	
	public static DTOAssembler<ClienteDTO, ClienteDomain> getClienteDTOAssembler(){
		return instance;
	}
	
	
	@Override
	public ClienteDTO toDTO(final ClienteDomain domain) {
		
		var domainImp= ObjectHelper.getDefault(domain, new ClienteDomain());
		
		return new ClienteDTO(domainImp.getId(),
				domainImp.getNombre(),
				domainImp.getApellido(),
				domainImp.getIdentificacion(),
				domainImp.getCelular(),
				domainImp.getFechaNacimiento(),
				domainImp.getCorreo());

	}

	@Override
	public ClienteDomain toDomain(final ClienteDTO dto) {
		var dtoImp = ObjectHelper.getDefault(dto, new ClienteDTO());
		
		return new ClienteDomain(dtoImp.getId(),
				dtoImp.getNombre(),
				dtoImp.getApellido(),
				dtoImp.getIdentificacion(),
				dtoImp.getCelular(),
				dtoImp.getFechaNacimiento(),
				dtoImp.getCorreo());
	}

	@Override
	public List<ClienteDTO> toDTO(final List<ClienteDomain> domainList) {
		return List.of();
	}

}
