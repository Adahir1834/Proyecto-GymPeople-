package gym.ws.comentarios;

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
@Table(name = "comentarios")
 
public class Comentarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComentario;
	
	@Column(name = "idCliente")
	@JdbcTypeCode(java.sql.Types.VARCHAR)

	private int idCliente;
	
	@Column(name = "comentario")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@NotBlank(message = "El Comentario es obligatorio")
	private String comentario;
	
	@Column(name = "polaridad")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private String polaridad;
	
	

	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getPolaridad() {
		return polaridad;
	}

	public void setPolaridad(String polaridad) {
		this.polaridad = polaridad;
	}

	public Comentarios(int idComentario, @NotBlank(message = "Id del Cliente es obligatorio") int idCliente,
			@NotBlank(message = "El Comentario es obligatorio") String comentario,
			@NotBlank(message = "La polaridad es obligatoria") String polaridad) {
		super();
		this.idComentario = idComentario;
		this.idCliente = idCliente;
		this.comentario = comentario;
		this.polaridad = polaridad;
	}

	public Comentarios() {
		super();
	}


}
