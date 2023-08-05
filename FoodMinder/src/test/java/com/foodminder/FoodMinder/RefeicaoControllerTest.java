package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.services.RefeicaoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RefeicaoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RefeicaoService refeicaoService;
    @Autowired
    private ObjectMapper objectMapper;
    private Refeicao refeicao;
    @MockBean
    private RefeicaoRepository refeicaoRepository;

    @BeforeEach
    public void setup() {
        Refeicao refeicao = new Refeicao();
        refeicao.setNome("Lasanha");
        refeicao.setReceita("lasanha.md");
        refeicaoRepository.save(refeicao);
    }
    @AfterEach
    public void down() {refeicaoRepository.deleteAll();}
    @Test
    @DisplayName("Listar todas as refeicões")
    public void RefeicaoController_GetAllRefeicao_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/refeicao"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("Registrar refeição")
    public void RefeicaoController_RegisterRefeicao_ReturnCreated() throws Exception {
        Refeicao refeicao = Refeicao.builder()
                .nome("Torta de maçã")
                .receita("torta_de_maçã.md")
                .build();
        String refeicaoRequest = objectMapper.writeValueAsString(refeicao);
        mockMvc.perform(MockMvcRequestBuilders.post("/refeicao")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(refeicaoRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
}
