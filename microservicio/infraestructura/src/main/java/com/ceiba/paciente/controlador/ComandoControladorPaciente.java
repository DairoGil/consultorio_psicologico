package com.ceiba.paciente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorRegistrar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
@Tag(name = "Controlador comando paciente")
public class ComandoControladorPaciente {

    private final ManejadorRegistrar manejadorRegistrar;

    public ComandoControladorPaciente(ManejadorRegistrar manejadorRegistrar){
        this.manejadorRegistrar = manejadorRegistrar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar paciente", description = "Metodo utilizado para registrar un nuevo paciente")
    public ComandoRespuesta<Long> registrar(@RequestBody ComandoPaciente comandoPaciente){
        return this.manejadorRegistrar.ejecutar(comandoPaciente);
    }
}
