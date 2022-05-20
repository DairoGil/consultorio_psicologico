package com.ceiba.terapia.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.servicio.ServicioAsignarTerapia;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;

public class ServicioIniciar {
    private final RepositorioTerapia repositorioTerapia;
    private final ServicioAsignarTerapia servicioAsignarTerapia;

    public ServicioIniciar(RepositorioTerapia repositorioTerapia, ServicioAsignarTerapia servicioAsignarTerapia) {
        this.repositorioTerapia = repositorioTerapia;
        this.servicioAsignarTerapia = servicioAsignarTerapia;
    }

    public Long ejecutar(Terapia terapia) {
        terapia.iniciar();
        validarTerapiasActivas(terapia.getPaciente().getId());
        servicioAsignarTerapia.ejecutar(terapia.getPaciente());
        return repositorioTerapia.guardar(terapia);
    }

    private void validarTerapiasActivas(Long idPaciente){
        if(repositorioTerapia.obtenerActivaPorIdPaciente(idPaciente) != null){
            throw new ExcepcionDuplicidad("El paciente ya tiene una terapia activa");
        }
    }
}
