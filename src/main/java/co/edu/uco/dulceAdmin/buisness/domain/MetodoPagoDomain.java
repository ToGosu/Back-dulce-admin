package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class MetodoPagoDomain extends Domain {
	private String nombre;
	
	public MetodoPagoDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	public MetodoPagoDomain(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	public MetodoPagoDomain(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
	public static MetodoPagoDomain createDefault() {
		return new MetodoPagoDomain();
	}

}