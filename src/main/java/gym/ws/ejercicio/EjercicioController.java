package gym.ws.ejercicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

@Controller
@RestController
@RequestMapping("/ejercicio")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class EjercicioController {

	@Autowired
	private EjercicioRepository ejercicioRepository;

	// Obtener todos los estudiantes
	@GetMapping
	public ResponseEntity<Iterable<Ejercicio>> obtenerTodos() {
		Iterable<Ejercicio> ejercicio = ejercicioRepository.findAll();
		return ResponseEntity.ok(ejercicio);
	}

	// Obtener estudiante por número de control
	@GetMapping("/{idEjercicio}")
	public ResponseEntity<?> obtenerPorIdEjercicio(@PathVariable Integer idEjercicio) {
		Ejercicio ejercicio = new Ejercicio();
		ejercicio = ejercicioRepository.findById(idEjercicio).get();
		return new ResponseEntity<Ejercicio>(ejercicio, HttpStatus.OK);
	}

	// Agregar nuevo estudiante
	@PostMapping()
	public ResponseEntity<String> registrar(@Valid @RequestBody Ejercicio ejercicio) {

		Optional<Ejercicio> ejercicioExistente = ejercicioRepository.findById(ejercicio.getIdEjercicio());

		if (ejercicioExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un ejercicio con este id");
		}

		ejercicioRepository.save(ejercicio);
		return ResponseEntity.status(HttpStatus.CREATED).body("Ejercicio registrado correctamente");

	}

	// Modificar un estudiante existente
	@PutMapping("/{idEjercicio}")
	public ResponseEntity<String> modificar(@Valid @RequestBody Ejercicio ejercicio,
			@PathVariable Integer idEjercicio) {
		try {
			Optional<Ejercicio> ejercicioOptional = ejercicioRepository.findById(idEjercicio);

			if (ejercicioOptional.isPresent()) {
				Ejercicio ejercicioExistente = ejercicioOptional.get();
				ejercicioExistente.setNombreEjercicio(ejercicio.getNombreEjercicio());
				ejercicioRepository.save(ejercicioExistente);
				return ResponseEntity.ok("Ejercicio modificado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Ejercicio con id " + idEjercicio + " no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el ejercicio");
		}
	}

//	// Eliminar un estudiante por número de control
//	@DeleteMapping("/{idEjercicio}")
//	public ResponseEntity<String> eliminar(@PathVariable Integer idEjercicio) {
//		try {
//			Optional<Ejercicio> ejercicioOptional = ejercicioRepository.findById(idEjercicio);
//
//			if (ejercicioOptional.isPresent()) {
//				Ejercicio ejercicioExistente = ejercicioOptional.get();
//				ejercicioRepository.delete(ejercicioExistente);
//				return ResponseEntity.ok("Registro eliminado correctamente");
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ejercicio no encontrado");
//			}
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el ejercicio");
//		}
//	}

}
