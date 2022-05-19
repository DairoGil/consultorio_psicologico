package com.ceiba.terapia.controlador;

import com.ceiba.terapia.comando.ComandoTerapia;
import com.ceiba.terapia.entidad.EstadoTerapia;

public class ComandoTerapiaTestDataBuilder {

    private Long id;
    private Long idPaciente;
    private String resumen;
    private Integer periodicidadMes;
    private EstadoTerapia estado;

    public ComandoTerapiaTestDataBuilder crearPorDefecto() {
        this.idPaciente = 2l;
        this.resumen = "Razones por las que el paciente ha acudido a la consulta.";
        this.periodicidadMes = 3;
        return this;
    }

    public ComandoTerapia build(){
        return new ComandoTerapia(this.id, this.idPaciente, this.resumen, this.periodicidadMes, this.estado);
    }
}
