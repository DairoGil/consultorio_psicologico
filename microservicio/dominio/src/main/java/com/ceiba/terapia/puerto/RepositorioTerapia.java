package com.ceiba.terapia.puerto;

import com.ceiba.terapia.entidad.Terapia;

import java.util.List;

public interface RepositorioTerapia {
    Long guardar(Terapia terapia);
    Terapia obtener(Long id);
    void actualizarEstado(Terapia terapia);
    List<Terapia> obtenerPorIdPaciente(Long idPaciente);
}
