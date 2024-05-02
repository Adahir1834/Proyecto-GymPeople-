package gym.ws.empleado;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	Empleado findFirstByOrderByIdEmpleadoDesc();
	
	
}