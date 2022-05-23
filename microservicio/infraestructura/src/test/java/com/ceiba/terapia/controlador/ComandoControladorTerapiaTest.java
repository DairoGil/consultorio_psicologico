package com.ceiba.terapia.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.terapia.comando.ComandoTerapia;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorTerapia.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorTerapiaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioTerapia repositorioTerapia;

    @Test
    void iniciarTerapiaExitoso() throws Exception {
        ComandoTerapia comandoTerapia =
                new ComandoTerapiaTestDataBuilder().crearPorDefecto().build();

        MvcResult result = mockMvc.perform(post("/terapia")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(comandoTerapia)))
                .andExpect(status().isCreated()).andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        RespuestaIniciar respuestaIniciar = objectMapper.readValue(jsonResult, RespuestaIniciar.class);

        Terapia terapia = repositorioTerapia.obtener(respuestaIniciar.getValor());

        Assertions.assertEquals(comandoTerapia.getIdPaciente(), terapia.getPaciente().getId());
        Assertions.assertEquals(comandoTerapia.getResumen(), terapia.getResumen());
        Assertions.assertEquals(comandoTerapia.getPeriodicidadMes(), terapia.getPeriodicidadMes());
    }
}
