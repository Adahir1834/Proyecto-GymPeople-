package gym.ws.objetivo;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/objetivo")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,

		RequestMethod.DELETE })
public class ObjetivoController {
	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	@GetMapping()
	public @ResponseBody Iterable<Objetivo> obtenerTodos() {
		return objetivoRepository.findAll();
	}
	
	@GetMapping("/{nombre}")
	public @ResponseBody ResponseEntity<?> obtenerPorId(@PathVariable String nombre) {
		Objetivo objetivo= new Objetivo();
		objetivo = objetivoRepository.findByNombre(nombre).get();
		return new ResponseEntity<>(objetivo, HttpStatus.OK);
	}
	/*@PostMapping()
	public ResponseEntity<String> registrar(@Valid @RequestBody Empleado empleado) {
		Optional<Empleado> empleadoExistente = empleadoRepository.findById(empleado.getIdEmpleado());
		if (empleadoExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un empleado con este id");
		}
		empleadoRepository.save(empleado);
		return ResponseEntity.status(HttpStatus.CREATED).body("Empleado registrado correctamente");

	}
	*/
	// Modificar un empleado existente
	@PutMapping("/{idObjetivo}")
	public ResponseEntity<String> modificar(@Valid @RequestBody Objetivo objetivo, @PathVariable Integer idObjetivo) {
		try {
			Optional<Objetivo> objetivoOptional = objetivoRepository.findById(idObjetivo);

			if (objetivoOptional.isPresent()) {
				Objetivo objetivoExistente = objetivoOptional.get();
				objetivoExistente.setNombre(objetivo.getNombre());
				objetivoRepository.save(objetivoExistente);
				return ResponseEntity.ok("Objetivo modificado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Objetivo con id " + idObjetivo + " no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el objetivo");
		}
	}

/*	@DeleteMapping("/{idObjetivo}")
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

	}*/
}
