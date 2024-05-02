package gym.ws.test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

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
import gym.ws.membresia.Membresia;
import gym.ws.membresia.MembresiaRepository;



@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = gym.ws.Application.class)
@TestMethodOrder(OrderAnnotation.class)
public class MembresiaControllerTest {
	@Autowired
    private MockMvc mockMvc;
    @Autowired
    private MembresiaRepository membresiaRepository;
    
    @Autowired
    private ClienteController clientecontroller;
    
    private static int idMembresiaAux;
    @Test
    @Order(1)
    public void obtenerTodosTest() throws Exception {
    	String token = clientecontroller.getJWTToken("Adahir");
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/membresias").header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(greaterThan(0))));
        
            Membresia membresia= membresiaRepository.findFirstByOrderByIdMembresiaDesc();
            idMembresiaAux = membresia.getIdMembresia();
    }
     @Test
	 @Order(2)
	public void registrarTest() throws Exception {
    	String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.post("/membresias").header("Authorization", token)
				.content(JsonUtil.toJson(new Membresia(0, "Mensual", 250, Date.valueOf("2023-10-10"),Date.valueOf("2023-10-10"))))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("Membresia registrada correctamente")));
		Membresia membresia= membresiaRepository.findFirstByOrderByIdMembresiaDesc();
       idMembresiaAux = membresia.getIdMembresia();
	}
	
	@Test
	@Order(3)
	public void obtenerPorIdMembresiaTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.get("/membresias/"+idMembresiaAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.idMembresia", is(idMembresiaAux)));
	} 
	
	@Test
	@Order(4)
	public void actualizarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.put("/membresias/"+idMembresiaAux).header("Authorization", token)
				.content(JsonUtil.toJson(new Membresia(idMembresiaAux, "Semanal", 100, Date.valueOf("2023-08-10"),Date.valueOf("2023-08-17"))))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Membresia modificada correctamente")));
	}
	
	@Test
	@Order(5)
	public void eliminarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.delete("/membresias/"+idMembresiaAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status()
				.isOk())
				.andExpect(content().string(containsString("Membresia eliminada correctamente")));
	}
	
	
	@Test
	@Order(6)
	public void eliminarNotFoundTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.delete("/membresias/"+idMembresiaAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status()
				.isNotFound())
				.andExpect(content().string(containsString("Membresia no encontrada")));
	}

    
}