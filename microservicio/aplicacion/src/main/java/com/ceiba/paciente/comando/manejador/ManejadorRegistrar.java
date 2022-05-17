package com.ceiba.paciente.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.comando.fabrica.FabricaPaciente;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistrar implements ManejadorComandoRespuesta<ComandoPaciente, ComandoRespuesta<Long>> {

    private final FabricaPaciente fabricaPaciente;
    private final ServicioRegistrar servicioRegistrar;

    public ManejadorRegistrar(FabricaPaciente fabricaPaciente, ServicioRegistrar servicioRegistrar){
        this.fabricaPaciente = fabricaPaciente;
        this.servicioRegistrar = servicioRegistrar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoPaciente comandoPaciente) {
        return new ComandoRespuesta<>(servicioRegistrar
                .ejecutar(fabricaPaciente.crear(comandoPaciente)));
    }
}
