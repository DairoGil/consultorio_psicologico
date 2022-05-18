package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsesorar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioAsesorarTest {

    @Test
    void asesorarPacienteTipoValoracionSinCitasAsesoria() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .build();
        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsesorar servicioAsesorar = new ServicioAsesorar(repositorioPaciente);
        servicioAsesorar.ejecutar(paciente);

        Mockito.verify(repositorioPaciente, Mockito.times(1))
                .actualizarPorAsesoria(paciente);
        Assertions.assertEquals(TipoPaciente.ASESORIA, paciente.getTipoPaciente());
    }

    @Test
    void asesorarPacienteNoExisteDeberiaLanzarError() {
        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsesorar servicioAsesorar = new ServicioAsesorar(repositorioPaciente);

        BasePrueba.assertThrows(()-> servicioAsesorar.ejecutar(null),
                ExcepcionValorObligatorio.class,
                "No se encuentra el paciente");
    }

    @Test
    void asesorarPacienteNoEstaEnValoracionDeberiaLanzarError() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conTipoPaciente(TipoPaciente.TERAPIA)
                .build();

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsesorar servicioAsesorar = new ServicioAsesorar(repositorioPaciente);

        BasePrueba.assertThrows(()-> servicioAsesorar.ejecutar(paciente),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }

    @Test
    void asesorarPacienteConCitasAsesoriaDeberiaLanzarError() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .conSesionesAsesoria(1)
                .build();

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsesorar servicioAsesorar = new ServicioAsesorar(repositorioPaciente);

        BasePrueba.assertThrows(()-> servicioAsesorar.ejecutar(paciente),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }
}
