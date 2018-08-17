package com.synonyms.app.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synonyms.app.controller.UserController;
import com.synonyms.app.model.User;
import com.synonyms.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMVC;
    @MockBean
    private UserService userService;

    private User user;
    @InjectMocks
    UserController userController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMVC = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User("chandan3706","Chandan", "123456");
    }


    @Test
    public void testLoginUser() throws Exception {
        String userName = "chandan3706";
        String password = "123456";
        Mockito.when(userService.loginUser(userName, password)).thenReturn(user);
        mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    // Parsing String format data into JSON format
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }

}
