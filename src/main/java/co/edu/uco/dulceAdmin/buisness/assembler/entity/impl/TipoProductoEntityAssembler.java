package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.TipoProductoDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.entity.TipoProductoEntity;

public class TipoProductoEntityAssembler implements EntityAssembler<TipoProductoEntity, TipoProductoDomain>{

	public static final EntityAssembler<TipoProductoEntity, TipoProductoDomain> instance = new TipoProductoEntityAssembler();
	
	private TipoProductoEntityAssembler() {
		
	}
	
	public static EntityAssembler<TipoProductoEntity, TipoProductoDomain> getTipoProductoEntityAssembler(){
		return instance;
	}
	
	
	@Override
	public TipoProductoEntity toEntity(TipoProductoDomain domain) {
		var domainImp= ObjectHelper.getDefault(domain, new TipoProductoDomain(UUIDHelper.getUUIDHelper().getDefault()));
		return new TipoProductoEntity(domainImp.getId(),domainImp.getNombre(),domainImp.getDescripcion());
	}

	@Override
	public TipoProductoDomain toDomain(TipoProductoEntity entity) {
		var entityImp = ObjectHelper.getDefault(entity, new TipoProductoEntity());
		
		return new TipoProductoDomain(entityImp.getId(),entityImp.getNombre(),entityImp.getDescripcion());
	}

	@Override
	public List<TipoProductoEntity> toDTO(List<TipoProductoDomain> domainList) {
		return List.of();
	}

}
