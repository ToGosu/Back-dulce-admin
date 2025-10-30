package co.edu.uco.dulceAdmin.buisness.assembler.dto.impl;

import co.edu.uco.dulceAdmin.buisness.assembler.dto.DTOAssembler;
import co.edu.uco.dulceAdmin.buisness.domain.TrabajadorDomain;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.dto.TrabajadorDTO;

import static co.edu.uco.dulceAdmin.buisness.assembler.dto.impl.RolTrabajadorDTOAssembler.getRolTrabajadorDTOAssembler;

import java.util.ArrayList;
import java.util.List;

public class TrabajadorDTOAssembler implements DTOAssembler<TrabajadorDTO, TrabajadorDomain> {

    private static final DTOAssembler<TrabajadorDTO, TrabajadorDomain> instance =
            new TrabajadorDTOAssembler();

    private TrabajadorDTOAssembler() {
        super();
    }

    public static DTOAssembler<TrabajadorDTO, TrabajadorDomain> getTrabajadorDTOAssembler() {
        return instance;
    }

    @Override
    public TrabajadorDTO toDTO(final TrabajadorDomain domain) {

        var domainTmp = ObjectHelper.getDefault(domain, new TrabajadorDomain());
        var rolDtoTmp = getRolTrabajadorDTOAssembler().toDTO(domainTmp.getRol());

        return new TrabajadorDTO(
                domainTmp.getId(),
                domainTmp.getNombre(),
                domainTmp.getApellido(),
                domainTmp.getIdentificacion(),
                domainTmp.getNumeroCelular(),
                rolDtoTmp,
                domainTmp.getContraseñaInicioSesion()
        );
    }

    @Override
    public TrabajadorDomain toDomain(final TrabajadorDTO dto) {

        var dtoTmp = ObjectHelper.getDefault(dto, new TrabajadorDTO());
        var rolDomainTmp = getRolTrabajadorDTOAssembler().toDomain(dtoTmp.getRol());

        return new TrabajadorDomain(
                dtoTmp.getId(),
                dtoTmp.getNombre(),
                dtoTmp.getApellido(),
                dtoTmp.getIdentificacion(),
                dtoTmp.getNumeroCelular(),
                rolDomainTmp,
                dtoTmp.getContraseñaInicioSesion()
        );
    }

    @Override
    public List<TrabajadorDTO> toDTO(final List<TrabajadorDomain> domainList) {

    	return List.of();
    }
}
