package com.ceiba.paciente.puerto;

import com.ceiba.paciente.entidad.Paciente;

public interface RepositorioPaciente {

    Long guardar(Paciente paciente);
    Paciente obtener(Long id);
    void actualizar(Paciente paciente);
    void eliminar(Long id);
}
