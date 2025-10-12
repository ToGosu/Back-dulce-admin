package co.edu.uco.dulceAdmin.buisness.domain;

import java.math.BigDecimal;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class ProductoReciboDomain extends Domain{
	
	private ProductoDomain producto;
	private ReciboDomain recibo;
	private int cantidad;
	
	
	public ProductoReciboDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setProducto(new ProductoDomain());
		setRecibo(new ReciboDomain());
		setCantidad(NumericHelper.getDefault());
		
	}
	
    public ProductoReciboDomain(final UUID id) {
        super(id);
        setProducto(new ProductoDomain());
        setRecibo(new ReciboDomain());
        setCantidad(NumericHelper.getDefault());
    }

    public ProductoReciboDomain(final UUID id, final ProductoDomain producto, final ReciboDomain recibo, final int cantidad) {
        super(id);
        setProducto(producto);
        setRecibo(recibo);
        setCantidad(cantidad);
    }

	
	
	public ProductoDomain getProducto() {
		return producto;
	}
	public void setProducto(final ProductoDomain producto) {
		this.producto = ObjectHelper.getDefault(producto, new ProductoDomain());
	}
	public ReciboDomain getRecibo() {
		return recibo;
	}
	public void setRecibo(final ReciboDomain recibo) {
		this.recibo = ObjectHelper.getDefault(recibo, new ReciboDomain());
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