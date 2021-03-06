package com.ceiba.paciente.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        MvcResult result = mockMvc.perform(post("/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoPaciente)))
                .andExpect(status().isCreated()).andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        RespuestaRegistrar respuestaRegistrar = objectMapper.readValue(jsonResult, RespuestaRegistrar.class);

        Paciente pacienteCreado = repositorioPaciente.obtener(respuestaRegistrar.getValor());

        Assertions.assertEquals(3l, pacienteCreado.getId());
        Assertions.assertEquals(comandoPaciente.getNombre(), pacienteCreado.getNombre());
        Assertions.assertEquals(comandoPaciente.getFechaNacimiento(),pacienteCreado.getFechaNacimiento());
        Assertions.assertEquals(comandoPaciente.getTelefono(), pacienteCreado.getTelefono());
        Assertions.assertEquals(TipoPaciente.VALORACION, pacienteCreado.getTipoPaciente());
        Assertions.assertEquals(0, pacienteCreado.getSesionesAsesoria());
    }

    @Test
    void asesorarPacienteExitoso() throws Exception {

        mockMvc.perform(post("/paciente/asesorar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Paciente paciente = repositorioPaciente.obtener(1l);

        Assertions.assertTrue(paciente.esTipoAsesoria());
    }
}