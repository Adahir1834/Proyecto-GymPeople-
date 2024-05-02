package gym.ws.empleado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleados")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,

		RequestMethod.DELETE })

public class EmpleadoController {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@GetMapping()
	public @ResponseBody Iterable<Empleado> obtenerTodos() {
		return empleadoRepository.findAll();
	}
	
	@GetMapping("/{idEmpleado}")
	public @ResponseBody ResponseEntity<?> obtenerPorId(@PathVariable Integer idEmpleado) {
		Empleado empleado = new Empleado();
		empleado = empleadoRepository.findById(idEmpleado).get();
		return new ResponseEntity<>(empleado, HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<String> registrar(@Valid @RequestBody Empleado empleado) {
		Optional<Empleado> empleadoExistente = empleadoRepository.findById(empleado.getIdEmpleado());
		if (empleadoExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un empleado con este id");
		}
		empleadoRepository.save(empleado);
		return ResponseEntity.status(HttpStatus.CREATED).body("Empleado registrado correctamente");

	}
	// Modificar un empleado existente
	@PutMapping("/{idEmpleado}")
	public ResponseEntity<String> modificar(@Valid @RequestBody Empleado empleado, @PathVariable Integer idEmpleado) {
		try {
			Optional<Empleado> estudianteOptional = empleadoRepository.findById(idEmpleado);

			if (estudianteOptional.isPresent()) {
				Empleado empleadoExistente = estudianteOptional.get();
				empleadoExistente.setNombre(empleado.getNombre());
				empleadoExistente.setApellidoPaterno(empleado.getApellidoPaterno());
				empleadoExistente.setApellidoMaterno(empleado.getApellidoMaterno());
				empleadoExistente.setIdRol(empleado.getIdRol());
				empleadoRepository.save(empleadoExistente);
				return ResponseEntity.ok("Empleado modificado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Empleado con id " + idEmpleado + " no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el estudiante");
		}
	}

	@DeleteMapping("/{idEmpleado}")
	public ResponseEntity<String> eliminar(@PathVariable Integer idEmpleado) {
		try {
			Optional<Empleado> empleadoOptional = empleadoRepository.findById(idEmpleado);

			if (empleadoOptional.isPresent()) {
				Empleado empleadoExistente = empleadoOptional.get();
				empleadoRepository.delete(empleadoExistente);
				return ResponseEntity.ok("Registro eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el empleado");
		}

	}
}
