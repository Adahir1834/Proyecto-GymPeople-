package gym.ws.nivelactividad;


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
@Table (name="NivelActividad")

public class NivelActividad {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	//@Type(type="text")
	@Column(name="idNivelAct")
	@Hidden
	private int idNivelAct;
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message="Nombre es obligatorio")
	private String nombre;
	
	public NivelActividad() {
		super();
	}

	public NivelActividad(int idNivelAct, @NotBlank(message = "Nombre es obligatorio") String nombre) {
		super();
		this.idNivelAct = idNivelAct;
		this.nombre = nombre;
	}

	public int getIdNivelAct() {
		return idNivelAct;
	}

	public void setIdNivelAct(int idNivelAct) {
		this.idNivelAct = idNivelAct;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
}
