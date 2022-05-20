package com.ceiba.sesion.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.paciente.controlador.ComandoControladorPaciente;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorSesion.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorSesionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarSesionesPendientesPorPaciente() throws Exception {
        mockMvc.perform(get("/sesion/{id}", 6l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].fecha", is(LocalDate.of(2022,7,13).toString())))
                .andExpect(jsonPath("$[0].hora", is(8)))
                .andExpect(jsonPath("$[0].estado", is(EstadoSesion.PENDIENTE.toString())));
    }
}
