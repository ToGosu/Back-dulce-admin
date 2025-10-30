package co.edu.uco.dulceAdmin.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class MetodoPagoEntity extends Entity {

	private String nombre;
	
	public MetodoPagoEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	
	public MetodoPagoEntity(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	
	public MetodoPagoEntity(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
	public static MetodoPagoEntity createDefault() {
		return new MetodoPagoEntity();
	}
}
