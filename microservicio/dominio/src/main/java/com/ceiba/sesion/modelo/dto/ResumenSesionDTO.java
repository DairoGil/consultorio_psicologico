package com.ceiba.sesion.modelo.dto;

import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ResumenSesionDTO {

    private Long id;
    private LocalDate fecha;
    private Integer hora;
    private EstadoSesion estado;
    private String nota;
}
