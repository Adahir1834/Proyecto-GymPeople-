package gym.ws.norelational;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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


@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class RolController {
	
	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping()
	public Iterable<Rol>obtenerTodos(){
		return rolRepository.findAll();
	}
	@GetMapping("{nombre}")
	public Iterable<Rol> buscarPorNombre(@PathVariable String nombre) {
	    Query query = new Query();
	    query.addCriteria(Criteria.where("nombre").regex("^" + nombre + "$"));
	    List<Rol> recursos = mongoTemplate.find(query, Rol.class);
	    return recursos;
	}
	@PostMapping()
	public String registrar(@RequestBody Rol recurso) {
		rolRepository.save(recurso);
		return "Rol registrado correctamente";
	}

	@PutMapping("{idRol}")
	public String modificar(@RequestBody Rol recurso, @PathVariable String idRol) {
		rolRepository.findById(idRol).get();
		recurso.setId(idRol);
		rolRepository.save(recurso);
		return "Rol actualizado correctamente";
		
	}
	@DeleteMapping("{idRol}")
	public String eliminar(@PathVariable String idRol) {
		rolRepository.findById(idRol).get();
		rolRepository.deleteById(idRol);
		return "Rol eliminado correctamente";
	
	}

}
