package gym.ws.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	 Cliente findByNombre(String nombre);
	 Cliente findFirstByOrderByIdclienteDesc();
	 Cliente findByUsuarioAndContrasena(String usuario, String contrasena); 
	  
}
