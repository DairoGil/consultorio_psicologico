package com.ceiba.sesion.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.terapia.entidad.Terapia;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Sesion {

    private static final List<Integer> HORAS_PERMITIDAS = Arrays.asList(8, 9, 10, 11, 14, 15, 16, 17);

    private Long id;
    private Paciente paciente;
    private LocalDate fecha;
    private Integer hora;
    private EstadoSesion estado;
    private String nota;
    private Integer valor;
    private Terapia terapia;

    public Sesion(Paciente paciente, LocalDate fecha, Integer hora) {
        validarObligatorio(paciente, "Se requiere el paciente que asistira a la sesion");
        validarObligatorio(fecha, "Se requiere la fecha de la sesion");
        validarObligatorio(hora, "Se requiere la hora de la sesion");
        validarHorarioAtencion(fecha, hora);

        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Sesion(Long id, LocalDate fecha, Integer hora, EstadoSesion estado, String nota) {
        validarObligatorio(id, "Se requiere el id de la sesion");
        validarObligatorio(fecha, "Se requiere la fecha de la sesion");
        validarObligatorio(hora, "Se requiere la hora de la sesion");
        validarObligatorio(estado, "Se requiere el estado de la sesion");

        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.nota = nota;
    }

    private void validarHorarioAtencion(LocalDate fecha, Integer hora) {
        validarSiEsFinDeSemana(fecha);
        validarSiEsHoraPermitida(hora);
    }

    private void validarSiEsHoraPermitida(Integer hora) {
        if(!HORAS_PERMITIDAS.contains(hora)){
            throw new ExcepcionValorInvalido("La hora ingresada no esta dentro del horario de atención");
        }
    }

    private void validarSiEsFinDeSemana(LocalDate fecha) {
        if(fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY){
            throw new ExcepcionValorInvalido("No se presta servicio los fines de semana");
        }
    }

    private void validarDiasAnticipacion() {
        LocalDate fechaMinima = sumarDias(3);
        if(this.fecha.isBefore(fechaMinima)){
            throw new ExcepcionValorInvalido("Requiere al menos dos dias de anticipación para realizar este proceso");
        }
    }

    public void agendar() {
        validarDiasAnticipacion();
        this.valor = calcularValor();
        this.estado = EstadoSesion.PENDIENTE;
    }

    private int calcularValor(){
        if(this.paciente.esTipoValoracion()){
            return 80000;
        }else if(this.paciente.esTipoTerapia()){
            return 60000;
        }
        return 0;
    }

    public void cumplir(String nota) {
        this.nota = nota;
        this.estado = EstadoSesion.CUMPLIDA;
    }

    public void asignarTerapia (Terapia terapia){
        this.terapia = terapia;
    }

    public boolean esPendiente() {
        return this.estado.equals(EstadoSesion.PENDIENTE);
    }

    public boolean esCumplida() {
        return this.estado.equals(EstadoSesion.CUMPLIDA);
    }

    public static Sesion reconstruir(Long id, LocalDate fecha, Integer hora, EstadoSesion estado, String nota) {
        return new Sesion(id, fecha, hora, estado, nota);
    }

    public static LocalDate sumarDias(int diasASumar) {

        LocalDate fechaValidar = LocalDate.now();
        int diasSumados = 0;

        while (diasSumados < diasASumar){
            fechaValidar = fechaValidar.plusDays(1);
            if(fechaValidar.getDayOfWeek() != DayOfWeek.SATURDAY && fechaValidar.getDayOfWeek() != DayOfWeek.SUNDAY){
                diasSumados = diasSumados + 1;
            }
        }

        return fechaValidar;
    }
}
