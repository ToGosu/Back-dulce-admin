package co.edu.uco.dulceAdmin.dto;

import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class TrabajadorDTO extends DTO {

	private String nombre;
	private String apellido;
	private String identificacion;
	private String numeroCelular;
	private RolTrabajadorDTO rol;
	private String contraseñaInicioSesion;

	public TrabajadorDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setNumeroCelular(TextHelper.getDefault());
		setRol(new RolTrabajadorDTO());
		setContraseñaInicioSesion(TextHelper.getDefault());
	}

	public TrabajadorDTO(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setNumeroCelular(TextHelper.getDefault());
		setRol(new RolTrabajadorDTO());
		setContraseñaInicioSesion(TextHelper.getDefault());
	}

	public TrabajadorDTO(final UUID id, final String nombre, final String apellido, final String identificacion,
			final String numeroCelular, final RolTrabajadorDTO rol, final String contraseñaInicioSesion) {
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

	public RolTrabajadorDTO getRol() {
		return rol;
	}

	public void setRol(final RolTrabajadorDTO rol) {
		this.rol = ObjectHelper.getDefault(rol, new RolTrabajadorDTO());
	}

	public String getContraseñaInicioSesion() {
		return contraseñaInicioSesion;
	}

	public void setContraseñaInicioSesion(final String contraseñaInicioSesion) {
		this.contraseñaInicioSesion = TextHelper.getDefaultWithTrim(contraseñaInicioSesion);
	}
}
