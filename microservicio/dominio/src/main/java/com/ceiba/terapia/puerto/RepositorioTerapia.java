package com.ceiba.terapia.puerto;

import com.ceiba.terapia.entidad.Terapia;

public interface RepositorioTerapia {
    Long guardar(Terapia terapia);
    Terapia obtener(Long id);
    Terapia obtenerActivaPorIdPaciente(Long idPaciente);
}
