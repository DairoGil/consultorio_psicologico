package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;

public class ServicioActualizarTipo {

    private final RepositorioPaciente repositorioPaciente;

    public ServicioActualizarTipo(RepositorioPaciente repositorioPaciente){
        this.repositorioPaciente = repositorioPaciente;
    }

    public void ejecutar(Paciente paciente, TipoPaciente nuevoTipo){
        ValidadorArgumento.validarObligatorio(paciente, "No existe paciente para registrar");
        paciente.actualizarTipo(nuevoTipo);
        repositorioPaciente.actualizar(paciente);
    }
}
