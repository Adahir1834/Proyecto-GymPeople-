package gym.ws.dto;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gym.ws.rutina.Rutina;
import gym.ws.rutina.RutinaRepository;
import jakarta.transaction.Transactional;

@Service
public class RutinaService {
	@Autowired
	private RutinaRepository repo;

	public List<Rutina> findAll() {
		return repo.findAll();
	}
	@Transactional
	public String save(Rutina rutina) {
		Rutina auxRutina = repo.save(rutina);
		System.out.println(auxRutina.getIdRutina());
		if (auxRutina.getIdRutina() != 0) {
			int a = 1 / 0;
			System.out.println("El valor de a es : " + a);
		}
		if (auxRutina != null) {
			return "El usuario se registró correctamente";
		}
		return "El usuario no se registró";
	}

	public Rutina findById(Integer idRutina) {
		return repo.findById(idRutina).get();
	}

	public void deleteById(Integer idRutina) {
		repo.deleteById(idRutina);
	}

}
