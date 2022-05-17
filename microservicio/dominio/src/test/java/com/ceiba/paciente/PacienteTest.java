package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PacienteTest {

    @Test
    void deberiaCrearPacienteExitoso(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conId(1l)
                .conNombre("Paciente 1")
                .conFechaNacimiento(LocalDate.of(1996,7,23))
                .conTelefono("3120144527")
                .crear();

        Assertions.assertEquals(1l, paciente.getId());
        Assertions.assertEquals("Paciente 1", paciente.getNombre());
        Assertions.assertEquals(TipoPaciente.VALORACION, paciente.getTipoPaciente());
        Assertions.assertEquals(LocalDate.of(1996,7,23), paciente.getFechaNacimiento());
        Assertions.assertEquals("3120144527", paciente.getTelefono());
    }

    @Test
    void pacienteSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder().build(),
                ExcepcionValorObligatorio.class,
                "Se requiere la identificación del paciente");
    }

    @Test
    void pacienteSinNombreDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conId(1l)
                        .build(),
                ExcepcionValorObligatorio.class,
                "Se requiere el nombre del paciente");
    }

    @Test
    void pacienteSinFechaNacimientoDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conId(1l)
                        .conNombre("Paciente 1")
                        .build(),
                ExcepcionValorObligatorio.class,
                "Se requiere la fecha de nacimiento del paciente");
    }

    @Test
    void deberiaResponderEsPacienteValoracionCorrectamente(){
        Paciente paciente = new PacienteTestDataBuilder()
                        .conId(1l)
                        .conNombre("Paciente 1")
                        .conFechaNacimiento(LocalDate.of(1996,7,23))
                        .crear();
        Assertions.assertTrue(paciente.esTipoValoracion());
        Assertions.assertFalse(paciente.esTipoAsesoria());
        Assertions.assertFalse(paciente.esTipoTerapia());
    }

    @Test
    void deberiaResponderEsPacienteAsesoriaCorrectamente(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conId(1l)
                .conNombre("Paciente 1")
                .conFechaNacimiento(LocalDate.of(1996,7,23))
                .conTipoPaciente(TipoPaciente.ASESORIA)
                .build();
        Assertions.assertTrue(paciente.esTipoAsesoria());
        Assertions.assertFalse(paciente.esTipoValoracion());
        Assertions.assertFalse(paciente.esTipoTerapia());
    }

    @Test
    void deberiaResponderEsPacienteTerapiaCorrectamente(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conId(1l)
                .conNombre("Paciente 1")
                .conFechaNacimiento(LocalDate.of(1996,7,23))
                .conTipoPaciente(TipoPaciente.TERAPIA)
                .build();
        Assertions.assertTrue(paciente.esTipoTerapia());
        Assertions.assertFalse(paciente.esTipoValoracion());
        Assertions.assertFalse(paciente.esTipoAsesoria());
    }
}
