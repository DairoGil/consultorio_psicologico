package com.ceiba.terapia.comando;

import com.ceiba.terapia.entidad.EstadoTerapia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoTerapia {

    private Long id;
    private Long idPaciente;
    private String resumen;
    private Integer periodicidadMes;
    private EstadoTerapia estado;
}
