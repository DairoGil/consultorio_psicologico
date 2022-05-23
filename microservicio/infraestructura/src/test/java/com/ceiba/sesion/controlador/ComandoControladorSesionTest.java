package com.ceiba.sesion.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.sesion.comando.ComandoSesion;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import com.ceiba.sesion.puerto.repositorio.RepositorioSesion;
import com.ceiba.terapia.controlador.RespuestaIniciar;
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
@WebMvcTest(ComandoControladorSesion.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorSesionTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioSesion repositorioSesion;

    @Autowired
    private DaoSesion daoSesion;

    @Test
    void AgendarExitoso() throws Exception {
        ComandoSesion comandoSesion =
                new ComandoSesionTestDataBuilder().crearPorDefecto().build();

        MvcResult result = mockMvc.perform(post("/sesion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSesion)))
                .andExpect(status().isCreated()).andReturn();

        String jsonResult = result.getResponse().getContentAsString();
        RespuestaAgendar respuestaAgendar = objectMapper.readValue(jsonResult, RespuestaAgendar.class);

        ResumenSesionDTO resumenSesionDTO = daoSesion.obtener(respuestaAgendar.getValor());

        Assertions.assertEquals(comandoSesion.getFecha(), resumenSesionDTO.getFecha());
        Assertions.assertEquals(comandoSesion.getHora(), resumenSesionDTO.getHora());
        Assertions.assertEquals(EstadoSesion.PENDIENTE, resumenSesionDTO.getEstado());
    }
}
