package com.ceiba.terapia.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.terapia.comando.ComandoTerapia;
import com.ceiba.terapia.comando.manejador.ManejadorIniciar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terapia")
@Tag(name = "Controlador comando terapia")
public class ComandoControladorTerapia {

    private final ManejadorIniciar manejadorIniciar;

    public ComandoControladorTerapia(ManejadorIniciar manejadorIniciar) {
        this.manejadorIniciar = manejadorIniciar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Iniciar", description = "Metodo utilizado para iniciar una nueva terapia")
    public ComandoRespuesta<Long> iniciar(@RequestBody ComandoTerapia comandoTerapia){
        return this.manejadorIniciar.ejecutar(comandoTerapia);
    }
}
