package com.ceiba.paciente.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.paciente.comando.ComandoAsesorar;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsesorar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAsesorar implements ManejadorComando<ComandoAsesorar> {

    private final ServicioAsesorar servicioAsesorar;
    private final RepositorioPaciente repositorioPaciente;

    public ManejadorAsesorar(ServicioAsesorar servicioAsesorar, RepositorioPaciente repositorioPaciente) {
        this.servicioAsesorar = servicioAsesorar;
        this.repositorioPaciente = repositorioPaciente;
    }

    @Override
    public void ejecutar(ComandoAsesorar comandoAsesorar) {
        servicioAsesorar.ejecutar(repositorioPaciente.obtener(comandoAsesorar.getIdPaciente()));
    }
}
