package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.services.RefeicaoService;
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

import static org.mockito.Mockito.when;

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
        Refeicao refeicao1 = new Refeicao();
        refeicao1.setId(1);
        refeicao1.setNome("Lasanha");
        refeicao1.setReceita("lasanha.md");

        Refeicao refeicao2 = new Refeicao();
        refeicao2.setId(2);
        refeicao2.setNome("Torta de maçã");
        refeicao2.setReceita("torta_de_maçã.md");

        List<Refeicao> refeicoes = List.of(refeicao1, refeicao2);
        when(refeicaoRepository.findAll()).thenReturn(refeicoes);
        when(refeicaoRepository.findById(1)).thenReturn(Optional.of(refeicao1));

    }
    @AfterEach
    public void down() {refeicaoRepository.deleteAll();}
    @Test
    @DisplayName("Listar todas as refeicões")
    public void RefeicaoController_GetAllRefeicao_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/refeicao"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nome").value("Lasanha"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].receita").value("lasanha.md"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nome").value("Torta de maçã"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].receita").value("torta_de_maçã.md"))
                .andDo(MockMvcResultHandlers.print());
    }
    @ParameterizedTest
    @ValueSource(ints = { 1, 11 })
    @DisplayName("Listar refeição por ID; 1 para OK, 11 para NOT FOUND")
    public void RefeicaoController_GetById_VerifyStatus(int id) throws Exception {
        if (id == 1) {
            mockMvc.perform(MockMvcRequestBuilders.get("/refeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Lasanha"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.receita").value("lasanha.md"))
                    .andDo(MockMvcResultHandlers.print());
        } else if (id == 11) {
            mockMvc.perform(MockMvcRequestBuilders.get("/refeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }
    }
    @Test
    @DisplayName("Registrar refeição")
    public void RefeicaoController_RegisterRefeicao_ReturnCreated() throws Exception {
        Refeicao refeicao = Refeicao.builder()
                .nome("Torta de Pêssego")
                .receita("torta_de_pêssego.md")
                .build();
        String refeicaoRequest = objectMapper.writeValueAsString(refeicao);
        mockMvc.perform(MockMvcRequestBuilders.post("/refeicao")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(refeicaoRequest))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Torta de Pêssego"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receita").value("torta_de_pêssego.md"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @ParameterizedTest
    @ValueSource(ints = { 1, 11 })
    @DisplayName("Deletar refeição por ID; 1 para OK, 11 para NOT FOUND")
    public void RefeicaoControleer_DeleteById_VerifyStatus(int id) throws Exception{
        if (id == 1) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/refeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andDo(MockMvcResultHandlers.print());
        } else if (id == 11) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/refeicao/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }
    }
}
