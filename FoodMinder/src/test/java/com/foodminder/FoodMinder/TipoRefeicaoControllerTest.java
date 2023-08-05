package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.services.TipoRefeicaoService;
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
public class TipoRefeicaoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;
    @Autowired
    private ObjectMapper objectMapper;
    private TipoRefeicao tipoRefeicao;
    @MockBean
    private TipoRefeicaoRepository tipoRefeicaoRepository;
    @BeforeEach
    public void setup() {
        tipoRefeicao = new TipoRefeicao();
        tipoRefeicao.setTipo("Café da manhã");
        tipoRefeicaoRepository.save(tipoRefeicao);
    }
    @AfterEach
    public void down() {
        tipoRefeicaoRepository.deleteAll();
    }
    @Test
    @DisplayName("Listar todas os tipos de refeições")
    public void TipoRefeicaoController_GetAllTipoRefeicao_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tipoRefeicao"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("Registrar tipo de refeição")
    public void TipoRefeicaoController_RegisterTipoRefeicao_ReturnCreated() throws Exception {
        TipoRefeicao tipoRefeicao = TipoRefeicao.builder()
                .tipo("Café da Tarde")
                .build();
        String tipoRefeicaoRequest = objectMapper.writeValueAsString(tipoRefeicao);
        mockMvc.perform(MockMvcRequestBuilders.post("/tipoRefeicao")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(tipoRefeicaoRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
}