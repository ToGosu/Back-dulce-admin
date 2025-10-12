package co.edu.uco.dulceAdmin.entity;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class ProductoReciboEntity extends Entity {

	private ProductoEntity producto;
	private ReciboEntity recibo;
	private int cantidad;

	public ProductoReciboEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setProducto(new ProductoEntity());
		setRecibo(new ReciboEntity());
		setCantidad(NumericHelper.getDefault());
	}

	public ProductoReciboEntity(final UUID id) {
		super(id);
		setProducto(new ProductoEntity());
		setRecibo(new ReciboEntity());
		setCantidad(NumericHelper.getDefault());
	}

	public ProductoReciboEntity(final UUID id, final ProductoEntity producto, final ReciboEntity recibo, final int cantidad) {
		super(id);
		setProducto(producto);
		setRecibo(recibo);
		setCantidad(cantidad);
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(final ProductoEntity producto) {
		this.producto = ObjectHelper.getDefault(producto, new ProductoEntity());
	}

	public ReciboEntity getRecibo() {
		return recibo;
	}

	public void setRecibo(final ReciboEntity recibo) {
		this.recibo = ObjectHelper.getDefault(recibo, new ReciboEntity());
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(final int cantidad) {
		this.cantidad = NumericHelper.getDefault(cantidad);
	}

	public BigDecimal getTotalProducto() {
		BigDecimal precio = BigDecimal.valueOf(ObjectHelper.getDefault(producto.getPrecio(), 0));
		BigDecimal cantidadBig = BigDecimal.valueOf(cantidad);
		return precio.multiply(cantidadBig);
	}
}
