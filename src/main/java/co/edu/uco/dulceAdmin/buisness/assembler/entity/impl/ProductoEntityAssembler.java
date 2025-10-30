package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ProductoDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.entity.ProductoEntity;

import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.TipoProductoEntityAssembler.getTipoProductoEntityAssembler;

public final class ProductoEntityAssembler implements EntityAssembler<ProductoEntity, ProductoDomain> {

	private static final EntityAssembler<ProductoEntity, ProductoDomain> instance = new ProductoEntityAssembler();

	private ProductoEntityAssembler() {
		super();
	}

	public static EntityAssembler<ProductoEntity, ProductoDomain> getProductoEntityAssembler() {
		return instance;
	}

	@Override
	public ProductoEntity toEntity(final ProductoDomain domain) {

		var domainTmp = ObjectHelper.getDefault(domain, new ProductoDomain());
		var tipoEntityTmp = getTipoProductoEntityAssembler().toEntity(domainTmp.getTipo());

		var entity = new ProductoEntity();
		entity.setId(domainTmp.getId());
		entity.setNombre(domainTmp.getNombre());
		entity.setPrecio(domainTmp.getPrecio());
		entity.setTipo(tipoEntityTmp);

		return entity;
	}

	@Override
	public ProductoDomain toDomain(final ProductoEntity entity) {

		var entityTmp = ObjectHelper.getDefault(entity, new ProductoEntity());
		var tipoDomainTmp = getTipoProductoEntityAssembler().toDomain(entityTmp.getTipo());

		return new ProductoDomain(
				entityTmp.getId(),
				entityTmp.getNombre(),
				entityTmp.getPrecio(),
				tipoDomainTmp
		);
	}

	@Override
	public List<ProductoEntity> toDTO(final List<ProductoDomain> domainList) {

		return List.of();
	}
}
