package com.ceiba.sesion.controlador;

import com.ceiba.sesion.comando.ComandoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;

import java.time.LocalDate;

public class ComandoSesionTestDataBuilder {

    private Long idPaciente;
    private LocalDate fecha;
    private Integer hora;

    public ComandoSesionTestDataBuilder crearPorDefecto() {
        this.idPaciente = 1l;
        this.fecha = Sesion.sumarDias(3);
        this.hora = 8;
        return this;
    }

    public ComandoSesion build() {
        return new ComandoSesion(this.idPaciente, this.fecha, this.hora);
    }
}
