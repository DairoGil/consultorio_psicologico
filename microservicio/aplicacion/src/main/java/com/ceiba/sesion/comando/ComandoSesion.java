package com.ceiba.sesion.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSesion {

    private Long idPaciente;
    private LocalDate fecha;
    private Integer hora;
}
