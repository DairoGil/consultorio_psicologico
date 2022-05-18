package com.ceiba.configuracion;

import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioAsesorar;
import com.ceiba.paciente.servicio.ServicioRegistrar;
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
}
