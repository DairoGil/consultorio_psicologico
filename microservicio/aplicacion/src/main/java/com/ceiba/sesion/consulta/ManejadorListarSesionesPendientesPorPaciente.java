package com.ceiba.sesion.consulta;

import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarSesionesPendientesPorPaciente {

    private final DaoSesion daoSesion;

    public ManejadorListarSesionesPendientesPorPaciente(DaoSesion daoSesion) {
        this.daoSesion = daoSesion;
    }

    public List<ResumenSesionDTO> ejecutar(Long idPaciente) {
        return this.daoSesion.listarPendientesPorIdPaciente(idPaciente);
    }
}
