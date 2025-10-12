package co.edu.uco.dulceAdmin.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class RolTrabajadorEntity extends Entity {

	private String nombre;

	public RolTrabajadorEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}

	public RolTrabajadorEntity(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}

	public RolTrabajadorEntity(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
}
