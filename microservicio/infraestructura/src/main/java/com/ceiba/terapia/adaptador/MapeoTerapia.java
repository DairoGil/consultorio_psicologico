package com.ceiba.terapia.adaptador;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.terapia.entidad.EstadoTerapia;
import com.ceiba.terapia.entidad.Terapia;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoTerapia implements RowMapper<Terapia>, MapperResult {

    private final RepositorioPaciente repositorioPaciente;

    public MapeoTerapia(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    @Override
    public Terapia mapRow(ResultSet resulSet, int rowNum) throws SQLException {
        Long id = resulSet.getLong("id");
        Long idPaciente = resulSet.getLong("id_paciente");
        String resumen = resulSet.getString("resumen");
        Integer periodicidadMes = resulSet.getInt("periodicidad_mes");
        EstadoTerapia estadoTerapia = EstadoTerapia.valueOf(resulSet.getString("estado"));
        return Terapia.reconstruir(id, repositorioPaciente.obtener(idPaciente), resumen, periodicidadMes, estadoTerapia);
    }
}
