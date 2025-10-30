package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ReciboDomain;
import co.edu.uco.dulceAdmin.buisness.domain.ProductoReciboDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.ReciboDTO;
import co.edu.uco.dulceAdmin.dto.ProductoReciboDTO;

import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.ClienteDTOAssembler.getClienteDTOAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.TrabajadorDTOAssembler.getTrabajadorDTOAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.MetodoPagoDTOAssembler.getMetodoPagoDTOAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.ProductoReciboDTOAssembler.getProductoReciboDTOAssembler;

import java.util.ArrayList;
import java.util.List;

public class ReciboDTOAssembler implements DTOAssembler<ReciboDTO, ReciboDomain> {

    private static final DTOAssembler<ReciboDTO, ReciboDomain> instance =
            new ReciboDTOAssembler();

    private ReciboDTOAssembler() {
        super();
    }

    public static DTOAssembler<ReciboDTO, ReciboDomain> getReciboDTOAssembler() {
        return instance;
    }

    @Override
    public ReciboDTO toDTO(final ReciboDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new ReciboDomain());

        var clienteDtoTmp = getClienteDTOAssembler().toDTO(domainTmp.getCliente());
        var trabajadorDtoTmp = getTrabajadorDTOAssembler().toDTO(domainTmp.getTrabajador());
        var metodoPagoDtoTmp = getMetodoPagoDTOAssembler().toDTO(domainTmp.getMetodoPago());
        var productosReciboDtoTmp = getProductoReciboDTOAssembler().toDTO(domainTmp.getProductosRecibo());

        return new ReciboDTO(
                domainTmp.getId(),
                clienteDtoTmp,
                trabajadorDtoTmp,
                metodoPagoDtoTmp,
                domainTmp.getCodigo(),
                productosReciboDtoTmp
        );
    }

    @Override
    public ReciboDomain toDomain(final ReciboDTO dto) {

        var dtoTmp = ObjectHelper.getDefault(dto, new ReciboDTO());

        var clienteDomainTmp = getClienteDTOAssembler().toDomain(dtoTmp.getCliente());
        var trabajadorDomainTmp = getTrabajadorDTOAssembler().toDomain(dtoTmp.getTrabajador());
        var metodoPagoDomainTmp = getMetodoPagoDTOAssembler().toDomain(dtoTmp.getMetodoPago());

        var productosReciboDtoList = ObjectHelper.getDefault(dtoTmp.getProductosRecibo(), new ArrayList<ProductoReciboDTO>());
        var productosReciboDomainList = new ArrayList<ProductoReciboDomain>();
        for (var productoReciboDto : productosReciboDtoList) {
            productosReciboDomainList.add(getProductoReciboDTOAssembler().toDomain(productoReciboDto));
        }

        return new ReciboDomain(
                dtoTmp.getId(),
                clienteDomainTmp,
                trabajadorDomainTmp,
                metodoPagoDomainTmp,
                dtoTmp.getCodigo(),
                productosReciboDomainList
        );
    }

    @Override
    public List<ReciboDTO> toDTO(final List<ReciboDomain> domainList) {

    	return List.of();
    }
}
