package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ProductoReciboDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.entity.ProductoReciboEntity;

import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.ProductoEntityAssembler.getProductoEntityAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.ReciboEntityAssembler.getReciboEntityAssembler;

public final class ProductoReciboEntityAssembler implements EntityAssembler<ProductoReciboEntity, ProductoReciboDomain> {

	private static final EntityAssembler<ProductoReciboEntity, ProductoReciboDomain> instance = new ProductoReciboEntityAssembler();

	private ProductoReciboEntityAssembler() {
		super();
	}

	public static EntityAssembler<ProductoReciboEntity, ProductoReciboDomain> getProductoReciboEntityAssembler() {
		return instance;
	}

	@Override
	public ProductoReciboEntity toEntity(final ProductoReciboDomain domain) {

		var domainTmp = ObjectHelper.getDefault(domain, new ProductoReciboDomain());

		var productoEntityTmp = getProductoEntityAssembler().toEntity(domainTmp.getProducto());
		var reciboEntityTmp = getReciboEntityAssembler().toEntity(domainTmp.getRecibo());

		var entity = new ProductoReciboEntity();
		entity.setId(domainTmp.getId());
		entity.setProducto(productoEntityTmp);
		entity.setRecibo(reciboEntityTmp);
		entity.setCantidad(domainTmp.getCantidad());

		return entity;
	}

	@Override
	public ProductoReciboDomain toDomain(final ProductoReciboEntity entity) {

		var entityTmp = ObjectHelper.getDefault(entity, new ProductoReciboEntity());

		var productoDomainTmp = getProductoEntityAssembler().toDomain(entityTmp.getProducto());
		var reciboDomainTmp = getReciboEntityAssembler().toDomain(entityTmp.getRecibo());

		return new ProductoReciboDomain(
				entityTmp.getId(),
				productoDomainTmp,
				reciboDomainTmp,
				entityTmp.getCantidad()
		);
	}

	@Override
	public List<ProductoReciboEntity> toDTO(final List<ProductoReciboDomain> domainList) {

		return List.of();
	}
}
