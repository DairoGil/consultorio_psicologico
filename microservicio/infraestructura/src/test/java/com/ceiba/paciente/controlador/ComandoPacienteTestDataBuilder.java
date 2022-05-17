package com.ceiba.paciente.controlador;

import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.entidad.TipoPaciente;

import java.time.LocalDate;

public class ComandoPacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private TipoPaciente tipoPaciente;

    public ComandoPacienteTestDataBuilder crearPorDefecto() {
        this.id = 3l;
        this.nombre = "Paciente 1";
        this.fechaNacimiento = LocalDate.of(1996,7,23);
        this.telefono = "3245876935";
        return this;
    }

    public ComandoPaciente build(){
        return new ComandoPaciente(this.id, this.nombre, this.fechaNacimiento, this.telefono, this.tipoPaciente);
    }
}
