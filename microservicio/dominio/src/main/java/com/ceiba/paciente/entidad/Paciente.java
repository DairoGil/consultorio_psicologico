package com.ceiba.paciente.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Paciente {

    private Long id;
    private final String nombre;
    private final LocalDate fechaNacimiento;
    private String telefono;

    private Integer sesionesAsesoria;
    private TipoPaciente tipoPaciente;

    public Paciente(Long id, String nombre, LocalDate fechaNacimiento, String telefono, Integer sesionesAsesoria, TipoPaciente tipoPaciente) {
        validarObligatorio(id, "Se requiere la identificación del paciente");
        validarObligatorio(nombre, "Se requiere el nombre del paciente");
        validarObligatorio(fechaNacimiento, "Se requiere la fecha de nacimiento del paciente");

        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sesionesAsesoria = sesionesAsesoria;
        this.tipoPaciente = tipoPaciente;
    }

    public Paciente(Long id, String nombre, LocalDate fechaNacimiento, String telefono) {
        validarObligatorio(id, "Se requiere la identificación del paciente");
        validarObligatorio(nombre, "Se requiere el nombre del paciente");
        validarObligatorio(fechaNacimiento, "Se requiere la fecha de nacimiento del paciente");

        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }

    public boolean esTipoValoracion() {
        return this.tipoPaciente.equals(TipoPaciente.VALORACION);
    }

    public boolean esTipoAsesoria() {
        return this.tipoPaciente.equals(TipoPaciente.ASESORIA);
    }

    public boolean esTipoTerapia() {
        return this.tipoPaciente.equals(TipoPaciente.TERAPIA);
    }

    public void crear(){
        this.tipoPaciente = TipoPaciente.VALORACION;
        this.sesionesAsesoria = 0;
    }

    public static Paciente reconstruir(Long id, String nombre, LocalDate fechaNacimiento, String telefono, Integer citasAsesoria, TipoPaciente tipoPaciente){
        validarObligatorio(tipoPaciente, "Se requiere el tipo de paciente");
        return new Paciente(id, nombre, fechaNacimiento, telefono, citasAsesoria, tipoPaciente);
    }

    public void asesorar() {
        if(!esTipoValoracion() || this.sesionesAsesoria != 0){
            throw new ExcepcionValorInvalido("El paciente ya tiene activa una asesoria o terapia");
        }
        this.sesionesAsesoria = 3;
        this.tipoPaciente = TipoPaciente.ASESORIA;
    }

    public void asignarTerapia(){
        if(!esTipoValoracion() || this.sesionesAsesoria != 0){
            throw new ExcepcionValorInvalido("El paciente ya tiene activa una asesoria o terapia");
        }
        this.tipoPaciente = TipoPaciente.TERAPIA;
    }
}
