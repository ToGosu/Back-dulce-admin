package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.ProductoDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.ProductoDTO;

import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.TipoProductoDTOAssembler.getTipoProductoDTOAssembler;

import java.util.ArrayList;
import java.util.List;

public class ProductoDTOAssembler implements DTOAssembler<ProductoDTO, ProductoDomain> {

    private static final DTOAssembler<ProductoDTO, ProductoDomain> instance = new ProductoDTOAssembler();

    private ProductoDTOAssembler() {
        super();
    }

    public static DTOAssembler<ProductoDTO, ProductoDomain> getProductoDTOAssembler() {
        return instance;
    }

    @Override
    public ProductoDTO toDTO(final ProductoDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new ProductoDomain());

        var tipoDtoTmp = getTipoProductoDTOAssembler().toDTO(domainTmp.getTipo());

        return new ProductoDTO(
                domainTmp.getId(),
                domainTmp.getNombre(),
                domainTmp.getPrecio(),
                tipoDtoTmp
        );
    }

    @Override
    public ProductoDomain toDomain(final ProductoDTO dto) {

        var dtoTmp = ObjectHelper.getDefault(dto, new ProductoDTO());

        var tipoDomainTmp = getTipoProductoDTOAssembler().toDomain(dtoTmp.getTipo());

        return new ProductoDomain(
                dtoTmp.getId(),
                dtoTmp.getNombre(),
                dtoTmp.getPrecio(),
                tipoDomainTmp
        );
    }

    @Override
    public List<ProductoDTO> toDTO(final List<ProductoDomain> domainList) {

    	return List.of();
    }
}
