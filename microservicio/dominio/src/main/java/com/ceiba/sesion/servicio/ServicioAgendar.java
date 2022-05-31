package com.ceiba.sesion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.servicio.ServicioValidarPosibilidadAgendarCita;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import com.ceiba.sesion.puerto.repositorio.RepositorioSesion;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;

import java.util.List;
import java.util.Optional;

public class ServicioAgendar {

    private final RepositorioSesion repositorioSesion;
    private final DaoSesion daoSesion;
    private final RepositorioTerapia repositorioTerapia;

    private final ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita;

    public ServicioAgendar(RepositorioSesion repositorioSesion, DaoSesion daoSesion, RepositorioTerapia repositorioTerapia, ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita) {
        this.repositorioSesion = repositorioSesion;
        this.daoSesion = daoSesion;
        this.repositorioTerapia = repositorioTerapia;
        this.servicioValidarPosibilidadAgendarCita = servicioValidarPosibilidadAgendarCita;
    }

    public Long ejecutar(Sesion sesion){
        servicioValidarPosibilidadAgendarCita.ejecutar(sesion.getPaciente());
        validarSesionElMismoDia(sesion);
        validarSesionEnMismoHorario(sesion);
        sesion.agendar();
        validarTerapiaActiva(sesion);
        return repositorioSesion.guardar(sesion);
    }

    private void validarSesionElMismoDia(Sesion sesion) {
        List<ResumenSesionDTO> sesionesPendientes = daoSesion.listarPendientesPorIdPaciente(sesion.getPaciente().getId());
        Optional<ResumenSesionDTO> sesionMismoDia = sesionesPendientes.stream().filter(sesionEvaluar -> sesionEvaluar.getFecha().equals(sesion.getFecha())).findFirst();
        if(sesionMismoDia.isPresent()){
            throw new ExcepcionDuplicidad("No puede agendar dos citas el mismo dia");
        }
    }
    private void validarTerapiaActiva(Sesion sesion){
        Terapia terapia = repositorioTerapia.obtenerActivaPorIdPaciente(sesion.getPaciente().getId());
        if( terapia != null) {
            sesion.asignarTerapia(terapia);
        }
    }

    private void validarSesionEnMismoHorario(Sesion sesion){
        List<ResumenSesionDTO> sesionesPendientes = daoSesion.listarPendientes(sesion);
        Optional<ResumenSesionDTO> sesionMismoHorario = sesionesPendientes.stream().
                filter(sesionEvaluar -> sesionEvaluar.getFecha().equals(sesion.getFecha()) && sesionEvaluar.getHora().equals(sesion.getHora())).findFirst();
        if(sesionMismoHorario.isPresent()){
            throw new ExcepcionDuplicidad("Ya hay una sesión agendada en este horario");
        }
    }
}
