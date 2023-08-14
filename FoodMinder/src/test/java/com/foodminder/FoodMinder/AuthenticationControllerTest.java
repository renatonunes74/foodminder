package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.usuario.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationManager authenticationManager;
    @MockBean
    private UsuarioRepository repository;
    @MockBean
    private UserDetailsService userDetailsService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registrar() throws Exception {
        RegisterDTO data = new RegisterDTO("newUsername", "newPassword", UsuarioRole.USER);
        when(repository.findByLogin("newUsername")).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/registrar").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(data)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());
    }
  //  @Test
  //  public void login() throws Exception {
   // }
}
