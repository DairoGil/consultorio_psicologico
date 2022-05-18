package com.ceiba.paciente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.paciente.comando.ComandoAsesorar;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorAsesorar;
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
    private final ManejadorAsesorar manejadorAsesorar;

    public ComandoControladorPaciente(ManejadorRegistrar manejadorRegistrar, ManejadorAsesorar manejadorAsesorar){
        this.manejadorRegistrar = manejadorRegistrar;
        this.manejadorAsesorar = manejadorAsesorar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar", description = "Metodo utilizado para registrar un nuevo paciente")
    public ComandoRespuesta<Long> registrar(@RequestBody ComandoPaciente comandoPaciente){
        return this.manejadorRegistrar.ejecutar(comandoPaciente);
    }

    @PostMapping("asesorar/{id-paciente}")
    @Operation(summary = "Asesorar", description = "Metodo utilizado para indicar que un usuario adquirio una asesoria")
    public void asesorar(@PathVariable("id-paciente") Long idPaciente){
        this.manejadorAsesorar.ejecutar(new ComandoAsesorar(idPaciente));
    }
}
