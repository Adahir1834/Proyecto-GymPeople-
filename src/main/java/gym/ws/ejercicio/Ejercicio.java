package gym.ws.ejercicio;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEjercicio;
	@Column(name = "nombreEjercicio")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Nombre del ejercicio es obligatorio")
	private String nombreEjercicio;
	
	public Ejercicio() {
		super();
	}

	public Ejercicio(int idEjercicio,
			@NotBlank(message = "Nombre del ejercicio es obligatorio") String nombreEjercicio) {
		super();
		this.idEjercicio = idEjercicio;
		this.nombreEjercicio = nombreEjercicio;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public String getNombreEjercicio() {
		return nombreEjercicio;
	}

	public void setNombreEjercicio(String nombreEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
	}
	
	
	
}
