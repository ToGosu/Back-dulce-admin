package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class TipoProductoDomain extends Domain{
	private String nombre;
	private String descripcion;
	
	public TipoProductoDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setDescripcion(TextHelper.getDefault());
	}
	public TipoProductoDomain(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setDescripcion(TextHelper.getDefault());
	}
	public TipoProductoDomain(final UUID id, final String nombre, final String descripcion) {
		super(id);
		this.nombre=nombre;
		this.descripcion=descripcion;
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
	public static TipoProductoDomain createDefault() {
		return new TipoProductoDomain();
	}
}