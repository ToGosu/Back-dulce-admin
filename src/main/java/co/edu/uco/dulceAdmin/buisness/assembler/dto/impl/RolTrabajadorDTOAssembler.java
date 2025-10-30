package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.RolTrabajadorDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.RolTrabajadorDTO;

public class RolTrabajadorDTOAssembler implements DTOAssembler<RolTrabajadorDTO, RolTrabajadorDomain>{
	
	private static final DTOAssembler<RolTrabajadorDTO, RolTrabajadorDomain> instance = new RolTrabajadorDTOAssembler();
	
	private RolTrabajadorDTOAssembler() {
		super();
	}
	
	public static DTOAssembler<RolTrabajadorDTO, RolTrabajadorDomain> getRolTrabajadorDTOAssembler(){
		return instance;
	}
	
	
	
	@Override
	public RolTrabajadorDTO toDTO(final RolTrabajadorDomain domain) {
		var domainImp= ObjectHelper.getDefault(domain, new RolTrabajadorDomain());
		return new RolTrabajadorDTO(domainImp.getId(),domainImp.getNombre());
	}

	@Override
	public RolTrabajadorDomain toDomain(final RolTrabajadorDTO dto) {
		
		var dtoImp = ObjectHelper.getDefault(dto, new RolTrabajadorDTO());
		
		return new RolTrabajadorDomain(dtoImp.getId(),dtoImp.getNombre());
	}

	@Override
	public List<RolTrabajadorDTO> toDTO(List<RolTrabajadorDomain> domainList) {
		// TODO Auto-generated method stub
		return List.of();
	}

}
