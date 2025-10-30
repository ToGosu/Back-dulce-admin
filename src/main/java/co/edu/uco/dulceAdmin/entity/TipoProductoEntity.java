package co.edu.uco.dulceAdmin.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class TipoProductoEntity extends Entity {

	private String nombre;
	private String descripcion;

	public TipoProductoEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setDescripcion(TextHelper.getDefault());
	}

	public TipoProductoEntity(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setDescripcion(TextHelper.getDefault());
	}

	public TipoProductoEntity(final UUID id, final String nombre, final String descripcion) {
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
	public static TipoProductoEntity createDefault() {
		return new TipoProductoEntity();
	}
}
