package gym.ws.empleado;

import org.hibernate.annotations.JdbcTypeCode;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table (name="Empleado")

public class Empleado {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	//@Type(type="text")
	@Column(name="idEmpleado")//cambiar el nombre su esta diferente en la base de datos
	@Hidden
	private int idEmpleado;
	
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message="Nombre es obligatorio")
	private String nombre;
	
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message="Apellido paterno es obligatorio")
	private String apellidoPaterno;
	
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message="Apellido materno es obligatorio")
	private String apellidoMaterno;
	
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private int idRol;
	
	public Empleado() {
		super();
	}
	public Empleado(int idEmpleado, @NotBlank(message = "Nombre es obligatorio") String nombre,
			@NotBlank(message = "Apellido paterno es obligatorio") String apellidoPaterno,
			@NotBlank(message = "Apellido materno es obligatorio") String apellidoMaterno,
			@NotBlank(message = "Id rol es obligatorio") int idRol) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.idRol = idRol;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
		

}
