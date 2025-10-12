package co.edu.uco.dulceAdmin.buisness.domain;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class TrabajadorDomain extends Domain{
	
	private String nombre;
	private String apellido;
	private String identificacion;
	private String numeroCelular;
	private RolTrabajadorDomain rol;
	private String contraseñaInicioSesion;
	
	public TrabajadorDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setNumeroCelular(TextHelper.getDefault());
		setRol(new RolTrabajadorDomain());
		setContraseñaInicioSesion(TextHelper.getDefault());
	}
	public TrabajadorDomain(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setNumeroCelular(TextHelper.getDefault());
		setRol(new RolTrabajadorDomain());
		setContraseñaInicioSesion(TextHelper.getDefault());
	}
	public TrabajadorDomain(final UUID id, final String nombre, final String apellido, final String identificacion, final String numeroCelular, final RolTrabajadorDomain rol, final String contraseñaInicioSesion) {
		super(id);
		this.nombre=nombre;
		this.apellido=apellido;
		this.identificacion=identificacion;
		this.numeroCelular=numeroCelular;
		this.rol=rol;
		this.contraseñaInicioSesion=contraseñaInicioSesion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(final String nombre) {
		this.nombre = TextHelper.getDefaultWithTrim(nombre);
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(final String apellido) {
		this.apellido = TextHelper.getDefaultWithTrim(apellido);
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(final String identificacion) {
		this.identificacion = TextHelper.getDefaultWithTrim(identificacion);
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(final String numeroCelular) {
		this.numeroCelular = TextHelper.getDefaultWithTrim(numeroCelular);
	}
	public RolTrabajadorDomain getRol() {
		return rol;
	}
	public void setRol(final RolTrabajadorDomain rol) {
		this.rol = ObjectHelper.getDefault(rol, new RolTrabajadorDomain());
	}
	public String getContraseñaInicioSesion() {
		return contraseñaInicioSesion;
	}
	public void setContraseñaInicioSesion(final String contraseñaInicioSesion) {
		this.contraseñaInicioSesion = TextHelper.getDefaultWithTrim(contraseñaInicioSesion);
	}
	
	
	
	
}