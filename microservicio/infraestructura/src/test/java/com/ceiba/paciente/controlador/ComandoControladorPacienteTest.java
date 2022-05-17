package com.ceiba.paciente.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorPaciente.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorPacienteTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioPaciente repositorioPaciente;

    @Test
    void registrarPacienteExitoso() throws Exception{
        ComandoPaciente comandoPaciente =
                new ComandoPacienteTestDataBuilder().crearPorDefecto().build();
        mockMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoPaciente)))
                .andExpect(status().isCreated());
    }
}