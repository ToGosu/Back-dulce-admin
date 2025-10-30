package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;
import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.InventarioDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.entity.InventarioEntity;

import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.ProductoEntityAssembler.getProductoEntityAssembler;

public final class InventarioEntityAssembler implements EntityAssembler<InventarioEntity, InventarioDomain> {

	private static final EntityAssembler<InventarioEntity, InventarioDomain> instance = new InventarioEntityAssembler();

	private InventarioEntityAssembler() {
		super();
	}

	public static EntityAssembler<InventarioEntity, InventarioDomain> getInventarioEntityAssembler() {
		return instance;
	}

	@Override
	public InventarioEntity toEntity(final InventarioDomain domain) {

		var domainTmp = ObjectHelper.getDefault(domain, new InventarioDomain());
		var productoEntityTmp = getProductoEntityAssembler().toEntity(domainTmp.getProducto());

		var entity = new InventarioEntity();
		entity.setId(domainTmp.getId());
		entity.setProducto(productoEntityTmp);
		entity.setStock(domainTmp.getStock());
		entity.setEsPerecedero(domainTmp.getEsPerecedero());
		entity.setFechaCreacion(domainTmp.getFechaCreacion());
		entity.setFechaVencimiento(domainTmp.getFechaVencimiento());

		return entity;
	}

	@Override
	public InventarioDomain toDomain(final InventarioEntity entity) {

		var entityTmp = ObjectHelper.getDefault(entity, new InventarioEntity());
		var productoDomainTmp = getProductoEntityAssembler().toDomain(entityTmp.getProducto());

		return new InventarioDomain(
				entityTmp.getId(),
				productoDomainTmp,
				entityTmp.getStock(),
				entityTmp.getEsPerecedero(),
				entityTmp.getFechaCreacion(),
				entityTmp.getFechaVencimiento()
		);
	}

	@Override
	public List<InventarioEntity> toDTO(final List<InventarioDomain> domainList) {

		return List.of();
	}
}
