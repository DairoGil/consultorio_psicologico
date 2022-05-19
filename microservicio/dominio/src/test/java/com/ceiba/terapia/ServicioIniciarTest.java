package com.ceiba.terapia;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsignarTerapia;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;
import com.ceiba.terapia.servicio.ServicioIniciar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class ServicioIniciarTest {

    @Test
    void deberiaGuardarTerapia() {
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .iniciar();

        List<Terapia> terapiasActivas = new ArrayList<>();

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);
        Mockito.when(repositorioTerapia.guardar(Mockito.any())).thenReturn(terapia.getId());
        Mockito.when(repositorioTerapia.obtenerPorIdPaciente(Mockito.any())).thenReturn(terapiasActivas);

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsignarTerapia servicioAsignarTerapia = new ServicioAsignarTerapia(repositorioPaciente);

        ServicioIniciar servicioIniciar = new ServicioIniciar(repositorioTerapia, servicioAsignarTerapia);

        Long idTerapiaCreada = servicioIniciar.ejecutar(terapia);

        Mockito.verify(repositorioTerapia, Mockito.times(1))
                .guardar(terapia);
        Assertions.assertTrue(terapia.getPaciente().esTipoTerapia());
        Assertions.assertEquals(terapia.getId(), idTerapiaCreada);
    }

    @Test
    void pacienteConTerapiaActivaDeberiaLanzarError() {
        Terapia terapia = new TerapiaTestDataBuilder()
                .conTerapiaPorDefecto()
                .iniciar();

        List<Terapia> terapiasActivas = new ArrayList<>();
        terapiasActivas.add(terapia);

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);
        Mockito.when(repositorioTerapia.obtenerPorIdPaciente(Mockito.any())).thenReturn(terapiasActivas);

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioAsignarTerapia servicioAsignarTerapia = new ServicioAsignarTerapia(repositorioPaciente);

        ServicioIniciar servicioIniciar = new ServicioIniciar(repositorioTerapia, servicioAsignarTerapia);

        BasePrueba.assertThrows(()->servicioIniciar.ejecutar(terapia),
                ExcepcionDuplicidad.class,
                "El paciente ya tiene una terapia activa");
    }
}
