package gym.ws.rutina;

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
@RequestMapping("/rutina")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class RutinaController {

	@Autowired
	private RutinaRepository rutinaRepository;
	
	// Obtener todas las rutinas
		@GetMapping
		public ResponseEntity<Iterable<Rutina>> obtenerTodos() {
			Iterable<Rutina> rutina = rutinaRepository.findAll();
			return ResponseEntity.ok(rutina);
		}
		
		// Obtener rutina por id
		@GetMapping("/{idRutina}")
		public ResponseEntity<?> obtenerPorIdRutina(@PathVariable Integer idRutina) {
			Rutina rutina = new Rutina();
			rutina = rutinaRepository.findById(idRutina).get();
			return new ResponseEntity<Rutina>(rutina, HttpStatus.OK);
		}
		
		// Agregar nueva rutina
		@PostMapping()
		public ResponseEntity<String> registrar(@Valid @RequestBody Rutina rutina) {

			Optional<Rutina> rutinaExistente = rutinaRepository.findById(rutina.getIdRutina());

			if (rutinaExistente.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una rutina con este id");
			}

			rutinaRepository.save(rutina);
			return ResponseEntity.status(HttpStatus.CREATED).body("Rutina registrada correctamente");

		}
		
		// Modificar una rutina existente
		@PutMapping("/{idRutina}")
		public ResponseEntity<String> modificar(@Valid @RequestBody Rutina rutina, @PathVariable Integer idRutina) {
			try {
				Optional<Rutina> rutinaOptional = rutinaRepository.findById(idRutina);

				if (rutinaOptional.isPresent()) {
					Rutina rutinaExistente = rutinaOptional.get();
					rutinaExistente.setNombreRutina(rutina.getNombreRutina());
					rutinaExistente.setRepeticiones(rutina.getRepeticiones());
					rutinaExistente.setIdEjercicio(rutina.getIdEjercicio());
					rutinaExistente.setIdObjetivo(rutina.getIdObjetivo());
					rutinaExistente.setIdNivelAct(rutina.getIdNivelAct());
					rutinaRepository.save(rutinaExistente);
					return ResponseEntity.ok("Rutina modificada correctamente");
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("Rutina con el id " + idRutina + " no encontrado");
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar la rutina");
			}
		}
		
		// Eliminar una rutina por id
		@DeleteMapping("/{idRutina}")
		public ResponseEntity<String> eliminar(@PathVariable Integer idRutina) {
			try {
				Optional<Rutina> rutinaOptional = rutinaRepository.findById(idRutina);

				if (rutinaOptional.isPresent()) {
					Rutina rutinaExistente = rutinaOptional.get();
					rutinaRepository.delete(rutinaExistente);
					return ResponseEntity.ok("Registro eliminado correctamente");
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rutina no encontrada");
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la rutina");
			}
		}
}
