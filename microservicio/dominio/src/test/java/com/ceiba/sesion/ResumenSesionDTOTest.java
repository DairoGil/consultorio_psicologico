package com.ceiba.sesion;

import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ResumenSesionDTOTest {

    @Test
    void deberiaReconstruirResumenSesionExitoso() {

        ResumenSesionDTO resumenSesionDTO = new ResumenSesionDTOTestDataBuilder()
                .conResumenSesionPorDefecto()
                .reconstruir();

        Assertions.assertEquals(2l, resumenSesionDTO.getId());
        Assertions.assertEquals(Sesion.sumarDias(3), resumenSesionDTO.getFecha());
        Assertions.assertEquals(8, resumenSesionDTO.getHora());
        Assertions.assertEquals(EstadoSesion.PENDIENTE, resumenSesionDTO.getEstado());
        Assertions.assertEquals("Se evidencia avance en el paciente", resumenSesionDTO.getNota());
    }
}
