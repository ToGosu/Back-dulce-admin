package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.List;
import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.TrabajadorDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.entity.TrabajadorEntity;

import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.RolTrabajadorEntityAssembler.getRolTrabajadorEntityAssembler;

public final class TrabajadorEntityAssembler implements EntityAssembler<TrabajadorEntity, TrabajadorDomain> {

	private static final EntityAssembler<TrabajadorEntity, TrabajadorDomain> instance = new TrabajadorEntityAssembler();

	private TrabajadorEntityAssembler() {
		super();
	}

	public static EntityAssembler<TrabajadorEntity, TrabajadorDomain> getTrabajadorEntityAssembler() {
		return instance;
	}

	@Override
	public TrabajadorEntity toEntity(final TrabajadorDomain domain) {

		var domainTmp = ObjectHelper.getDefault(domain, new TrabajadorDomain());
		var rolEntityTmp = getRolTrabajadorEntityAssembler().toEntity(domainTmp.getRol());

		var entity = new TrabajadorEntity();
		entity.setId(domainTmp.getId());
		entity.setNombre(domainTmp.getNombre());
		entity.setApellido(domainTmp.getApellido());
		entity.setIdentificacion(domainTmp.getIdentificacion());
		entity.setNumeroCelular(domainTmp.getNumeroCelular());
		entity.setRol(rolEntityTmp);
		entity.setContraseñaInicioSesion(domainTmp.getContraseñaInicioSesion());

		return entity;
	}

	@Override
	public TrabajadorDomain toDomain(final TrabajadorEntity entity) {

		var entityTmp = ObjectHelper.getDefault(entity, new TrabajadorEntity());
		var rolDomainTmp = getRolTrabajadorEntityAssembler().toDomain(entityTmp.getRol());

		return new TrabajadorDomain(
				entityTmp.getId(),
				entityTmp.getNombre(),
				entityTmp.getApellido(),
				entityTmp.getIdentificacion(),
				entityTmp.getNumeroCelular(),
				rolDomainTmp,
				entityTmp.getContraseñaInicioSesion()
		);
	}

	@Override
	public List<TrabajadorEntity> toDTO(final List<TrabajadorDomain> domainList) {

		return List.of();
	}
}
