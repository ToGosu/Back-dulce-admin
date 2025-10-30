package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ProductoReciboDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.ProductoReciboDTO;

import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.ProductoDTOAssembler.getProductoDTOAssembler;
import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.ReciboDTOAssembler.getReciboDTOAssembler;

import java.util.ArrayList;
import java.util.List;

public class ProductoReciboDTOAssembler implements DTOAssembler<ProductoReciboDTO, ProductoReciboDomain> {

    private static final DTOAssembler<ProductoReciboDTO, ProductoReciboDomain> instance =
            new ProductoReciboDTOAssembler();

    private ProductoReciboDTOAssembler() {
        super();
    }

    public static DTOAssembler<ProductoReciboDTO, ProductoReciboDomain> getProductoReciboDTOAssembler() {
        return instance;
    }

    @Override
    public ProductoReciboDTO toDTO(final ProductoReciboDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new ProductoReciboDomain());

        var productoDtoTmp = getProductoDTOAssembler().toDTO(domainTmp.getProducto());
        var reciboDtoTmp = getReciboDTOAssembler().toDTO(domainTmp.getRecibo());

        return new ProductoReciboDTO(
                domainTmp.getId(),
                productoDtoTmp,
                reciboDtoTmp,
                domainTmp.getCantidad()
        );
    }

    @Override
    public ProductoReciboDomain toDomain(final ProductoReciboDTO dto) {

        var dtoTmp = ObjectHelper.getDefault(dto, new ProductoReciboDTO());

        var productoDomainTmp = getProductoDTOAssembler().toDomain(dtoTmp.getProducto());
        var reciboDomainTmp = getReciboDTOAssembler().toDomain(dtoTmp.getRecibo());

        return new ProductoReciboDomain(
                dtoTmp.getId(),
                productoDomainTmp,
                reciboDomainTmp,
                dtoTmp.getCantidad()
        );
    }

    @Override
    public List<ProductoReciboDTO> toDTO(final List<ProductoReciboDomain> domainList) {

    	return List.of();
    }
}
