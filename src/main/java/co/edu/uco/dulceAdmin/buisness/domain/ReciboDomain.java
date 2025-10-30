package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.ArrayList;
import java.util.List;
import co.edu.uco.dulceAdmin.crosscuting.helper.NumericHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;

import java.math.BigDecimal;
import java.util.UUID;

public class ReciboDomain extends Domain{
	
	private ClienteDomain cliente;
	private TrabajadorDomain trabajador;
	private MetodoPagoDomain metodoPago;
	private Integer codigo;
	private List<ProductoReciboDomain> productosRecibo;
	
	public ReciboDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
	    setCliente(ClienteDomain.createDefault());
	    setMetodoPago(MetodoPagoDomain.createDefault());
	    setCodigo(NumericHelper.getDefault());
	    setTrabajador(TrabajadorDomain.createDefault());
	    setProductosRecibo(new ArrayList<>());
	    }
	   
	public ReciboDomain(final UUID id) {
		super(id);
	    setCliente(ClienteDomain.createDefault());
	    setMetodoPago(MetodoPagoDomain.createDefault());
	    setCodigo(NumericHelper.getDefault());
	    setTrabajador(TrabajadorDomain.createDefault());
	    setProductosRecibo(new ArrayList<>());
	    }
	public BigDecimal getTotal() {
	    return productosRecibo.stream()
	        .map(ProductoReciboDomain::getTotalProducto)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    }
	public ReciboDomain(final UUID id, final ClienteDomain cliente, final TrabajadorDomain trabajador, final MetodoPagoDomain metodoPago, final Integer codigo, final List<ProductoReciboDomain> productosRecibo) {
		super(id);
		this.cliente=cliente;
		this.trabajador=trabajador;
		this.metodoPago=metodoPago;
		this.codigo=codigo;
		this.productosRecibo=productosRecibo;
	}

	public ClienteDomain getCliente() {
		return cliente;
	}

	public void setCliente(final ClienteDomain cliente) {
		this.cliente = ObjectHelper.getDefault(cliente, new ClienteDomain());
	}

	public TrabajadorDomain getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(final TrabajadorDomain trabajador) {
		this.trabajador = ObjectHelper.getDefault(trabajador, new TrabajadorDomain());
	}

	public MetodoPagoDomain getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(final MetodoPagoDomain metodoPago) {
		this.metodoPago = ObjectHelper.getDefault(metodoPago, new MetodoPagoDomain());
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(final Integer codigo) {
		this.codigo = NumericHelper.getDefault(codigo);
	}

	public List<ProductoReciboDomain> getProductosRecibo() {
		return productosRecibo;
	}

	public void setProductosRecibo(final List<ProductoReciboDomain> productosRecibo) {
		this.productosRecibo = ObjectHelper.getDefault(productosRecibo, new ArrayList<>());
	}
	public static ReciboDomain createDefault() {
		return new ReciboDomain();
	}
	
}