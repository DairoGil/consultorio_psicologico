package com.ceiba.terapia;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsignarTerapia;
import com.ceiba.terapia.entidad.EstadoTerapia;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;
import com.ceiba.terapia.servicio.ServicioIniciar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioIniciarTest {

    @Test
    void deberiaGuardarTerapia() {
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .iniciar();

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);
        Mockito.when(repositorioTerapia.guardar(Mockito.any())).thenReturn(terapia.getId());
        Mockito.when(repositorioTerapia.obtenerActivaPorIdPaciente(Mockito.any())).thenReturn(null);

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsignarTerapia servicioAsignarTerapia = new ServicioAsignarTerapia(repositorioPaciente);

        ServicioIniciar servicioIniciar = new ServicioIniciar(repositorioTerapia, servicioAsignarTerapia);

        Long idTerapiaCreada = servicioIniciar.ejecutar(terapia);

        Terapia terapiaCreada = repositorioTerapia.obtener(idTerapiaCreada);
        Mockito.verify(repositorioTerapia, Mockito.times(1))
                .guardar(terapia);
        Assertions.assertTrue(terapia.getPaciente().esTipoTerapia());
        Assertions.assertEquals(terapia.getId(), idTerapiaCreada);
        Assertions.assertTrue(terapia.esActiva());
    }

    @Test
    void pacienteConTerapiaActivaDeberiaLanzarError() {
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .iniciar();

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);
        Mockito.when(repositorioTerapia.obtenerActivaPorIdPaciente(Mockito.any())).thenReturn(terapia);

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsignarTerapia servicioAsignarTerapia = new ServicioAsignarTerapia(repositorioPaciente);

        ServicioIniciar servicioIniciar = new ServicioIniciar(repositorioTerapia, servicioAsignarTerapia);

        BasePrueba.assertThrows(()->servicioIniciar.ejecutar(terapia),
                ExcepcionDuplicidad.class,
                "El paciente ya tiene una terapia activa");
    }

    @Test
    void validarFuncionalidadIniciar() {
        Terapia terapia = new TerapiaTestDataBuilder()
                .conPaciente(new PacienteTestDataBuilder().conPacientePorDefecto().reconstruir())
                .conResumen("Estado del paciente")
                .conPeriodicidadMes(2)
                .build();

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsignarTerapia servicioAsignarTerapia = new ServicioAsignarTerapia(repositorioPaciente);

        ServicioIniciar servicioIniciar = new ServicioIniciar(repositorioTerapia, servicioAsignarTerapia);

        servicioIniciar.ejecutar(terapia);

        Assertions.assertEquals(EstadoTerapia.ACTIVA, terapia.getEstado());
    }
}
