package com.ceiba.sesion.comando.fabrica;

import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.sesion.comando.ComandoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;
import org.springframework.stereotype.Component;

@Component
public class FabricaSesion {

    private final RepositorioPaciente repositorioPaciente;

    public FabricaSesion(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public Sesion crear(ComandoSesion comandoSesion) {
        return new Sesion(repositorioPaciente.obtener(comandoSesion.getIdPaciente()),
                comandoSesion.getFecha(),
                comandoSesion.getHora()
        );
    }
}
