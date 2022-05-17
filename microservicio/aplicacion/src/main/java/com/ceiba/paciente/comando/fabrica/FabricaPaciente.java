package com.ceiba.paciente.comando.fabrica;

import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.entidad.Paciente;
import org.springframework.stereotype.Component;

@Component
public class FabricaPaciente {

    public Paciente crear(ComandoPaciente comandoPaciente){
        return new Paciente(comandoPaciente.getId(),
                comandoPaciente.getNombre(),
                comandoPaciente.getFechaNacimiento(),
                comandoPaciente.getTelefono(),
                comandoPaciente.getTipoPaciente()
        );
    }
}
