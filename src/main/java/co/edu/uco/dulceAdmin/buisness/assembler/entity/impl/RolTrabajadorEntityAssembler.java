package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.RolTrabajadorDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.entity.RolTrabajadorEntity;

public class RolTrabajadorEntityAssembler implements EntityAssembler<RolTrabajadorEntity, RolTrabajadorDomain>{
	
	private static final EntityAssembler<RolTrabajadorEntity, RolTrabajadorDomain> instance = new RolTrabajadorEntityAssembler();
	
	private RolTrabajadorEntityAssembler() {
		
	}
	
	public static EntityAssembler<RolTrabajadorEntity, RolTrabajadorDomain> getRolTrabajadorEntityAssembler(){
		return instance;
	}
	
	
	@Override
	public RolTrabajadorEntity toEntity(RolTrabajadorDomain domain) {
		var domainImp = ObjectHelper.getDefault(domain, new RolTrabajadorDomain(UUIDHelper.getUUIDHelper().getDefault()));
		
		return new RolTrabajadorEntity(domainImp.getId(),domainImp.getNombre());
		
	}

	@Override
	public RolTrabajadorDomain toDomain(RolTrabajadorEntity entity) {
		var entityImp = ObjectHelper.getDefault(entity, new RolTrabajadorEntity());
		
		return new RolTrabajadorDomain(entityImp.getId(),entityImp.getNombre());
	}

	@Override
	public List<RolTrabajadorEntity> toDTO(List<RolTrabajadorDomain> domainList) {
		return List.of();
	}

}
