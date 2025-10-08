package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

class Domain {
	
	private UUID id;
	
	protected Domain(final UUID id) {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
	
	
}
