package co.edu.uco.dulceAdmin.buisness.assembler.entity.impl;

import co.edu.uco.dulceAdmin.buisness.assembler.entity.EntityAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ReciboDomain;
import co.edu.uco.dulceAdmin.buisness.domain.ProductoReciboDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.entity.ReciboEntity;
import co.edu.uco.dulceAdmin.entity.ProductoReciboEntity;

import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.ClienteEntityAssembler.getClienteEntityAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.TrabajadorEntityAssembler.getTrabajadorEntityAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.MetodoPagoEntityAssembler.getMetodoPagoEntityAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.entity.impl.ProductoReciboEntityAssembler.getProductoReciboEntityAssembler;

import java.util.ArrayList;
import java.util.List;

public final class ReciboEntityAssembler implements EntityAssembler<ReciboEntity, ReciboDomain> {

    private static final EntityAssembler<ReciboEntity, ReciboDomain> instance =
            new ReciboEntityAssembler();

    private ReciboEntityAssembler() {
        super();
    }

    public static EntityAssembler<ReciboEntity, ReciboDomain> getReciboEntityAssembler() {
        return instance;
    }

    @Override
    public ReciboEntity toEntity(final ReciboDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new ReciboDomain());

        var clienteEntityTmp = getClienteEntityAssembler().toEntity(domainTmp.getCliente());
        var trabajadorEntityTmp = getTrabajadorEntityAssembler().toEntity(domainTmp.getTrabajador());
        var metodoPagoEntityTmp = getMetodoPagoEntityAssembler().toEntity(domainTmp.getMetodoPago());

        var productosReciboDomainList = ObjectHelper.getDefault(domainTmp.getProductosRecibo(), new ArrayList<ProductoReciboDomain>());
        var productosReciboEntityList = new ArrayList<ProductoReciboEntity>();
        for (var productoReciboDomain : productosReciboDomainList) {
            productosReciboEntityList.add(getProductoReciboEntityAssembler().toEntity(productoReciboDomain));
        }

        return new ReciboEntity(
                domainTmp.getId(),
                clienteEntityTmp,
                trabajadorEntityTmp,
                metodoPagoEntityTmp,
                domainTmp.getCodigo(),
                productosReciboEntityList
        );
    }

    @Override
    public ReciboDomain toDomain(final ReciboEntity entity) {

        var entityTmp = ObjectHelper.getDefault(entity, new ReciboEntity());

        var clienteDomainTmp = getClienteEntityAssembler().toDomain(entityTmp.getCliente());
        var trabajadorDomainTmp = getTrabajadorEntityAssembler().toDomain(entityTmp.getTrabajador());
        var metodoPagoDomainTmp = getMetodoPagoEntityAssembler().toDomain(entityTmp.getMetodoPago());

        var productosReciboEntityList = ObjectHelper.getDefault(entityTmp.getProductosRecibo(), new ArrayList<ProductoReciboEntity>());
        var productosReciboDomainList = new ArrayList<ProductoReciboDomain>();
        for (var productoReciboEntity : productosReciboEntityList) {
            productosReciboDomainList.add(getProductoReciboEntityAssembler().toDomain(productoReciboEntity));
        }

        return new ReciboDomain(
                entityTmp.getId(),
                clienteDomainTmp,
                trabajadorDomainTmp,
                metodoPagoDomainTmp,
                entityTmp.getCodigo(),
                productosReciboDomainList
        );
    }

    @Override
    public List<ReciboEntity> toDTO(final List<ReciboDomain> domainList) {

    	return List.of();
    }
}
