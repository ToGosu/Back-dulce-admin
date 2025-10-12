package co.edu.uco.dulceAdmin.dto;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class ProductoReciboDTO extends DTO {

	private ProductoDTO producto;
	private ReciboDTO recibo;
	private int cantidad;

	public ProductoReciboDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setProducto(new ProductoDTO());
		setRecibo(new ReciboDTO());
		setCantidad(NumericHelper.getDefault());
	}

	public ProductoReciboDTO(final UUID id) {
		super(id);
		setProducto(new ProductoDTO());
		setRecibo(new ReciboDTO());
		setCantidad(NumericHelper.getDefault());
	}

	public ProductoReciboDTO(final UUID id, final ProductoDTO producto, final ReciboDTO recibo, final int cantidad) {
		super(id);
		setProducto(producto);
		setRecibo(recibo);
		setCantidad(cantidad);
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(final ProductoDTO producto) {
		this.producto = ObjectHelper.getDefault(producto, new ProductoDTO());
	}

	public ReciboDTO getRecibo() {
		return recibo;
	}

	public void setRecibo(final ReciboDTO recibo) {
		this.recibo = ObjectHelper.getDefault(recibo, new ReciboDTO());
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
