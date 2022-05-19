package com.ceiba.terapia;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.terapia.entidad.EstadoTerapia;
import com.ceiba.terapia.entidad.Terapia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TerapiaTest {

    @Test
    void deberiaIniciarTerapiaExitoso() {
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .iniciar();

        Assertions.assertEquals(2l, terapia.getId());
        Assertions.assertEquals(1l, terapia.getPaciente().getId());
        Assertions.assertEquals("Razones por las que el paciente ha acudido a la consulta.", terapia.getResumen());
        Assertions.assertEquals(3, terapia.getPeriodicidadMes());
        Assertions.assertEquals(EstadoTerapia.ACTIVA, terapia.getEstado());
    }

    @Test
    void terapiaSinPacienteDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new TerapiaTestDataBuilder().build(),
                ExcepcionValorObligatorio.class,
                "Se requiere el paciente para asignar la terapia");
    }

    @Test
    void terapiaSinResumenDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new TerapiaTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .build(),
                ExcepcionValorObligatorio.class,
                "Se requiere el resumen de la terapia");
    }

    @Test
    void terapiaSinPeriodicidadMesDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new TerapiaTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .conResumen("Razones por las que el paciente ha acudido a la consulta.")
                        .build(),
                ExcepcionValorObligatorio.class,
                "Se requiere el periodo por mes de la terapia");
    }

    @Test
    void deberiaResponderEsTerapiaActivaCorrectamente(){
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .conEstadoTerapia(EstadoTerapia.ACTIVA)
                .build();
        Assertions.assertTrue(terapia.esActiva());
        Assertions.assertFalse(terapia.esFinalizada());
    }

    @Test
    void deberiaResponderEsTerapiaFinalizadaCorrectamente(){
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .conEstadoTerapia(EstadoTerapia.FINALIZADA)
                .build();
        Assertions.assertTrue(terapia.esFinalizada());
        Assertions.assertFalse(terapia.esActiva());
    }
}
