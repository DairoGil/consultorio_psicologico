package com.ceiba.sesion.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.sesion.comando.ComandoSesion;
import com.ceiba.sesion.comando.manejador.ManejadorAgendar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sesion")
@Tag(name = "Controlador comando sesion")
public class ComandoControladorSesion {

    private final ManejadorAgendar manejadorAgendar;

    public ComandoControladorSesion(ManejadorAgendar manejadorAgendar) {
        this.manejadorAgendar = manejadorAgendar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Agendar", description = "Metodo utilizado para agendar una sesion")
    public ComandoRespuesta<Long> agendar(@RequestBody ComandoSesion comandoSesion){
        return this.manejadorAgendar.ejecutar(comandoSesion);
    }
}
