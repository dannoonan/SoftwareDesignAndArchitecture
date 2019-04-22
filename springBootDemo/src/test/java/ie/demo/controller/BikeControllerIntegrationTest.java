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
public class BikeControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testCreateBikeSuccess() throws Exception {

        MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("bikeType", "1");
        paraMap.add("nodeId", "1");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bike")
                .params(paraMap);

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
    }

    @Test
    public void testCreateBikeBadRequest() throws Exception {

        MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.add("bikeType", "1");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bike")
                .params(paraMap);

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetNodesSuccess() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bike");

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
    }

    @Test
    public void testGetNodesBadRequest() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bike");

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetBikesByNodeSuccess() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/node/1");

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
    }

    @Test
    public void testGetBikesByNodeNodeNotFound() throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/bike/12345");

        this.mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
