package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;

public class ProductoDomain extends Domain{
	
	private String nombre;
	private Integer precio;
	private TipoProductoDomain tipo;
	
	public ProductoDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setPrecio(NumericHelper.getDefault());
		setTipo(TipoProductoDomain.createDefault());
	}
	
	public ProductoDomain(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setPrecio(NumericHelper.getDefault());
		setTipo(TipoProductoDomain.createDefault());
	}
	public ProductoDomain(final UUID id, final String nombre, final int precio, final TipoProductoDomain tipo) {
		super(id);
		this.nombre=nombre;
		this.precio=precio;
		this.tipo=tipo;
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
	public TipoProductoDomain getTipo() {
		return tipo;
	}
	public void setTipo(final TipoProductoDomain tipo) {
		this.tipo = ObjectHelper.getDefault(tipo, new TipoProductoDomain());
	}

	public static ProductoDomain createDefault() {
		return new ProductoDomain();
	}
}