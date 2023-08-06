package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.services.PlanejamentoService;
import org.junit.jupiter.api.*;
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
public class PlanejamentoControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlanejamentoService planejamentoService;
    @Autowired
    private ObjectMapper objectMapper;
    private Planejamento planejamento;
    @MockBean
    private PlanejamentoRepository planejamentoRepository;
    private Refeicao refeicao;
    private TipoRefeicao tipoRefeicao;

    @BeforeEach
    public void setup() {
        refeicao = new Refeicao();
        tipoRefeicao = new TipoRefeicao();

        refeicao.setId(3);
        tipoRefeicao.setId(3);

        Planejamento planejamento1 = new Planejamento();
        planejamento1.setId(1);
        planejamento1.setData("2023-08-12");
        planejamento1.setTipoRefeicao(tipoRefeicao);
        planejamento1.setRefeicao(refeicao);

        Planejamento planejamento2 = new Planejamento();
        planejamento1.setId(2);
        planejamento2.setData("2023-08-24");
        planejamento2.setTipoRefeicao(tipoRefeicao);
        planejamento2.setRefeicao(refeicao);

        List<Planejamento> planejamentos = List.of(planejamento1, planejamento2);
        when(planejamentoRepository.findAll()).thenReturn(planejamentos);
        when(planejamentoRepository.findById(1)).thenReturn(Optional.of(planejamento1));
    }
    @AfterEach
    public void down() {planejamentoRepository.deleteAll();}
    @Test
    @DisplayName("Listar todos os planejamentos")
    public void PlanejamentoController_GetAllPlanejamento_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/planejamento"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].data").value("2023-08-12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].refeicao.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tipoRefeicao.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].data").value("2023-08-24"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].refeicao.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].tipoRefeicao.id").value(3))
                .andDo(MockMvcResultHandlers.print());
    }
    @ParameterizedTest
    @ValueSource(ints = { 1, 11 })
    @DisplayName("Listar planejamento por ID; 1 para OK, 11 para NOT FOUND")
    public void PlanejamentoController_GetById_VerifyStatus(int id) throws Exception {
        if (id == 1) {
            mockMvc.perform(MockMvcRequestBuilders.get("/planejamento/" + id))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("2023-08-12"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.refeicao.id").value(3))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.tipoRefeicao.id").value(3))
                    .andDo(MockMvcResultHandlers.print());
        } else if (id == 11) {
            mockMvc.perform(MockMvcRequestBuilders.get("/planejamento/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }
    }

    @Test
    @DisplayName("Registrar planejamento")
    public void PlanejamentoController_RegisterPlanejamento_ReturnCreated() throws Exception {
        Planejamento planejamento = Planejamento.builder()
                .data("2023-02-11")
                .tipoRefeicao(tipoRefeicao)
                .refeicao(refeicao)
                .build();
        String planejamentoRequest = objectMapper.writeValueAsString(planejamento);
        mockMvc.perform(MockMvcRequestBuilders.post("/planejamento")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(planejamentoRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("2023-02-11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refeicao.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoRefeicao.id").value(3))
                .andDo(MockMvcResultHandlers.print());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 11 })
    @DisplayName("Deletar planejamento por ID; 1 para OK, 11 para NOT FOUND")
    public void PlanejamentoController_DeleteById_VerifyStatus(int id) throws Exception {
        if(id == 1) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/planejamento/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andDo(MockMvcResultHandlers.print());
        }
        else if (id == 11) {
            mockMvc.perform(MockMvcRequestBuilders.delete("/planejamento/" + id))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }
    }
}
