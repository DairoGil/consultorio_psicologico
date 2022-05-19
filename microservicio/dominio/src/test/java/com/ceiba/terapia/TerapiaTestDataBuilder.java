package com.ceiba.terapia;

import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.terapia.entidad.EstadoTerapia;
import com.ceiba.terapia.entidad.Terapia;

public class TerapiaTestDataBuilder {

    private Long id;
    private Paciente paciente;
    private String resumen;
    private Integer periodicidadMes;
    private EstadoTerapia estadoTerapia;

    public TerapiaTestDataBuilder conTerapiaPorDefecto() {
        this.id = 2l;
        this.paciente = new PacienteTestDataBuilder().conPacientePorDefecto().build();
        this.resumen = "Razones por las que el paciente ha acudido a la consulta.";
        this.periodicidadMes = 3;
        this.estadoTerapia = EstadoTerapia.ACTIVA;
        return this;
    }

    public TerapiaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TerapiaTestDataBuilder conPaciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public TerapiaTestDataBuilder conResumen(String resumen) {
        this.resumen = resumen;
        return this;
    }

    public TerapiaTestDataBuilder conPeriodicidadMes(Integer periodicidadMes) {
        this.periodicidadMes = periodicidadMes;
        return this;
    }

    public TerapiaTestDataBuilder conEstadoTerapia(EstadoTerapia estadoTerapia) {
        this.estadoTerapia = estadoTerapia;
        return this;
    }

    public Terapia build(){
        return new Terapia(id, paciente, resumen, periodicidadMes, estadoTerapia);
    }

    public Terapia iniciar() {
        Terapia terapia = new Terapia(id, paciente, resumen, periodicidadMes, estadoTerapia);
        terapia.iniciar();
        return terapia;
    }
}
