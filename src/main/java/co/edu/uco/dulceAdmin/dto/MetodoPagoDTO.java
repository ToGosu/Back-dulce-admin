package co.edu.uco.dulceAdmin.dto;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class MetodoPagoDTO extends DTO {

	private String nombre;
	
	public MetodoPagoDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	
	public MetodoPagoDTO(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	
	public MetodoPagoDTO(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
	public static MetodoPagoDTO createDefault() {
		return new MetodoPagoDTO();
	}
}
