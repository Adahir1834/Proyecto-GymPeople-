package gym.ws.membresia;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/membresias")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class MembresiaController {

	@Autowired
	private MembresiaRepository membresiaRepository;

	// Obtener todos los estudiantes
	@GetMapping
	public ResponseEntity<Iterable<Membresia>> obtenerTodos() {
		Iterable<Membresia> membresias = membresiaRepository.findAll();
		return ResponseEntity.ok(membresias);
	}

	// Obtener Membresia por número de control
	@GetMapping("/{idMembresia}")
	public ResponseEntity<?> obtenerPorIdMembresia(@PathVariable Integer idMembresia) {
		Membresia membresia = new Membresia();
		membresia = membresiaRepository.findById(idMembresia).get();
		return new ResponseEntity<Membresia>(membresia, HttpStatus.OK);
	}
	// Agregar nueva Membresia
	@PostMapping()
	public ResponseEntity<String> registrar(@Valid @RequestBody Membresia membresia) {

		Optional<Membresia> membresiaExistente = membresiaRepository.findById(membresia.getIdMembresia());

		if (membresiaExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una membresia con este id");
		}

		membresiaRepository.save(membresia);
		return ResponseEntity.status(HttpStatus.CREATED).body("Membresia registrada correctamente");

	}

	// Modificar un estudiante existente
	@PutMapping("/{idMembresia}")
	public ResponseEntity<String> modificar(@Valid @RequestBody Membresia membresia, @PathVariable Integer idMembresia) {
		try {
			Optional<Membresia> membresiaOptional = membresiaRepository.findById(idMembresia);

			if (membresiaOptional.isPresent()) {
				Membresia membresiaExistente = membresiaOptional.get();
				membresiaExistente.setTipo(membresia.getTipo());
				membresiaExistente.setCosto(membresia.getCosto());
				membresiaExistente.setFechaInicio(membresia.getFechaInicio());
				membresiaExistente.setFechaFin(membresia.getFechaFin());
				membresiaRepository.save(membresiaExistente);
				return ResponseEntity.ok("Membresia modificada correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("La membresia " + idMembresia + " no fue encontrada");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar la membresia");
		}
	}

	// Eliminar un estudiante por número de control
	@DeleteMapping("/{idMembresia}")
	public ResponseEntity<String> eliminar(@PathVariable Integer idMembresia) {
		try {
			Optional<Membresia> membresiaOptional = membresiaRepository.findById(idMembresia);

			if (membresiaOptional.isPresent()) {
				Membresia membresiaExistente = membresiaOptional.get();
				membresiaRepository.delete(membresiaExistente);
				return ResponseEntity.ok("Membresia eliminada correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membresia no encontrada");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la membresia");
		}
	}


}
