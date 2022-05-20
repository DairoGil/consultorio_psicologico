package com.ceiba.paciente.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.puerto.dao.DaoSesion;

import java.util.List;

public class ServicioValidarPosibilidadAgendarCita {

    private final DaoSesion daoSesion;

    public ServicioValidarPosibilidadAgendarCita(DaoSesion daoSesion) {
        this.daoSesion = daoSesion;
    }

    public void ejecutar(Paciente paciente){
        ValidadorArgumento.validarObligatorio(paciente, "No se encuentra el paciente");
        validarPosibilidadAgendar(paciente);
    }

    private void validarPosibilidadAgendar(Paciente paciente) {
        List<ResumenSesionDTO> sesionesPendientes = daoSesion.listarPendientesPorIdPaciente(paciente.getId());
        if(paciente.esTipoValoracion() && !sesionesPendientes.isEmpty()){
            throw new ExcepcionDuplicidad("El paciente tiene una sesión de valoración pendiente");
        }else if(paciente.esTipoAsesoria() && paciente.getSesionesAsesoria() == 0){
            throw new ExcepcionDuplicidad("El paciente alcanzó el máximo de sesiones permitidas por asesoría");
        }
    }
}
