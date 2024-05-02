package gym.ws.nivelactividad;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/nivelActividad")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,

		RequestMethod.DELETE })

public class NivelActividadController {
	@Autowired
	private NivelActividadRepository nivelActividadRepository;
	
	@GetMapping()
	public @ResponseBody Iterable<NivelActividad> obtenerTodos() {
		return nivelActividadRepository.findAll();
	}
	
	@GetMapping("/{nombre}")
	public @ResponseBody ResponseEntity<?> obtenerPorId(@PathVariable String nombre) {
		NivelActividad nivel= new NivelActividad();
		nivel = nivelActividadRepository.findByNombre(nombre).get();
		return new ResponseEntity<>(nivel, HttpStatus.OK);
	}

	// Modificar un empleado existente
	@PutMapping("/{idNivelAct}")
	public ResponseEntity<String> modificar(@Valid @RequestBody NivelActividad nivel, @PathVariable Integer idNivelAct) {
		try {
			Optional<NivelActividad> nivelOptional = nivelActividadRepository.findById(idNivelAct);

			if (nivelOptional.isPresent()) {
				NivelActividad nivelExistente = nivelOptional.get();
				nivelExistente.setNombre(nivel.getNombre());
				nivelActividadRepository.save(nivelExistente);
				return ResponseEntity.ok("Nivel modificado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Nivel con id " + idNivelAct + " no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el nivel");
		}
	}

}
