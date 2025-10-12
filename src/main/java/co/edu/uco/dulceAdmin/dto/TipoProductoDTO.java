package co.edu.uco.dulceAdmin.dto;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class TipoProductoDTO extends DTO {

	private String nombre;
	private String descripcion;

	public TipoProductoDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setDescripcion(TextHelper.getDefault());
	}

	public TipoProductoDTO(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setDescripcion(TextHelper.getDefault());
	}

	public TipoProductoDTO(final UUID id, final String nombre, final String descripcion) {
		super(id);
		setNombre(nombre);
		setDescripcion(descripcion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = TextHelper.getDefaultWithTrim(descripcion);
	}
}
