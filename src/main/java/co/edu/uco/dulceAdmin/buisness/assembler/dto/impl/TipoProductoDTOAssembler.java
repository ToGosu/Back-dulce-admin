package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.TipoProductoDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.TipoProductoDTO;

public final class TipoProductoDTOAssembler implements DTOAssembler<TipoProductoDTO, TipoProductoDomain>{

	private static final DTOAssembler<TipoProductoDTO, TipoProductoDomain> instance = new TipoProductoDTOAssembler();
	
	private TipoProductoDTOAssembler() {
		super();
	}
	
	public static DTOAssembler<TipoProductoDTO, TipoProductoDomain> getTipoProductoDTOAssembler(){
		return instance;
	}
	
	
	@Override
	public TipoProductoDTO toDTO(TipoProductoDomain domain) {
		
		var domainImp=ObjectHelper.getDefault(domain, new TipoProductoDomain());
		
		return new TipoProductoDTO(domainImp.getId(),
				domainImp.getNombre(),
				domainImp.getDescripcion());
	}

	@Override
	public TipoProductoDomain toDomain(final TipoProductoDTO dto) {
		var dtoImp = ObjectHelper.getDefault(dto, new TipoProductoDTO());
		
		return new TipoProductoDomain(dtoImp.getId(), dtoImp.getNombre(), dtoImp.getDescripcion());
	}

	@Override
	public List<TipoProductoDTO> toDTO(final List<TipoProductoDomain> domainList) {
		return List.of();
	}
	

}
