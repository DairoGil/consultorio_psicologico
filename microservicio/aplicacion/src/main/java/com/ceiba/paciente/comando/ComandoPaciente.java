package com.ceiba.paciente.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPaciente {

    private Long idPaciente;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
}
