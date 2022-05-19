package com.ceiba.terapia.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import com.ceiba.paciente.entidad.Paciente;
import lombok.Getter;

@Getter
public class Terapia {

    private Long id;
    private Paciente paciente;
    private String resumen;
    private Integer periodicidadMes;
    private EstadoTerapia estado;

    public Terapia(Long id, Paciente paciente, String resumen, Integer periodicidadMes, EstadoTerapia estado) {
        validarObligatorio(paciente, "Se requiere el paciente para asignar la terapia");
        validarObligatorio(resumen, "Se requiere el resumen de la terapia");
        validarObligatorio(periodicidadMes, "Se requiere el periodo por mes de la terapia");

        this.id = id;
        this.paciente = paciente;
        this.resumen = resumen;
        this.periodicidadMes = periodicidadMes;
        this.estado = estado;
    }

    public static Terapia reconstruir(Long id, Paciente paciente, String resumen, Integer periodicidadMes, EstadoTerapia estadoTerapia) {
        validarObligatorio(id, "Se requiere el id de la terapia");
        validarObligatorio(estadoTerapia, "Se requiere conocer el estado de la terapia");
        return new Terapia(id, paciente, resumen, periodicidadMes, estadoTerapia);
    }

    public boolean esActiva() {
        return this.estado.equals(EstadoTerapia.ACTIVA);
    }

    public boolean esFinalizada() {
        return this.estado.equals(EstadoTerapia.FINALIZADA);
    }

    public void iniciar() {
        this.estado = EstadoTerapia.ACTIVA;
    }
}
