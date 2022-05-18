package com.ceiba.paciente.adaptador;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class MapeoPaciente implements RowMapper<Paciente>, MapperResult {

    @Override
    public Paciente mapRow(ResultSet resulSet, int rowNum) throws SQLException {
        Long id = resulSet.getLong("id");
        String nombre = resulSet.getString("nombre");
        LocalDate fechaNacimiento = extraerLocalDate(resulSet, "fecha_nacimiento");
        String telefono = resulSet.getString("telefono");
        Integer sesionesAsesoria = resulSet.getInt("sesiones_asesoria");
        TipoPaciente tipoPaciente = TipoPaciente.valueOf(resulSet.getString("tipo_paciente"));
        return Paciente.reconstruir(id, nombre, fechaNacimiento, telefono, sesionesAsesoria, tipoPaciente);
    }
}
