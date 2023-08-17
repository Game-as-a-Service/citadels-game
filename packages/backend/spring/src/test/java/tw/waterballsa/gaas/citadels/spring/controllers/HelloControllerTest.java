package tw.waterballsa.gaas.citadels.spring.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;

public class HelloControllerTest extends CitadelsSpringBootTest {

    @Test
    public void TestHelloEndPoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("hello"));
    }

}
