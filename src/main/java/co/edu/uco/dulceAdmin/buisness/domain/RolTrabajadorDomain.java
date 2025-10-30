package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class RolTrabajadorDomain extends Domain{

	private String nombre;
	
	public RolTrabajadorDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	public RolTrabajadorDomain(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	public RolTrabajadorDomain(final UUID id, final String nombre) {
		super(id);
		this.nombre=nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
	public static RolTrabajadorDomain createDefault() {
		return new RolTrabajadorDomain();
	}
}