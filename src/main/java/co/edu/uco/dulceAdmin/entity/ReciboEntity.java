package co.edu.uco.dulceAdmin.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class ReciboEntity extends Entity {

	private ClienteEntity cliente;
	private TrabajadorEntity trabajador;
	private MetodoPagoEntity metodoPago;
	private Integer codigo;
	private List<ProductoReciboEntity> productosRecibo;

	public ReciboEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCliente(new ClienteEntity());
		setTrabajador(new TrabajadorEntity());
		setMetodoPago(new MetodoPagoEntity());
		setCodigo(NumericHelper.getDefault());
		setProductosRecibo(new ArrayList<>());
	}

	public ReciboEntity(final UUID id) {
		super(id);
		setCliente(new ClienteEntity());
		setTrabajador(new TrabajadorEntity());
		setMetodoPago(new MetodoPagoEntity());
		setCodigo(NumericHelper.getDefault());
		setProductosRecibo(new ArrayList<>());
	}

	public ReciboEntity(final UUID id, final ClienteEntity cliente, final TrabajadorEntity trabajador,
			final MetodoPagoEntity metodoPago, final Integer codigo, final List<ProductoReciboEntity> productosRecibo) {
		super(id);
		setCliente(cliente);
		setTrabajador(trabajador);
		setMetodoPago(metodoPago);
		setCodigo(codigo);
		setProductosRecibo(productosRecibo);
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(final ClienteEntity cliente) {
		this.cliente = ObjectHelper.getDefault(cliente, new ClienteEntity());
	}

	public TrabajadorEntity getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(final TrabajadorEntity trabajador) {
		this.trabajador = ObjectHelper.getDefault(trabajador, new TrabajadorEntity());
	}

	public MetodoPagoEntity getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(final MetodoPagoEntity metodoPago) {
		this.metodoPago = ObjectHelper.getDefault(metodoPago, new MetodoPagoEntity());
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(final Integer codigo) {
		this.codigo = NumericHelper.getDefault(codigo);
	}

	public List<ProductoReciboEntity> getProductosRecibo() {
		return productosRecibo;
	}

	public void setProductosRecibo(final List<ProductoReciboEntity> productosRecibo) {
		this.productosRecibo = ObjectHelper.getDefault(productosRecibo, new ArrayList<>());
	}

	public BigDecimal getTotal() {
		return productosRecibo.stream()
				.map(ProductoReciboEntity::getTotalProducto)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
