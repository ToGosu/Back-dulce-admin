package co.edu.uco.dulceAdmin.dto;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;

public class ProductoDTO extends DTO {
	
	private String nombre;
	private Integer precio;
	private TipoProductoDTO tipo;
	
	public ProductoDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setPrecio(NumericHelper.getDefault());
		setTipo(new TipoProductoDTO());
	}
	
	public ProductoDTO(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setPrecio(NumericHelper.getDefault());
		setTipo(new TipoProductoDTO());
	}
	
	public ProductoDTO(final UUID id, final String nombre, final Integer precio, final TipoProductoDTO tipo) {
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
	
	public TipoProductoDTO getTipo() {
		return tipo;
	}
	
	public void setTipo(final TipoProductoDTO tipo) {
		this.tipo = ObjectHelper.getDefault(tipo, new TipoProductoDTO());
	}
}
