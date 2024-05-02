package gym.ws.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gym.ws.cliente.Cliente;
import gym.ws.cliente.ClienteRepository;

import jakarta.transaction.Transactional;


@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public String save(Cliente cliente) {
		Cliente auxCliente= repo.save(cliente);
		System.out.println(auxCliente.getIdcliente());
		if (auxCliente.getIdcliente()!= 0) {
			int a = 1 / 0;
			System.out.println("El valor de a es : " + a);
		}
		if (auxCliente != null) {
			return "El cliente se registró correctamente";
		}
		return "El cliente no se registró";
	}

	public Cliente findById(Integer id) {
		return repo.findById(id).get();
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
