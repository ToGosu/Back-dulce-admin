package co.edu.uco.dulceAdmin.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class ReciboDTO extends DTO {

	private ClienteDTO cliente;
	private TrabajadorDTO trabajador;
	private MetodoPagoDTO metodoPago;
	private Integer codigo;
	private List<ProductoReciboDTO> productosRecibo;

	public ReciboDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setCliente(ClienteDTO.createDefault());
		setTrabajador(TrabajadorDTO.createDefault());
		setMetodoPago(MetodoPagoDTO.createDefault());
		setCodigo(NumericHelper.getDefault());
		setProductosRecibo(new ArrayList<>());
	}

	public ReciboDTO(final UUID id) {
		super(id);
		setCliente(ClienteDTO.createDefault());
		setTrabajador(TrabajadorDTO.createDefault());
		setMetodoPago(MetodoPagoDTO.createDefault());
		setCodigo(NumericHelper.getDefault());
		setProductosRecibo(new ArrayList<>());
	}

	public ReciboDTO(final UUID id, final ClienteDTO cliente, final TrabajadorDTO trabajador, final MetodoPagoDTO metodoPago,
			final Integer codigo, final List<ProductoReciboDTO> productosRecibo) {
		super(id);
		setCliente(cliente);
		setTrabajador(trabajador);
		setMetodoPago(metodoPago);
		setCodigo(codigo);
		setProductosRecibo(productosRecibo);
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(final ClienteDTO cliente) {
		this.cliente = ObjectHelper.getDefault(cliente, new ClienteDTO());
	}

	public TrabajadorDTO getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(final TrabajadorDTO trabajador) {
		this.trabajador = ObjectHelper.getDefault(trabajador, new TrabajadorDTO());
	}

	public MetodoPagoDTO getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(final MetodoPagoDTO metodoPago) {
		this.metodoPago = ObjectHelper.getDefault(metodoPago, new MetodoPagoDTO());
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(final Integer codigo) {
		this.codigo = NumericHelper.getDefault(codigo);
	}

	public List<ProductoReciboDTO> getProductosRecibo() {
		return productosRecibo;
	}

	public void setProductosRecibo(final List<ProductoReciboDTO> productosRecibo) {
		this.productosRecibo = ObjectHelper.getDefault(productosRecibo, new ArrayList<>());
	}

	public BigDecimal getTotal() {
		return productosRecibo.stream()
				.map(ProductoReciboDTO::getTotalProducto)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	public static ReciboDTO createDefault() {
		return new ReciboDTO();
	}
}
