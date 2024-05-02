package gym.ws.objetivo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {

	Optional<Objetivo> findByNombre(String nombre);

	
}
