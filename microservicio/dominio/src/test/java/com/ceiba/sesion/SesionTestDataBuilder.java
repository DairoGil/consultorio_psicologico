package com.ceiba.sesion;

import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.terapia.TerapiaTestDataBuilder;
import com.ceiba.terapia.entidad.Terapia;

import java.time.LocalDate;

public class SesionTestDataBuilder {

    private Long id;
    private Paciente paciente;
    private LocalDate fecha;
    private Integer hora;
    private EstadoSesion estado;
    private String nota;
    private Integer valor;
    private Terapia terapia;

    public SesionTestDataBuilder conSesionPorDefecto() {
        this.id = 2l;
        this.paciente = new PacienteTestDataBuilder().conPacientePorDefecto().build();
        this.fecha = Sesion.sumarDias(3);
        this.hora = 8;
        this.estado = EstadoSesion.PENDIENTE;
        this.nota = "Se evidencia avance en el paciente";
        this.valor = 80000;

        return this;
    }

    public SesionTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public SesionTestDataBuilder conPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;
    }

    public SesionTestDataBuilder conFecha(LocalDate fecha){
        this.fecha = fecha;
        return this;
    }

    public SesionTestDataBuilder conHora(Integer hora){
        this.hora = hora;
        return this;
    }

    public SesionTestDataBuilder conEstado(EstadoSesion estado){
        this.estado = estado;
        return this;
    }

    public SesionTestDataBuilder conNota(String nota){
        this.nota = nota;
        return this;
    }

    public SesionTestDataBuilder conValor(Integer valor){
        this.valor = valor;
        return this;
    }

    public SesionTestDataBuilder conTerapia(String terapia){
        this.nota = nota;
        return this;
    }

    public Sesion crear() {
        Sesion sesion = new Sesion(paciente, fecha, hora);
        sesion.agendar();
        return sesion;
    }

    public Sesion reconstruir() {
        return Sesion.reconstruir(id, fecha, hora, estado, nota);
    }
}
