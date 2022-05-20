package com.ceiba.sesion;

import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;

import java.time.LocalDate;

public class ResumenSesionDTOTestDataBuilder {

    private Long id;
    private LocalDate fecha;
    private Integer hora;
    private EstadoSesion estado;
    private String nota;

    public ResumenSesionDTOTestDataBuilder conResumenSesionPorDefecto() {
        this.id = 2l;
        this.fecha = Sesion.sumarDias(3);
        this.hora = 8;
        this.estado = EstadoSesion.PENDIENTE;
        this.nota = "Se evidencia avance en el paciente";

        return this;
    }

    public ResumenSesionDTO reconstruir() {
        return new ResumenSesionDTO(id, fecha, hora, estado, nota);
    }
}
