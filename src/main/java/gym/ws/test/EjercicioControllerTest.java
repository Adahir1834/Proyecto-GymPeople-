package gym.ws.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import gym.ws.cliente.ClienteController;
import gym.ws.ejercicio.Ejercicio;
import gym.ws.ejercicio.EjercicioRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = gym.ws.Application.class)
@TestMethodOrder(OrderAnnotation.class)
public class EjercicioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EjercicioRepository ejercicioRepository;
	
    @Autowired
    private ClienteController clientecontroller;
    
	
	private static int idAux;
	
	@Test
	@Order(1)
	public void obtenerTodosTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/ejercicio").header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status()
			    .isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));

		Ejercicio ejercicio = ejercicioRepository.findFirstByOrderByIdEjercicioDesc();
		idAux = ejercicio.getIdEjercicio();
	}
	
	@Test
	@Order(2)
	public void registrarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(
				MockMvcRequestBuilders.post("/ejercicio").header("Authorization", token)
				.content(JsonUtil.toJson(new Ejercicio(0, "Ejercicio 1")))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(containsString("Ejercicio registrado correctamente")));
		Ejercicio ejercicio = ejercicioRepository.findFirstByOrderByIdEjercicioDesc();
		idAux = ejercicio.getIdEjercicio();
	}
	
	@Test
	@Order(3)
	public void obtenerPorIdEjercicioTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.get("/ejercicio/" + idAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.idEjercicio", is(idAux)));
	}
	
	@Test
	@Order(4)
	public void actualizarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.put("/ejercicio/" + idAux).header("Authorization", token)
				.content(JsonUtil.toJson(new Ejercicio(idAux, "Ejercicio 2")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Ejercicio modificado correctamente")));
	}
	
//	@Test
//	@Order(5)
//	public void eliminarTest() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.delete("/ejercicio/" + idAux).accept(MediaType.APPLICATION_JSON))
//				.andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Registro eliminado correctamente")));
//	}
//
//	@Test
//	@Order(6)
//	public void eliminarNotFoundTest() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.delete("/ejercicio/" + idAux).accept(MediaType.APPLICATION_JSON))
//				.andDo(print()).andExpect(status().isNotFound())
//				.andExpect(content().string(containsString("Ejercicio no encontrado")));
//	}
}
