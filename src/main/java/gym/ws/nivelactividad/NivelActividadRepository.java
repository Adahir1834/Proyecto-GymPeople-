package gym.ws.nivelactividad;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelActividadRepository extends JpaRepository<NivelActividad, Integer> {

	Optional<NivelActividad> findByNombre(String nombre);

	
}
