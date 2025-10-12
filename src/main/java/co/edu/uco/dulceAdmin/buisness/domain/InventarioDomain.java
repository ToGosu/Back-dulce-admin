package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.BooleanHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.DateHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class InventarioDomain extends Domain {
	
	private ProductoDomain producto;
	private Integer stock;
	private Boolean esPerecedero;
	private Date fechaCreacion;
	private Date fechaVencimiento;
	
	// -------------------------------------------------------------------------
	// Constructores
	// -------------------------------------------------------------------------
	
	public InventarioDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setProducto(new ProductoDomain());
		setStock(NumericHelper.getDefault());
		setEsPerecedero(BooleanHelper.getDefault());
		setFechaCreacion(DateHelper.getDefault());
		setFechaVencimiento(DateHelper.calculateExpirationDate(getFechaCreacion(), getEsPerecedero()));
	}
	
	public InventarioDomain(final UUID id) {
		super(id);
		setProducto(new ProductoDomain());
		setStock(NumericHelper.getDefault());
		setEsPerecedero(BooleanHelper.getDefault());
		setFechaCreacion(DateHelper.getDefault());
		setFechaVencimiento(DateHelper.calculateExpirationDate(getFechaCreacion(), getEsPerecedero()));
	}
	
	public InventarioDomain(final UUID id, final ProductoDomain producto, final Integer stock, final Boolean esPerecedero,
			final Date fechaCreacion, final Date fechaVencimiento) {
		super(id);
		setProducto(producto);
		setStock(stock);
		setEsPerecedero(esPerecedero);
		setFechaCreacion(fechaCreacion);
		setFechaVencimiento(ObjectHelper.getDefault(fechaVencimiento, 
				DateHelper.calculateExpirationDate(this.fechaCreacion, this.esPerecedero)));
	}

	public ProductoDomain getProducto() {
		return producto;
	}

	public void setProducto(final ProductoDomain producto) {
		this.producto = ObjectHelper.getDefault(producto, new ProductoDomain());
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(final Integer stock) {
		this.stock = NumericHelper.getDefault(stock);
	}

	public Boolean getEsPerecedero() {
		return esPerecedero;
	}

	public void setEsPerecedero(final Boolean esPerecedero) {
		this.esPerecedero = BooleanHelper.getDefault(esPerecedero);
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(final Date fechaCreacion) {
		this.fechaCreacion = DateHelper.getDefault(fechaCreacion);
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(final Date fechaVencimiento) {
		this.fechaVencimiento = ObjectHelper.getDefault(fechaVencimiento, DateHelper.calculateExpirationDate(this.fechaCreacion, this.esPerecedero));
	}
	
	
}