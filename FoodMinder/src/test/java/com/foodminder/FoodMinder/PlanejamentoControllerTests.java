package com.foodminder.FoodMinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.services.PlanejamentoService;
import org.junit.jupiter.api.*;
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

        Planejamento planejamento = new Planejamento();
        planejamento.setData("2023-08-12");
        planejamento.setTipoRefeicao(tipoRefeicao);
        planejamento.setRefeicao(refeicao);

        planejamentoRepository.save(planejamento);
    }
    @AfterEach
    public void down() {
        planejamentoRepository.deleteAll();
    }
    @Test
    @DisplayName("Lista todos os planejamentos")
    public void PlanejamentoController_GetAllPlanejamento_ReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/planejamento"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
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
                .andDo(MockMvcResultHandlers.print());
    }
}