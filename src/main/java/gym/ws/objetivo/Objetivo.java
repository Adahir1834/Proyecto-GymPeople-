package gym.ws.objetivo;


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
@Table (name="Objetivo")
public class Objetivo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	//@Type(type="text")
	@Column(name="idObjetivo")
	@Hidden
	private int idObjetivo;
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message="Nombre es obligatorio")
	
	private String nombre;
	public Objetivo() {
		super();
	}
	public Objetivo(int idObjetivo, @NotBlank(message = "Nombre es obligatorio") String nombre) {
		super();
		this.idObjetivo = idObjetivo;
		this.nombre = nombre;
	}
	public int getIdObjetivo() {
		return idObjetivo;
	}
	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

