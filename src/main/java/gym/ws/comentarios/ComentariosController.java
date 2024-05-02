package gym.ws.comentarios;

import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import gym.ws.utility.*;
import jakarta.validation.Valid;
@Controller
@RestController
@RequestMapping("/Comentarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

public class ComentariosController {
   
	@Autowired
	private ComentarioRepository comentarioRepository;
	   @RequestMapping(method = RequestMethod.POST, path = "/registrar")
	    public ResponseEntity<String> registrar(@Valid @RequestBody Comentarios comentario) {
	        try {
	            comentario.setIdComentario(0);
	            SentimentAnalysis.Initialize(); 
	            SentimentAnalysis sa = new SentimentAnalysis();
	            Documents documents = new Documents();

	            documents.add("1", "es", comentario.getComentario());
	         
	            String response = sa.getTheSentiment(documents);
	            String englishPolarity = sa.extractPolarityFromResponse(response);
	            String spanishPolarity = sa.translatePolarityToSpanish(englishPolarity);

	            comentario.setPolaridad(spanishPolarity);
	            comentarioRepository.save(comentario);
	            return ResponseEntity.ok("Comentario registrado correctamente");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error al registrar el comentario: " + e.getMessage());
	        }
	    }

	
}



