package com.ceiba.terapia.comando.fabrica;

import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.terapia.comando.ComandoTerapia;
import com.ceiba.terapia.entidad.Terapia;
import org.springframework.stereotype.Component;

@Component
public class FabricaTerapia {

    private final RepositorioPaciente repositorioPaciente;

    public FabricaTerapia(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public Terapia crear(ComandoTerapia comandoTerapia){
        return new Terapia(comandoTerapia.getId(),
                repositorioPaciente.obtener(comandoTerapia.getIdPaciente()),
                comandoTerapia.getResumen(),
                comandoTerapia.getPeriodicidadMes(),
                comandoTerapia.getEstado()
        );
    }
}
