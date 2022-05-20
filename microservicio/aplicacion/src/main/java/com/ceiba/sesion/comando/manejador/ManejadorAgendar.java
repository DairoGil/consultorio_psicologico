package com.ceiba.sesion.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.sesion.comando.ComandoSesion;
import com.ceiba.sesion.comando.fabrica.FabricaSesion;
import com.ceiba.sesion.servicio.ServicioAgendar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAgendar implements ManejadorComandoRespuesta<ComandoSesion, ComandoRespuesta<Long>> {

    private final ServicioAgendar servicioAgendar;
    private final FabricaSesion fabricaSesion;

    public ManejadorAgendar(ServicioAgendar servicioAgendar, FabricaSesion fabricaSesion) {
        this.servicioAgendar = servicioAgendar;
        this.fabricaSesion = fabricaSesion;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSesion comando) {
        return new ComandoRespuesta<>(servicioAgendar.
                ejecutar(fabricaSesion.crear(comando)));
    }
}
