package co.edu.uco.dulceAdmin.dto;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.BooleanHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.DateHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class InventarioDTO extends DTO {
	
	private ProductoDTO producto;
	private Integer stock;
	private Boolean esPerecedero;
	private Date fechaCreacion;
	private Date fechaVencimiento;
	

	public InventarioDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setProducto(ProductoDTO.createDefault());
		setStock(NumericHelper.getDefault());
		setEsPerecedero(BooleanHelper.getDefault());
		setFechaCreacion(DateHelper.getDefault());
		setFechaVencimiento(DateHelper.calculateExpirationDate(getFechaCreacion(), getEsPerecedero()));
	}
	
	public InventarioDTO(final UUID id) {
		super(id);
		setProducto(ProductoDTO.createDefault());
		setStock(NumericHelper.getDefault());
		setEsPerecedero(BooleanHelper.getDefault());
		setFechaCreacion(DateHelper.getDefault());
		setFechaVencimiento(DateHelper.calculateExpirationDate(getFechaCreacion(), getEsPerecedero()));
	}
	
	public InventarioDTO(final UUID id, final ProductoDTO producto, final Integer stock, final Boolean esPerecedero,
			final Date fechaCreacion, final Date fechaVencimiento) {
		super(id);
		setProducto(producto);
		setStock(stock);
		setEsPerecedero(esPerecedero);
		setFechaCreacion(fechaCreacion);
		setFechaVencimiento(ObjectHelper.getDefault(fechaVencimiento, 
				DateHelper.calculateExpirationDate(this.fechaCreacion, this.esPerecedero)));
	}


	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(final ProductoDTO producto) {
		this.producto = ObjectHelper.getDefault(producto, new ProductoDTO());
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
		this.fechaVencimiento = ObjectHelper.getDefault(fechaVencimiento, 
				DateHelper.calculateExpirationDate(this.fechaCreacion, this.esPerecedero));
	}
	public static InventarioDTO createDefault() {
		return new InventarioDTO();
	}
}
