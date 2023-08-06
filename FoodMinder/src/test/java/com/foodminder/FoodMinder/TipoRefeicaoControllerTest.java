package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.services.TipoRefeicaoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
        TipoRefeicao tipoRefeicao1 = new TipoRefeicao();
        tipoRefeicao1.setId(1);
        tipoRefeicao1.setTipo("Café da manhã");

        TipoRefeicao tipoRefeicao2 = new TipoRefeicao();
        tipoRefeicao2.setId(2);
        tipoRefeicao2.setTipo("Almoço");

        List<TipoRefeicao> tipoRefeicoes = List.of(tipoRefeicao1, tipoRefeicao2);
        when(tipoRefeicaoRepository.findAll()).thenReturn(tipoRefeicoes);
        when(tipoRefeicaoRepository.findById(1)).thenReturn(Optional.of(tipoRefeicao1));
    }
    @AfterEach
    public void down() {tipoRefeicaoRepository.deleteAll();}
    @Test
    @DisplayName("Listar todas os tipos de refeições")
    public void TipoRefeicaoController_GetAllTipoRefeicao_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tipoRefeicao"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tipo").value("Café da manhã"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].tipo").value("Almoço"))
                .andDo(MockMvcResultHandlers.print());
    }
    @ParameterizedTest
    @ValueSource(ints = { 1, 11 })
    @DisplayName("Listar tipo de refeição por ID; 1 para OK, 11 para NOT FOUND")
    public void TipoRefeicaoController_GetById_VerifyStatus(int id) throws Exception {
        if (id == 1) {
            mockMvc.perform(MockMvcRequestBuilders.get("/tipoRefeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.tipo").value("Café da manhã"))
                    .andDo(MockMvcResultHandlers.print());
        } else if (id == 11) {
            mockMvc.perform(MockMvcRequestBuilders.get("/tipoRefeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipo").value("Café da Tarde"))
                .andDo(MockMvcResultHandlers.print());
    }
    @ParameterizedTest
    @ValueSource(ints = { 1, 11 })
    @DisplayName("Deletar tipo de refeição por ID; 1 para OK, 11 para NOT FOUND")
    public void TipoRefeicaoController_DeleteById_VerifyStatus(int id) throws Exception {
        if(id == 1) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/tipoRefeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andDo(MockMvcResultHandlers.print());
        }
        else if (id == 11) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/tipoRefeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }
    }
}