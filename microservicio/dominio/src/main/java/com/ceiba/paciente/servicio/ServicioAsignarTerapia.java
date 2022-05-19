package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;

public class ServicioAsignarTerapia {

    private final RepositorioPaciente repositorioPaciente;

    public ServicioAsignarTerapia(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public void ejecutar(Paciente paciente){
        ValidadorArgumento.validarObligatorio(paciente, "No se encuentra el paciente");
        paciente.asignarTerapia();
        repositorioPaciente.actualizarTipo(paciente);
    }
}
