package co.edu.uco.dulceAdmin.entity;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class TrabajadorEntity extends Entity {

	private String nombre;
	private String apellido;
	private String identificacion;
	private String numeroCelular;
	private RolTrabajadorEntity rol;
	private String contraseñaInicioSesion;

	public TrabajadorEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setNumeroCelular(TextHelper.getDefault());
		setRol(RolTrabajadorEntity.createDefault());
		setContraseñaInicioSesion(TextHelper.getDefault());
	}

	public TrabajadorEntity(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setNumeroCelular(TextHelper.getDefault());
		setRol(RolTrabajadorEntity.createDefault());
		setContraseñaInicioSesion(TextHelper.getDefault());
	}

	public TrabajadorEntity(final UUID id, 
			final String nombre, 
			final String apellido, 
			final String identificacion,
			final String numeroCelular, 
			final RolTrabajadorEntity rol, 
			final String contraseñaInicioSesion) {
		super(id);
		setNombre(nombre);
		setApellido(apellido);
		setIdentificacion(identificacion);
		setNumeroCelular(numeroCelular);
		setRol(rol);
		setContraseñaInicioSesion(contraseñaInicioSesion);
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

	public RolTrabajadorEntity getRol() {
		return rol;
	}

	public void setRol(final RolTrabajadorEntity rol) {
		this.rol = ObjectHelper.getDefault(rol, new RolTrabajadorEntity());
	}

	public String getContraseñaInicioSesion() {
		return contraseñaInicioSesion;
	}

	public void setContraseñaInicioSesion(final String contraseñaInicioSesion) {
		this.contraseñaInicioSesion = TextHelper.getDefaultWithTrim(contraseñaInicioSesion);
	}
	public static TrabajadorEntity createDefault() {
		return new TrabajadorEntity();
	}
}
