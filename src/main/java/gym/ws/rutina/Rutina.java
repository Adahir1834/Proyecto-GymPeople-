package gym.ws.rutina;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rutina")
public class Rutina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRutina;
	@Column(name = "nombreRutina")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Nombre de la rutina es obligatorio")
	private String nombreRutina;
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Repeticiones es obligatorio")
	private int repeticiones;
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Id de ejercicio es obligatorio")
	private int idEjercicio;
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Id de objetivo es obligatorio")
	private int idObjetivo;
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Id de nivel de actividad es obligatorio")
	private int idNivelAct;
	
	public Rutina() {
		super();
	}

	public Rutina(int idRutina, @NotBlank(message = "Nombre de la rutina es obligatorio") String nombreRutina,
			@NotBlank(message = "Repeticiones es obligatorio") int repeticiones,
			@NotBlank(message = "Id de ejercicio es obligatorio") int idEjercicio,
			@NotBlank(message = "Id de objetivo es obligatorio") int idObjetivo,
			@NotBlank(message = "Id de nivel de actividad es obligatorio") int idNivelAct) {
		super();
		this.idRutina = idRutina;
		this.nombreRutina = nombreRutina;
		this.repeticiones = repeticiones;
		this.idEjercicio = idEjercicio;
		this.idObjetivo = idObjetivo;
		this.idNivelAct = idNivelAct;
	}

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	public String getNombreRutina() {
		return nombreRutina;
	}

	public void setNombreRutina(String nombreRutina) {
		this.nombreRutina = nombreRutina;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public int getIdNivelAct() {
		return idNivelAct;
	}

	public void setIdNivelAct(int idNivelAct) {
		this.idNivelAct = idNivelAct;
	}
	
	
}
