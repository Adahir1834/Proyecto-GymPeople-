package gym.ws.perfil;

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
@Table(name = "Perfil")
public class Perfil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Type(type="text")
	@Column(name = "idPerfil") // cambiar el nombre su esta diferente en la base de datos
	@Hidden
	private int idPerfil;

	@JdbcTypeCode(java.sql.Types.CHAR)
	@NotBlank(message = "Campo sexo es obligatorio")
	private String sexo;

	@JdbcTypeCode(java.sql.Types.INTEGER)
	private int idObjetivo;

	@JdbcTypeCode(java.sql.Types.INTEGER)
	private int idNivelAct;

	public Perfil() {
		super();
	}

	public Perfil(int idPerfil, @NotBlank(message = "Campo sexo es obligatorio") String sexo, int idObjetivo,
			int idNivelAct) {
		super();
		this.idPerfil = idPerfil;
		this.sexo = sexo;
		this.idObjetivo = idObjetivo;
		this.idNivelAct = idNivelAct;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
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
