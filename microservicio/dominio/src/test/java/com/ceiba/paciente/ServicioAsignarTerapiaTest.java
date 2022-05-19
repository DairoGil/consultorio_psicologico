package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsesorar;
import com.ceiba.paciente.servicio.ServicioAsignarTerapia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioAsignarTerapiaTest {

    @Test
    void deberiaAsignarTerapiaPacienteTipoValoracion() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .build();
        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsignarTerapia servicioAsignarTerapia = new ServicioAsignarTerapia(repositorioPaciente);
        servicioAsignarTerapia.ejecutar(paciente);

        Mockito.verify(repositorioPaciente, Mockito.times(1))
                .actualizarTipo(paciente);
        Assertions.assertEquals(TipoPaciente.TERAPIA, paciente.getTipoPaciente());
    }

    @Test
    void asignarTerapiaPacienteNoExisteDeberiaLanzarError() {
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
                .conTipoPaciente(TipoPaciente.ASESORIA)
                .build();

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsesorar servicioAsesorar = new ServicioAsesorar(repositorioPaciente);

        BasePrueba.assertThrows(()-> servicioAsesorar.ejecutar(paciente),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene activa una asesoria o terapia");
    }
}
