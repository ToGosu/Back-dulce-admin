package co.edu.uco.dulceAdmin.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

class Entity {
	private UUID id;
	
	protected Entity(final UUID id) {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
}

