package gym.ws.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gym.ws.rutina.Rutina;
import gym.ws.rutina.RutinaRepository;

import java.util.Iterator; 

@RestController
@RequestMapping("/rutinasDTO")
public class RutinaControllerDTO {
	@Autowired
	private RutinaRepository rutinaRepository;

	@GetMapping()
	public List<RutinaDTO> obtenerTodos() {
		Iterable<Rutina> rutinas = rutinaRepository.findAll();
		Iterator<Rutina> iteratorRutinas = rutinas.iterator();
		List<RutinaDTO> rutinasDTO = new ArrayList<RutinaDTO>();	
		while (iteratorRutinas.hasNext()) {
			Rutina rutina = iteratorRutinas.next();
			RutinaDTO rutinaDTO = new RutinaDTO();
			rutinaDTO.setNombreRutina(rutina.getNombreRutina());
			rutinaDTO.setIdEjercicio(rutina.getIdEjercicio());
			rutinaDTO.setRepeticiones(rutina.getRepeticiones());
			rutinaDTO.setIdObjetivo(rutina.getIdObjetivo());
			rutinaDTO.setIdNivelAct(rutina.getIdNivelAct());
			rutinasDTO.add(rutinaDTO);
			
			}
		return rutinasDTO;
	}
}

