package co.edu.uco.dulceAdmin.buisness.assembler.entity;

import java.util.List;

public interface EntityAssembler<E, D> {
	
	E toEntity(D domain);
	
	D toDomain(E entity);
	
	List<E> toDTO(List<D> domainList);
}
