package com.ceiba.configuracion;

import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsesorar;
import com.ceiba.paciente.servicio.ServicioAsignarTerapia;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import com.ceiba.paciente.servicio.ServicioValidarPosibilidadAgendarCita;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import com.ceiba.sesion.puerto.repositorio.RepositorioSesion;
import com.ceiba.sesion.servicio.ServicioAgendar;
import com.ceiba.terapia.puerto.RepositorioTerapia;
import com.ceiba.terapia.servicio.ServicioIniciar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioRegistrar servicioRegistrar(RepositorioPaciente repositorioPaciente) {
        return new ServicioRegistrar(repositorioPaciente);
    }

    @Bean
    public ServicioAsesorar servicioAsesorar(RepositorioPaciente repositorioPaciente) {
        return new ServicioAsesorar(repositorioPaciente);
    }

    @Bean
    public ServicioAsignarTerapia servicioAsignarTerapia(RepositorioPaciente repositorioPaciente) {
        return new ServicioAsignarTerapia(repositorioPaciente);
    }

    @Bean
    public ServicioIniciar servicioIniciar(RepositorioTerapia repositorioTerapia, ServicioAsignarTerapia servicioAsignarTerapia) {
        return new ServicioIniciar(repositorioTerapia, servicioAsignarTerapia);
    }

    @Bean
    public ServicioAgendar servicioAgendar(RepositorioSesion repositorioSesion, DaoSesion daoSesion, RepositorioTerapia repositorioTerapia, ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita) {
        return new ServicioAgendar(repositorioSesion, daoSesion, repositorioTerapia, servicioValidarPosibilidadAgendarCita);
    }

    @Bean
    public ServicioValidarPosibilidadAgendarCita servicioValidarPosibilidadAgendarCita(DaoSesion daoSesion) {
        return new ServicioValidarPosibilidadAgendarCita(daoSesion);
    }
}
