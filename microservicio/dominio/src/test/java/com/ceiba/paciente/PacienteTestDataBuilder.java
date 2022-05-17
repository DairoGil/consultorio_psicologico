package com.ceiba.paciente;

import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;

import java.time.LocalDate;

public class PacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private TipoPaciente tipoPaciente;

    public PacienteTestDataBuilder conPacientePorDefecto(){
        this.id = 1l;
        this.nombre = "Paciente 1";
        this.fechaNacimiento = LocalDate.of(1996,7,23);
        this.telefono = "3120144527";
        this.tipoPaciente = TipoPaciente.VALORACION;

        return this;
    }

    public PacienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PacienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PacienteTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public PacienteTestDataBuilder conFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public PacienteTestDataBuilder conTipoPaciente(TipoPaciente tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
        return this;
    }

    public Paciente build() {
        Paciente paciente = new Paciente(id, nombre, fechaNacimiento,telefono, tipoPaciente);
        return paciente;
    }

    public Paciente crear() {
        Paciente paciente = new Paciente(id, nombre, fechaNacimiento,telefono, tipoPaciente);
        paciente.crear();
        return paciente;
    }
}
