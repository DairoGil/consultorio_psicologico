package com.ceiba.sesion;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.terapia.TerapiaTestDataBuilder;
import com.ceiba.terapia.entidad.Terapia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

class SesionTest {

    @Test
    void deberiaAgendarSesionExitoso() {
        Sesion sesion = new SesionTestDataBuilder().conSesionPorDefecto().crear();

        Assertions.assertEquals(1l, sesion.getPaciente().getId());
        Assertions.assertEquals(Sesion.sumarDias(3), sesion.getFecha());
        Assertions.assertEquals(8, sesion.getHora());
        Assertions.assertEquals(EstadoSesion.PENDIENTE, sesion.getEstado());
        Assertions.assertEquals(80000, sesion.getValor());
    }

    @Test
    void reconstruirSesionExitoso() {
        Sesion sesion = new SesionTestDataBuilder().conSesionPorDefecto().reconstruir();

        Assertions.assertEquals(2l, sesion.getId());
        Assertions.assertEquals(Sesion.sumarDias(3), sesion.getFecha());
        Assertions.assertEquals(8, sesion.getHora());
        Assertions.assertEquals(EstadoSesion.PENDIENTE, sesion.getEstado());
        Assertions.assertEquals("Se evidencia avance en el paciente", sesion.getNota());
    }

    @Test
    void agendarSesionSinPacienteDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder().crear(),
                ExcepcionValorObligatorio.class,
                "Se requiere el paciente que asistira a la sesion");
    }

    @Test
    void agendarSesionSinFechaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder().conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build()).crear(),
                ExcepcionValorObligatorio.class,
                "Se requiere la fecha de la sesion");
    }

    @Test
    void agendarSesionSinhoraDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .conFecha(Sesion.sumarDias(3))
                        .crear(),
                ExcepcionValorObligatorio.class,
                "Se requiere la hora de la sesion");
    }

    @Test
    void reconstruirSesionSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder().reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere el id de la sesion");
    }

    @Test
    void reconstruirSesionSinFechaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conId(3l)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere la fecha de la sesion");
    }

    @Test
    void reconstruirSesionSinHoraDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conId(3l)
                        .conFecha(Sesion.sumarDias(3))
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere la hora de la sesion");
    }

    @Test
    void reconstruirSesionSinEstadoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conId(3l)
                        .conFecha(Sesion.sumarDias(3))
                        .conHora(8)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere el estado de la sesion");
    }

    @Test
    void agendarSesionEnSabadoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .conFecha(LocalDate.of(2022, 05, 21))
                        .conHora(8)
                        .crear(),
                ExcepcionValorInvalido.class,
                "No se presta servicio los fines de semana");
    }

    @Test
    void agendarSesionEnDomingoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .conFecha(LocalDate.of(2022, 05, 22))
                        .conHora(8)
                        .crear(),
                ExcepcionValorInvalido.class,
                "No se presta servicio los fines de semana");
    }

    @Test
    void agendarSesionEnHoraNoPermitidaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .conFecha(Sesion.sumarDias(3))
                        .conHora(7)
                        .crear(),
                ExcepcionValorInvalido.class,
                "La hora ingresada no esta dentro del horario de atención");
    }

    @Test
    void agendarSesionSinDiasAnticipacionDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new SesionTestDataBuilder()
                        .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().build())
                        .conFecha(Sesion.sumarDias(2))
                        .conHora(8)
                        .crear(),
                ExcepcionValorInvalido.class,
                "Requiere al menos dos dias de anticipación para realizar este proceso");
    }

    @Test
    void validarCalculoDeValorPacienteAsesoria() {
        Sesion sesion = new SesionTestDataBuilder()
                .conPaciente(new PacienteTestDataBuilder()
                        .conPacientePorDefecto()
                        .conTipoPaciente(TipoPaciente.ASESORIA)
                        .reconstruir())
                .conFecha(Sesion.sumarDias(3))
                .conHora(8)
                .crear();

        Assertions.assertEquals(0, sesion.getValor());
    }

    @Test
    void validarCalculoDeValorPacienteTerapia() {
        Sesion sesion = new SesionTestDataBuilder()
                .conPaciente(new PacienteTestDataBuilder()
                        .conPacientePorDefecto()
                        .conTipoPaciente(TipoPaciente.TERAPIA)
                        .reconstruir())
                .conFecha(Sesion.sumarDias(3))
                .conHora(8)
                .crear();

        Assertions.assertEquals(60000, sesion.getValor());
    }

    @Test
    void deberiaAsignarTerapiaALaSesion() {
        Terapia terapia = new TerapiaTestDataBuilder().conTerapiaPorDefecto().build();
        Sesion sesion = new SesionTestDataBuilder()
                .conPaciente(terapia.getPaciente())
                .conFecha(Sesion.sumarDias(3))
                .conHora(8)
                .crear();
        sesion.asignarTerapia(terapia);

        Assertions.assertEquals(terapia, sesion.getTerapia());
    }

    @Test
    void deberiaResponderEsSesionPendienteCorrectamente() {
        Sesion sesion = new SesionTestDataBuilder().conSesionPorDefecto().crear();

        Assertions.assertTrue(sesion.esPendiente());
        Assertions.assertFalse(sesion.esCumplida());
    }

    @Test
    void deberiaResponderEsSesionCumplidaCorrectamente() {
        Sesion sesion = new SesionTestDataBuilder()
                .conSesionPorDefecto()
                .conEstado(EstadoSesion.CUMPLIDA)
                .reconstruir();

        Assertions.assertTrue(sesion.esCumplida());
        Assertions.assertFalse(sesion.esPendiente());
    }

}
