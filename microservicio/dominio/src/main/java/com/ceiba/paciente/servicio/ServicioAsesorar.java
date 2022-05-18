package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;

public class ServicioAsesorar {

    private final RepositorioPaciente repositorioPaciente;

    public ServicioAsesorar(RepositorioPaciente repositorioPaciente){
        this.repositorioPaciente = repositorioPaciente;
    }

    public void ejecutar(Paciente paciente){
        ValidadorArgumento.validarObligatorio(paciente, "No se encuentra el paciente");
        paciente.asesorar();
        repositorioPaciente.actualizarAsesoria(paciente);
    }
}
