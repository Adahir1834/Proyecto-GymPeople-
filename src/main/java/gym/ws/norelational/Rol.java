package gym.ws.norelational;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import io.swagger.v3.oas.annotations.Hidden;
@Document(collection="rol")

public class Rol {

	@MongoId(targetType=FieldType.OBJECT_ID)
	@Hidden
	private String id;
	private String nombre;
	
	
	public Rol() {
		super();
	}


	public Rol(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
