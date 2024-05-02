package gym.ws.cliente;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import gym.ws.comentarios.ComentarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

public class ClienteController {


	@Autowired
	private ClienteRepository clienteRepository;
	private String secretKey = "mySecretKeyItsteziutlanWebServices";

//Obtener lista
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> obtenerTodos() {
		Iterable<Cliente> clientes = clienteRepository.findAll();
		return ResponseEntity.ok(clientes);
	}

//Obtener por Identificador

	@GetMapping("/{idCliente}")
	public ResponseEntity<?> obtenerPorIdentificador(@PathVariable Integer idCliente) {
		Cliente cliente = new Cliente();
		cliente = clienteRepository.findById(idCliente).get();
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

//Obtener Por Nombre

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
		Cliente cliente = clienteRepository.findByNombre(nombre);

		if (cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} else {
			String mensajeError = "No se encontró ningún cliente con el nombre especificado.";
			return new ResponseEntity<String>(mensajeError, HttpStatus.NOT_FOUND);
		}
	}

	// Crear nuevo Cliente
	@PostMapping()
	public ResponseEntity<String> registrar(@Valid @RequestBody Cliente cliente) {

		Optional<Cliente> clienteExistente = clienteRepository.findById(cliente.getIdcliente());

		if (clienteExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un cliente con este identificador");
		}
		clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cliente registrado correctamente");

	}

	// Actualizar Cliente

	@PutMapping("/{idCliente}")
	public ResponseEntity<String> modificar(@Valid @RequestBody Cliente cliente, @PathVariable Integer idCliente) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);

		if (clienteOptional.isPresent()) {
			Cliente clienteExistente = clienteOptional.get();
			clienteExistente.setUsuario(cliente.getUsuario());
			clienteExistente.setContrasena(cliente.getContrasena());
			clienteExistente.setNombre(cliente.getNombre());
			clienteExistente.setApellidopaterno(cliente.getApellidopaterno());
			clienteExistente.setApellidomaterno(cliente.getApellidomaterno());
			clienteExistente.setIdMembresia(cliente.getIdMembresia());
			clienteExistente.setRol(cliente.getRol());
			clienteRepository.save(clienteExistente);
			return ResponseEntity.ok("Cliente actualizado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente con ID " + idCliente + " no encontrado");
		}
	}

	// Eliminar un cliente por ID de cliente
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<String> eliminar(@PathVariable Integer idCliente) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);

		if (clienteOptional.isPresent()) {
			Cliente clienteExistente = clienteOptional.get();
			clienteRepository.delete(clienteExistente);
			return ResponseEntity.ok("Cliente eliminado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
		}

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		Cliente cliente = clienteRepository.findByUsuarioAndContrasena(username, password);
		if (cliente != null) {
			String token = getJWTToken(username);
			return ResponseEntity.ok("Bearer " + token);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
		}
	}
  
	public String getJWTToken(String username) {
		// String secretKey = "mySecretKeyItsteziutlanWebServices";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("itstJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000000)).signWith(getSigningKey()).compact();

		return "Bearer " + token;
	}

	private Key getSigningKey() {
		byte[] keyBytes = this.secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
