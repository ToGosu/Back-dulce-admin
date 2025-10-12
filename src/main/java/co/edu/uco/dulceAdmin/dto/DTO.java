package co.edu.uco.dulceAdmin.dto;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

class DTO {
	private UUID id;
	
	protected DTO(final UUID id) {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
}