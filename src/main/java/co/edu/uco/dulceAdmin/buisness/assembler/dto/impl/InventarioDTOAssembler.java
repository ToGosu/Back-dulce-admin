package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.InventarioDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.InventarioDTO;

import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.ProductoDTOAssembler.getProductoDTOAssembler;

import java.util.ArrayList;
import java.util.List;

public class InventarioDTOAssembler implements DTOAssembler<InventarioDTO, InventarioDomain> {

    private static final DTOAssembler<InventarioDTO, InventarioDomain> instance =
            new InventarioDTOAssembler();

    private InventarioDTOAssembler() {
        super();
    }

    public static DTOAssembler<InventarioDTO, InventarioDomain> getInventarioDTOAssembler() {
        return instance;
    }

    @Override
    public InventarioDTO toDTO(final InventarioDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new InventarioDomain());

        var productoDtoTmp = getProductoDTOAssembler().toDTO(domainTmp.getProducto());

        return new InventarioDTO(
                domainTmp.getId(),
                productoDtoTmp,
                domainTmp.getStock(),
                domainTmp.getEsPerecedero(),
                domainTmp.getFechaCreacion(),
                domainTmp.getFechaVencimiento()
        );
    }

    @Override
    public InventarioDomain toDomain(final InventarioDTO dto) {

        var dtoTmp = ObjectHelper.getDefault(dto, new InventarioDTO());

        var productoDomainTmp = getProductoDTOAssembler().toDomain(dtoTmp.getProducto());

        return new InventarioDomain(
                dtoTmp.getId(),
                productoDomainTmp,
                dtoTmp.getStock(),
                dtoTmp.getEsPerecedero(),
                dtoTmp.getFechaCreacion(),
                dtoTmp.getFechaVencimiento()
        );
    }

    @Override
    public List<InventarioDTO> toDTO(final List<InventarioDomain> domainList) {

    	return List.of();
    }
}
