package gym.ws.membresia;

import java.sql.Date;
import java.time.LocalDate;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(name = "membresia")

public class Membresia {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMembresia;
	
	@Column(name = "tipo")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "Tipo es obligatorio")
	private String tipo;

	@Column(name = "costo")
	@JdbcTypeCode(java.sql.Types.DOUBLE)
	private int costo;

	@Column(name = "fechaInicio")
	@JdbcTypeCode(java.sql.Types.DATE)
	private Date fechaInicio;

	@Column(name = "fechaFin")
	@JdbcTypeCode(java.sql.Types.DATE)
	private Date fechaFin;
	
	public int getIdMembresia() {
		return idMembresia;
	}

	public void setIdMembresia(int idMembresia) {
		this.idMembresia = idMembresia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

 
	public Membresia(int idMembresia, @NotBlank(message = "Tipo es obligatorio") String tipo, int costo,
			@NotNull(message = "Fecha Inicio no puede ser nula") Date fechaInicio,
			@NotNull(message = "Fecha Fin no puede ser nula") Date fechaFin) {
		super();
		this.idMembresia = idMembresia;
		this.tipo = tipo;
		this.costo = costo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Membresia() {
		super();
	}

	
}