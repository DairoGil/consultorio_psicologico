package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.servicio.ServicioValidarPosibilidadAgendarCita;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class ServicioValidarPosibilidadAgendarCitaTest {

    private Paciente paciente;
    private List<ResumenSesionDTO> sesionesPendientes;

    @BeforeEach
    void setUp() {
        paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .build();

        sesionesPendientes = new ArrayList<>();
    }

    @Test
    void esPosibleAgendarCita(){
        DaoSesion daoSesion = Mockito.mock(DaoSesion.class);
        Mockito.when(daoSesion.obtenerPendientesPorIdPaciente(Mockito.any())).thenReturn(sesionesPendientes);

        ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita
                = new ServicioValidarPosibilidadAgendarCita(daoSesion);

        servicioValidarPosibilidadAgendarCita.ejecutar(paciente);

        Mockito.verify(daoSesion, Mockito.times(1))
                .obtenerPendientesPorIdPaciente(paciente.getId());
    }

    @Test
    void noSeEncuentraPacienteDeberiaLanzarError(){
        DaoSesion daoSesion = Mockito.mock(DaoSesion.class);

        ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita
                = new ServicioValidarPosibilidadAgendarCita(daoSesion);

        BasePrueba.assertThrows(()->servicioValidarPosibilidadAgendarCita.ejecutar(null),
                ExcepcionValorObligatorio.class,
                "No se encuentra el paciente");
    }

    @Test
    void agendarCitaPacienteValoracionConCitaPendienteDeberiaLanzarError(){

        DaoSesion daoSesion = Mockito.mock(DaoSesion.class);

        ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita
                = new ServicioValidarPosibilidadAgendarCita(daoSesion);

        BasePrueba.assertThrows(()->servicioValidarPosibilidadAgendarCita.ejecutar(null),
                ExcepcionValorObligatorio.class,
                "No se encuentra el paciente");
    }
}
