package com.ceiba.sesion;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.servicio.ServicioValidarPosibilidadAgendarCita;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import com.ceiba.sesion.puerto.repositorio.RepositorioSesion;
import com.ceiba.sesion.servicio.ServicioAgendar;
import com.ceiba.terapia.TerapiaTestDataBuilder;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class ServicioAgendarTest {

    @Test
    void deberiaAgendarSesionSinTerapia() {
        Sesion sesion = new SesionTestDataBuilder().conSesionPorDefecto().crear();
        List<ResumenSesionDTO> sesionesPendientes = new ArrayList<>();

        RepositorioSesion repositorioSesion =
                Mockito.mock(RepositorioSesion.class);
        Mockito.when(repositorioSesion.guardar(Mockito.any())).thenReturn(2l);

        DaoSesion daoSesion = Mockito.mock(DaoSesion.class);
        Mockito.when(daoSesion.obtenerPendientesPorIdPaciente(Mockito.any())).thenReturn(sesionesPendientes);

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);

        ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita =
                new ServicioValidarPosibilidadAgendarCita(daoSesion);

        ServicioAgendar servicioAgendar = new ServicioAgendar(repositorioSesion, daoSesion, repositorioTerapia, servicioValidarPosibilidadAgendarCita);

        Long idSesionCreada = servicioAgendar.ejecutar(sesion);

        Mockito.verify(repositorioSesion, Mockito.times(1))
                .guardar(sesion);
        Assertions.assertEquals(2l, idSesionCreada);
    }

    @Test
    void agendarSesionElMismoDiaDeOtraSesionDeberiaLanzarError() {
        Sesion sesion = new SesionTestDataBuilder()
                .conPaciente(new PacienteTestDataBuilder()
                        .conPacientePorDefecto()
                        .conTipoPaciente(TipoPaciente.ASESORIA)
                        .conSesionesAsesoria(3)
                        .build())
                .conFecha(Sesion.sumarDias(3))
                .conHora(8)
                .crear();
        List<ResumenSesionDTO> sesionesPendientes = new ArrayList<>();
        sesionesPendientes.add(new ResumenSesionDTOTestDataBuilder().conResumenSesionPorDefecto().reconstruir());

        RepositorioSesion repositorioSesion =
                Mockito.mock(RepositorioSesion.class);
        Mockito.when(repositorioSesion.guardar(Mockito.any())).thenReturn(2l);

        DaoSesion daoSesion = Mockito.mock(DaoSesion.class);
        Mockito.when(daoSesion.obtenerPendientesPorIdPaciente(Mockito.any())).thenReturn(sesionesPendientes);

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);

        ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita =
                new ServicioValidarPosibilidadAgendarCita(daoSesion);

        ServicioAgendar servicioAgendar = new ServicioAgendar(repositorioSesion, daoSesion, repositorioTerapia, servicioValidarPosibilidadAgendarCita);

        BasePrueba.assertThrows(()->servicioAgendar.ejecutar(sesion),
                ExcepcionDuplicidad.class,
                "No puede agendar dos citas el mismo dia");
    }

    @Test
    void deberiaAgendarSesionConTerapia() {
        Sesion sesion = new SesionTestDataBuilder().conSesionPorDefecto().crear();
        List<ResumenSesionDTO> sesionesPendientes = new ArrayList<>();
        Terapia terapia = new TerapiaTestDataBuilder().conTerapiaPorDefecto().build();

        RepositorioSesion repositorioSesion =
                Mockito.mock(RepositorioSesion.class);
        Mockito.when(repositorioSesion.guardar(Mockito.any())).thenReturn(2l);

        DaoSesion daoSesion = Mockito.mock(DaoSesion.class);
        Mockito.when(daoSesion.obtenerPendientesPorIdPaciente(Mockito.any())).thenReturn(sesionesPendientes);

        RepositorioTerapia repositorioTerapia =
                Mockito.mock(RepositorioTerapia.class);
        Mockito.when(repositorioTerapia.obtenerActivaPorIdPaciente(Mockito.any())).thenReturn(terapia);

        ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita =
                new ServicioValidarPosibilidadAgendarCita(daoSesion);

        ServicioAgendar servicioAgendar = new ServicioAgendar(repositorioSesion, daoSesion, repositorioTerapia, servicioValidarPosibilidadAgendarCita);

        Long idSesionCreada = servicioAgendar.ejecutar(sesion);

        Mockito.verify(repositorioSesion, Mockito.times(1))
                .guardar(sesion);
        Assertions.assertEquals(2l, idSesionCreada);
        Assertions.assertEquals(terapia, sesion.getTerapia());
    }
}