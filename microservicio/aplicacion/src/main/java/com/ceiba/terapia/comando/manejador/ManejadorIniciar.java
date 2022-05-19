package com.ceiba.terapia.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.terapia.comando.ComandoTerapia;
import com.ceiba.terapia.comando.fabrica.FabricaTerapia;
import com.ceiba.terapia.servicio.ServicioIniciar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorIniciar implements ManejadorComandoRespuesta<ComandoTerapia, ComandoRespuesta<Long>> {

    private final ServicioIniciar servicioIniciar;
    private final FabricaTerapia fabricaTerapia;

    public ManejadorIniciar(ServicioIniciar servicioIniciar, FabricaTerapia fabricaTerapia) {
        this.servicioIniciar = servicioIniciar;
        this.fabricaTerapia = fabricaTerapia;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoTerapia comando) {
        return new ComandoRespuesta<>(servicioIniciar.
                ejecutar(fabricaTerapia.crear(comando)));
    }
}
