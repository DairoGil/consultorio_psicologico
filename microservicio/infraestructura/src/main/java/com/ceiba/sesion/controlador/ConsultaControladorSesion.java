package com.ceiba.sesion.controlador;

import com.ceiba.sesion.consulta.ManejadorListarSesionesPendientesPorPaciente;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sesion")
@Tag(name = "Controlador consulta sesion")
public class ConsultaControladorSesion {

    private final ManejadorListarSesionesPendientesPorPaciente manejadorListarSesionesPendientesPorPaciente;

    public ConsultaControladorSesion(ManejadorListarSesionesPendientesPorPaciente manejadorListarSesionesPendientesPorPaciente) {
        this.manejadorListarSesionesPendientesPorPaciente = manejadorListarSesionesPendientesPorPaciente;
    }

    @GetMapping(value = "/pendientes-paciente/{id}")
    @Operation(summary = "Listar", description = "Metodo utilizado para consultar sesiones pendientes de un paciente")
    public List<ResumenSesionDTO> listarSesionesPendientesPorPaciente(@PathVariable("id") Long idPaciente) {
        return this.manejadorListarSesionesPendientesPorPaciente.ejecutar(idPaciente);
    }
}
