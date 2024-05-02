package gym.ws.ejercicio;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;
//
//public interface EjercicioRepository extends CrudRepository<Ejercicio, Integer>{

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer>{

	Ejercicio findFirstByOrderByIdEjercicioDesc();

}
