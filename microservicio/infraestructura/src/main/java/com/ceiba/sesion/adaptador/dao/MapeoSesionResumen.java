package com.ceiba.sesion.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class MapeoSesionResumen implements RowMapper<ResumenSesionDTO>, MapperResult {
    @Override
    public ResumenSesionDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        LocalDate fecha = extraerLocalDate(resultSet, "fecha");
        Integer hora = resultSet.getInt("hora");
        EstadoSesion estado = EstadoSesion.valueOf(resultSet.getString("estado"));
        String nota = resultSet.getString("nota");
        return new ResumenSesionDTO(id, fecha, hora, estado, nota);
    }
}
