package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ClienteDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.entity.ClienteEntity;

public class ClienteEntityAssembler implements EntityAssembler<ClienteEntity, ClienteDomain>{
	
	private static final EntityAssembler<ClienteEntity, ClienteDomain> instance = new ClienteEntityAssembler();
	
	private ClienteEntityAssembler() {
		
	}
	
	public static EntityAssembler<ClienteEntity, ClienteDomain> getClienteEntityAssembler(){
		return instance;
	}
	
	@Override
	public ClienteEntity toEntity(ClienteDomain domain) {
		var domainImp = ObjectHelper.getDefault(domain, new ClienteDomain(UUIDHelper.getUUIDHelper().getDefault()));
		
		return new ClienteEntity(domainImp.getId(),domainImp.getNombre(),
				domainImp.getApellido(),domainImp.getIdentificacion(),domainImp.getCelular(),domainImp.getFechaNacimiento(),
				domainImp.getCorreo());
	}

	@Override
	public ClienteDomain toDomain(ClienteEntity entity) {
		var entityImp = ObjectHelper.getDefault(entity, new ClienteEntity());
		return new ClienteDomain(entityImp.getId(),entityImp.getNombre(),
				entityImp.getApellido(),entityImp.getIdentificacion(),entityImp.getCelular(),entityImp.getFechaNacimiento(),
				entityImp.getCorreo());
	}

	@Override
	public List<ClienteEntity> toDTO(List<ClienteDomain> domainList) {
		return List.of();
	}

}
