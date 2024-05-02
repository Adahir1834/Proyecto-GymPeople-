package gym.ws.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gym.ws.cliente.Cliente;
import gym.ws.cliente.ClienteRepository;

@RestController
@RequestMapping("/clientesDTO")
public class ClienteControllerDTO {
	@Autowired
	private ClienteRepository clienteRepository;
	@GetMapping()
	public List<ClienteDTO> obtenerTodos() {
		Iterable<Cliente> clientes= clienteRepository.findAll();
		Iterator<Cliente> iteratorClientes = clientes.iterator();
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();	
		while (iteratorClientes.hasNext()) {
			Cliente cliente = iteratorClientes.next();
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdcliente(cliente.getIdcliente());
			clienteDTO.setUsuario(cliente.getUsuario());
			clienteDTO.setContrasena(cliente.getContrasena());
			clienteDTO.setNombre(cliente.getNombre());
			clienteDTO.setApellidopaterno(cliente.getApellidopaterno());
			clienteDTO.setApellidomaterno(cliente.getApellidomaterno());
			clienteDTO.setIdMembresia(cliente.getIdMembresia());
			clienteDTO.setRol(cliente.getRol());
			clientesDTO.add(clienteDTO);
			}
		return clientesDTO;
	}
}
