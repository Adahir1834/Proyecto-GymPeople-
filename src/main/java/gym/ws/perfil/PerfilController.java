package gym.ws.perfil;


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
@RequestMapping("/perfil")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,

		RequestMethod.DELETE })
public class PerfilController {
	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping()
	public @ResponseBody Iterable<Perfil> obtenerTodos() {
		return perfilRepository.findAll();
	}
	
	@GetMapping("/{idPerfil}")
	public @ResponseBody ResponseEntity<?> obtenerPorId(@PathVariable Integer idPerfil) {
		Perfil perfil= new Perfil();
		perfil = perfilRepository.findById(idPerfil).get();
		return new ResponseEntity<>(perfil, HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<String> registrar(@Valid @RequestBody Perfil perfil) {
		Optional<Perfil> perfilExistente = perfilRepository.findById(perfil.getIdPerfil());
		if (perfilExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un perfil con este id");
		}
		perfilRepository.save(perfil);
		return ResponseEntity.status(HttpStatus.CREATED).body("Perfil registrado correctamente");

	}
	// Modificar un empleado existente
	@PutMapping("/{idPerfil}")
	public ResponseEntity<String> modificar(@Valid @RequestBody Perfil perfil, @PathVariable Integer idPerfil) {
		try {
			Optional<Perfil> perfilOptional = perfilRepository.findById(idPerfil);

			if (perfilOptional.isPresent()) {
				Perfil perfilExistente = perfilOptional.get();
				perfilExistente.setSexo(perfil.getSexo());
				perfilExistente.setIdObjetivo(perfil.getIdObjetivo());
				perfilExistente.setIdNivelAct(perfil.getIdNivelAct());
				perfilExistente.setSexo(perfil.getSexo());
				
				perfilRepository.save(perfilExistente);
				return ResponseEntity.ok("Perfil modificado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Perfil con id " + idPerfil + " no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el perfil");
		}
	}

	@DeleteMapping("/{idPerfil}")
	public ResponseEntity<String> eliminar(@PathVariable Integer idPerfil) {
		try {
			Optional<Perfil> perfilOptional = perfilRepository.findById(idPerfil);

			if (perfilOptional.isPresent()) {
				Perfil perfilExistente = perfilOptional.get();
				perfilRepository.delete(perfilExistente);
				return ResponseEntity.ok("Perfil eliminado correctamente");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil no encontrado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el empleado");
		}

	}
}
