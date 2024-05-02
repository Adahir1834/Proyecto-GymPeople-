package gym.ws;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.FieldError;

@ControllerAdvice


public class ExceptionHandlerAdvice {

	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> tipoDato(SQLIntegrityConstraintViolationException e) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No es posible completar esta acción debido a restricciones de integridad de datos en la base de datos.");
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> tipoDato(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de dato no valido");
	}
 
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> elementoNoEncontrado(NoSuchElementException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no registrado en la Base de Datos");
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> elementoIncorrecto(MethodArgumentTypeMismatchException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Este recursos solo debe incluir datos de tipo numérico");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ModelAndView elementoObligatorio(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("errors", errors);
		mav.setViewName("methodArgumentNotValid");
		return mav;
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> elementoIncorrecto2(HttpRequestMethodNotSupportedException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No es necesario agregar nada");
	}

	
}
