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
import gym.ws.empleado.Empleado;
import gym.ws.empleado.EmpleadoRepository;



@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = gym.ws.Application.class)
@TestMethodOrder(OrderAnnotation.class)
public class EmpleadoControllerTest {
	@Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmpleadoRepository empleadoRepository;
	
    @Autowired
    private ClienteController clientecontroller;
    
    private static int idEmpleadoAux;
    
    
  
    @Test
    @Order(1)
    public void obtenerTodosTest() throws Exception {
    	
    	String token = clientecontroller.getJWTToken("Adahir");
        mockMvc.perform(MockMvcRequestBuilders.get("/empleados").header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(greaterThan(0))));
        
            Empleado empleado= empleadoRepository.findFirstByOrderByIdEmpleadoDesc();
            idEmpleadoAux = empleado.getIdEmpleado();
    }
    @Test
	 @Order(2)
	public void registrarTest() throws Exception {
    	String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.post("/empleados").header("Authorization", token)
				.content(JsonUtil.toJson(new Empleado(0, "Carlos", "Moreno", "Severo",2)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("Empleado registrado correctamente")));
		Empleado empleado= empleadoRepository.findFirstByOrderByIdEmpleadoDesc();
       idEmpleadoAux = empleado.getIdEmpleado();
	}
	
	@Test
	@Order(3)
	public void obtenerPorIdEmpleadoTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.get("/empleados/"+idEmpleadoAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.idEmpleado", is(idEmpleadoAux)));
	} 
	
	@Test
	@Order(4)
	public void actualizarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.put("/empleados/"+idEmpleadoAux).header("Authorization", token)
				.content(JsonUtil.toJson(new Empleado(idEmpleadoAux, "Adahir", "Mellll", "Osoooo",1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Empleado modificado correctamente")));
	}
	
	@Test
	@Order(5)
	public void eliminarTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.delete("/empleados/"+idEmpleadoAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status()
				.isOk())
				.andExpect(content().string(containsString("Registro eliminado correctamente")));
	}
	
	
	@Test
	@Order(6)
	public void eliminarNotFoundTest() throws Exception {
		String token = clientecontroller.getJWTToken("Adahir");
		mockMvc.perform(MockMvcRequestBuilders.delete("/empleados/"+idEmpleadoAux).header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status()
				.isNotFound())
				.andExpect(content().string(containsString("Empleado no encontrado")));
	}

    
}
