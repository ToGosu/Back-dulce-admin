package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.MetodoPagoDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.MetodoPagoDTO;

public class MetodoPagoDTOAssembler implements DTOAssembler<MetodoPagoDTO, MetodoPagoDomain>{
	
	private static final DTOAssembler<MetodoPagoDTO, MetodoPagoDomain> instance = new MetodoPagoDTOAssembler();
	
	private MetodoPagoDTOAssembler() {
		super();
	}
	
	public static DTOAssembler<MetodoPagoDTO,MetodoPagoDomain> getMetodoPagoDTOAssembler(){
		return instance;
	}
	
	@Override
	public MetodoPagoDTO toDTO(MetodoPagoDomain domain) {
		var domainImp = ObjectHelper.getDefault(domain, new MetodoPagoDomain());
		
		return new MetodoPagoDTO(domainImp.getId(),domainImp.getNombre());
	}

	@Override
	public MetodoPagoDomain toDomain(MetodoPagoDTO dto) {
		var dtoImp = ObjectHelper.getDefault(dto, new MetodoPagoDTO());
		
		return new MetodoPagoDomain(dtoImp.getId(),dtoImp.getNombre());
	}

	@Override
	public List<MetodoPagoDTO> toDTO(List<MetodoPagoDomain> domainList) {
		return List.of();
	}

}
