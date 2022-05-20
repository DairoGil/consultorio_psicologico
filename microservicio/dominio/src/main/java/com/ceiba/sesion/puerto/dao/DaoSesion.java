package com.ceiba.sesion.puerto.dao;

import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;

import java.util.List;

public interface DaoSesion {

    List<ResumenSesionDTO> obtenerPendientesPorIdPaciente(Long idPaciente);
}
