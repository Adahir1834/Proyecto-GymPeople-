package gym.ws.membresia;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MembresiaRepository extends JpaRepository<Membresia, Integer> {

	Membresia findFirstByOrderByIdMembresiaDesc();
}
