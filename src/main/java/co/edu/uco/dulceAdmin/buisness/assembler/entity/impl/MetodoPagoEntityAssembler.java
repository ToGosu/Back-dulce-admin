package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.MetodoPagoDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.entity.MetodoPagoEntity;

public class MetodoPagoEntityAssembler implements EntityAssembler<MetodoPagoEntity, MetodoPagoDomain>{
	
	private static final EntityAssembler<MetodoPagoEntity, MetodoPagoDomain> instance = new MetodoPagoEntityAssembler();
	
	private MetodoPagoEntityAssembler() {
		
	}
	
	public static EntityAssembler<MetodoPagoEntity, MetodoPagoDomain> getMetodoPagoEntityAssembler(){
		return instance;
	}
	
	@Override
	public MetodoPagoEntity toEntity(MetodoPagoDomain domain) {
		var domainImp = ObjectHelper.getDefault(domain, new MetodoPagoDomain(UUIDHelper.getUUIDHelper().getDefault()));
		
		
		return new MetodoPagoEntity(domainImp.getId(),domainImp.getNombre());
	}

	@Override
	public MetodoPagoDomain toDomain(MetodoPagoEntity entity) {
		var entityImp = ObjectHelper.getDefault(entity, new MetodoPagoEntity());
		
		return new MetodoPagoDomain(entityImp.getId(),entityImp.getNombre());
	}

	@Override
	public List<MetodoPagoEntity> toDTO(List<MetodoPagoDomain> domainList) {
		return List.of();
	}

}
