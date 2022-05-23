package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class ServicioRegistrarTest {

    @Test
    void deberiaRegistrarPaciente(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conId(1236l)
                .conNombre("Paciente 1")
                .conFechaNacimiento(LocalDate.of(1996,7,23))
                .build();

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);
        Mockito.when(repositorioPaciente.obtener(Mockito.any())).thenReturn(null);
        Mockito.when(repositorioPaciente.guardar(Mockito.any())).thenReturn(1l);

        ServicioRegistrar servicioRegistrar = new ServicioRegistrar(repositorioPaciente);

        Long idPacienteCreado = servicioRegistrar.ejecutar(paciente);

        Mockito.verify(repositorioPaciente, Mockito.times(1))
                        .guardar(paciente);
        Assertions.assertEquals(1l, idPacienteCreado);
        Assertions.assertEquals(TipoPaciente.VALORACION, paciente.getTipoPaciente());
        Assertions.assertEquals(0, paciente.getSesionesAsesoria());
    }

    @Test
    void pacienteDuplicadoDeberiaLanzarError(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .build();

        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);
        Mockito.when(repositorioPaciente.obtener(Mockito.any())).thenReturn(paciente);
        Mockito.when(repositorioPaciente.guardar(Mockito.any())).thenReturn(1l);

        ServicioRegistrar servicioRegistrar = new ServicioRegistrar(repositorioPaciente);

        BasePrueba.assertThrows(()->servicioRegistrar.ejecutar(paciente),
                ExcepcionDuplicidad.class,
                "El paciente ya existe en el sistema");
    }

    @Test
    void pacienteNuloDeberiaLanzarError(){
        RepositorioPaciente repositorioPaciente =
                Mockito.mock(RepositorioPaciente.class);

        ServicioRegistrar servicioRegistrar = new ServicioRegistrar(repositorioPaciente);

        BasePrueba.assertThrows(()->servicioRegistrar.ejecutar(null),
                ExcepcionValorObligatorio.class,
                "Se requiere paciente para registrar");
    }
}
