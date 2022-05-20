package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;

public class ServicioRegistrar {
    private final RepositorioPaciente repositorioPaciente;

    public ServicioRegistrar(RepositorioPaciente repositorioPaciente){
        this.repositorioPaciente = repositorioPaciente;
    }

    public Long ejecutar(Paciente paciente){
        ValidadorArgumento.validarObligatorio(paciente, "Se requiere paciente para registrar");
        validarDuplicidad(paciente);
        paciente.crear();
        return repositorioPaciente.guardar(paciente);
    }

    private void validarDuplicidad(Paciente paciente){
        if(repositorioPaciente.obtener(paciente.getId()) != null){
            throw new ExcepcionDuplicidad("El paciente ya existe en el sistema");
        }
    }
}
