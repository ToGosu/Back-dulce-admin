package co.edu.uco.dulceAdmin.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;

public class ProductoEntity extends Entity {
	
	private String nombre;
	private Integer precio;
	private TipoProductoEntity tipo;
	
	public ProductoEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setPrecio(NumericHelper.getDefault());
		setTipo(new TipoProductoEntity());
	}
	
	public ProductoEntity(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setPrecio(NumericHelper.getDefault());
		setTipo(new TipoProductoEntity());
	}
	
	public ProductoEntity(final UUID id, final String nombre, final Integer precio, final TipoProductoEntity tipo) {
		super(id);
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
	
	public Integer getPrecio() {
		return precio;
	}
	
	public void setPrecio(final Integer precio) {
		this.precio = NumericHelper.getDefault(precio);
	}
	
	public TipoProductoEntity getTipo() {
		return tipo;
	}
	
	public void setTipo(final TipoProductoEntity tipo) {
		this.tipo = ObjectHelper.getDefault(tipo, new TipoProductoEntity());
	}
}
