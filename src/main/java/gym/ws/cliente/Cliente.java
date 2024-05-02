package gym.ws.cliente;

import org.hibernate.annotations.JdbcTypeCode;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
 
public class Cliente {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcliente;
	
	@Column(name = "usuario")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Usuario es obligatorio")
	private String usuario;

	@Column(name = "contrasena")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Contraseña es obligatoria")
	private String contrasena;

	@Column(name = "nombre")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Nombre es obligatorio")
	private String nombre;

	@Column(name = "apellidoPaterno")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Apellido Paterno es obligatorio")
	private String apellidopaterno;

	@Column(name = "apellidoMaterno")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Apellido Materno es obligatorio")
	private String apellidomaterno;

	@Column(name = "idMembresia")
	@JdbcTypeCode(java.sql.Types.INTEGER)
	private int idMembresia;

	private String token;

	@JdbcTypeCode(java.sql.Types.INTEGER)
	@Column(name = "idRol")
	private int rol;
	
	
	public Cliente(int idcliente, @NotBlank(message = "Usuario es obligatorio") String usuario,
			@NotBlank(message = "Contraseña es obligatoria") String contrasena,
			@NotBlank(message = "Nombre es obligatorio") String nombre,
			@NotBlank(message = "Apellido Paterno es obligatorio") String apellidopaterno,
			@NotBlank(message = "Apellido Materno es obligatorio") String apellidomaterno, int idMembresia,
			String token, int rol) {
		super();
		this.idcliente = idcliente;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidopaterno = apellidopaterno;
		this.apellidomaterno = apellidomaterno;
		this.idMembresia = idMembresia;
		this.token = token;
		this.rol = rol;
	}


	public Cliente() {
		super();
	}


	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidopaterno() {
		return apellidopaterno;
	}

	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}

	public String getApellidomaterno() {
		return apellidomaterno;
	}

	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}

	public int getIdMembresia() {
		return idMembresia;
	}

	public void setIdMembresia(int idMembresia) {
		this.idMembresia = idMembresia;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}


	
}