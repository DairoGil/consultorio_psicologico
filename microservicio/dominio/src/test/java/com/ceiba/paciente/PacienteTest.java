package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.sesion.modelo.entidad.Sesion;
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
        Assertions.assertEquals(0, paciente.getSesionesAsesoria());
    }

    @Test
    void deberiaReconstruirPacienteExitoso(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .reconstruir();

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
    void pacienteSinTipoPacienteDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere el tipo de paciente");
    }

    @Test
    void reconstruirPacienteSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conTipoPaciente(TipoPaciente.VALORACION)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere la identificación del paciente");
    }

    @Test
    void reconstruirPacienteSinNombreDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conTipoPaciente(TipoPaciente.VALORACION)
                        .conId(104l)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Se requiere el nombre del paciente");
    }

    @Test
    void reconstruirPacienteSinFechaNacimientoDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conTipoPaciente(TipoPaciente.VALORACION)
                        .conId(104l)
                        .conNombre("Paciente prueba")
                        .reconstruir(),
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
                .reconstruir();
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
                .reconstruir();
        Assertions.assertTrue(paciente.esTipoTerapia());
        Assertions.assertFalse(paciente.esTipoValoracion());
        Assertions.assertFalse(paciente.esTipoAsesoria());
    }

    @Test
    void asesorarPacienteValoracionDeberiaQuedarActualizado(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .reconstruir();
        paciente.asesorar();
        Assertions.assertEquals(TipoPaciente.ASESORIA, paciente.getTipoPaciente());
        Assertions.assertEquals(3, paciente.getSesionesAsesoria());
    }

    @Test
    void asesorarPacienteNoEstaValoracionDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conTipoPaciente(TipoPaciente.TERAPIA)
                .reconstruir();

        BasePrueba.assertThrows(()->paciente.asesorar(),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }

    @Test
    void asesorarPacienteTieneSesionesAsesoriaDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conSesionesAsesoria(1)
                .reconstruir();

        BasePrueba.assertThrows(()->paciente.asesorar(),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }

    @Test
    void asignarTerapiaAPacienteValoracionDeberiaQuedarActualizado(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .crear();
        paciente.asignarTerapia();
        Assertions.assertEquals(TipoPaciente.TERAPIA, paciente.getTipoPaciente());
    }

    @Test
    void asignarTerapiaPacienteNoEstaValoracionDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conTipoPaciente(TipoPaciente.ASESORIA)
                .reconstruir();

        BasePrueba.assertThrows(()->paciente.asignarTerapia(),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }

    @Test
    void asignarTerapiaPacienteTieneSesionesAsesoriaDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conSesionesAsesoria(1)
                .reconstruir();

        BasePrueba.assertThrows(()->paciente.asignarTerapia(),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }

    @Test
    void asignarFechaNacimientoPosteriorFechaActualDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conFechaNacimiento(Sesion.sumarDias(1))
                .reconstruir();

        BasePrueba.assertThrows(()->paciente.crear(),
                ExcepcionValorInvalido.class,
                "Esta fecha de nacimiento no es valida");
    }

    @Test
    void asignarFechaNacimientoAnteriorFechaMinimaDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conFechaNacimiento(LocalDate.now().minusYears(131))
                .reconstruir();

        BasePrueba.assertThrows(()->paciente.crear(),
                ExcepcionValorInvalido.class,
                "Esta fecha de nacimiento no es valida");
    }
}
