package co.edu.uco.dulceAdmin.dto;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.dulceAdmin.crosscuting.helper.DateHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.UUIDHelper;

public class ClienteDTO extends DTO {
	
	private String nombre;
	private String apellido;
	private String identificacion;
	private String celular;
	private Date fechaNacimiento;
	private String correo;
	
	public ClienteDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setCelular(TextHelper.getDefault());
		setFechaNacimiento(DateHelper.getDefault());
		setCorreo(TextHelper.getDefault());
	}
	
	public ClienteDTO(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
		setApellido(TextHelper.getDefault());
		setIdentificacion(TextHelper.getDefault());
		setCelular(TextHelper.getDefault());
		setFechaNacimiento(DateHelper.getDefault());
		setCorreo(TextHelper.getDefault());
	}
	
	public ClienteDTO(final UUID id, final String nombre, final String apellido, final String identificacion, final String celular, final Date fechaNacimiento, final String correo) {
		super(id);
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
		this.celular = celular;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
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
	public String getCelular() {
		return celular;
	}
	public void setCelular(final String celular) {
		this.celular = TextHelper.getDefaultWithTrim(celular);
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(final Date fechaNacimiento) {
		this.fechaNacimiento = DateHelper.getDefault(fechaNacimiento);
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(final String correo) {
		this.correo = TextHelper.getDefaultWithTrim(correo);
	}
	public static ClienteDTO createDefault() {
		return new ClienteDTO();
	}
}
