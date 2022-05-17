package com.ceiba.paciente.comando;

import com.ceiba.paciente.entidad.TipoPaciente;
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

    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private TipoPaciente tipoPaciente;
}
