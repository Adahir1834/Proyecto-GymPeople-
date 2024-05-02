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

import gym.ws.cliente.Cliente;
import gym.ws.cliente.ClienteController;
import gym.ws.cliente.ClienteRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = gym.ws.Application.class)
@TestMethodOrder(OrderAnnotation.class)
public class ClienteControllerTest {


    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteController clientecontroller;
    
 
    private static int identificadorAux;
    
    
    @Test
    @Order(1)
    public void obtenerTodosTest() throws Exception {
    	String token = clientecontroller.getJWTToken("Adahir");
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente").header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));

        Cliente cliente = clienteRepository.findFirstByOrderByIdclienteDesc();
        identificadorAux = cliente.getIdcliente();
    }
    
    
	 @Test 
	 @Order(2)
	public void registrarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.post("/cliente").header("Authorization", token)
				.content(JsonUtil.toJson(new Cliente(0, "Adahir", "12345", "Adahir","Melchor","Osorio",1,"",1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("Cliente registrado correctamente")));
		Cliente cliente = clienteRepository.findFirstByOrderByIdclienteDesc();
		identificadorAux = cliente.getIdcliente();
	}
	 
		@Test
		@Order(3)
		public void obtenerPorNumeroDeControlTest() throws Exception {
			String token = clientecontroller.getJWTToken("Adahir");
			mockMvc.perform(MockMvcRequestBuilders.get("/cliente/"+identificadorAux).header("Authorization", token)
					.accept(MediaType.APPLICATION_JSON))
			        .andDo(print())
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.idcliente", is(identificadorAux)));
		} 
		
		@Test
		@Order(4)
		public void actualizarTest() throws Exception {
			String token = clientecontroller.getJWTToken("Adahir");
			mockMvc.perform(MockMvcRequestBuilders.put("/cliente/"+identificadorAux).header("Authorization", token)
					.content(JsonUtil.toJson(new Cliente(identificadorAux, "Adahir", "12345", "Adahir","Melchor","Osorio",1,"",1)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			         .andExpect(status().isOk())
					.andExpect(content().string(containsString("Cliente actualizado correctamente")));
		}

		
		@Test
		@Order(5)
		public void eliminarTest() throws Exception {
			String token = clientecontroller.getJWTToken("Adahir");
			mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/"+identificadorAux).header("Authorization", token)
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status()
					.isOk())
					.andExpect(content().string(containsString("Cliente eliminado correctamente")));
		}
    
		
		@Test
		@Order(6)
		public void eliminarNotFoundTest() throws Exception {
			String token = clientecontroller.getJWTToken("Adahir");
			mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/"+identificadorAux).header("Authorization", token)
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status()
					.isNotFound())
					.andExpect(content().string(containsString("Cliente no encontrado")));
		}
}
