package ie.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLoginSuccess() throws Exception {

        MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("username", "roryegan");
        paraMap.add("password", "password");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/user")
                .params(paraMap);

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
    }

    @Test
    public void testLoginFailure() throws Exception {

        MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("username", "notauser");
        paraMap.add("password", "password");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/user")
                .params(paraMap);

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"));
    }

    @Test
    public void testRegisterConflict() throws Exception {

        MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("username", "integrationuser");
        paraMap.add("password", "password");
        paraMap.add("userTypeId", "1");
        paraMap.add("email", "integrationuser@gmail.com");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user")
                .params(paraMap);

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("409"));
    }
}
