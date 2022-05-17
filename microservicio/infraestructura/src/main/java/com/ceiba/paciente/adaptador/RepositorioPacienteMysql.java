package com.ceiba.paciente.adaptador;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPacienteMysql implements RepositorioPaciente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "paciente", value = "crear")
    private static String sqlCrear;

    public RepositorioPacienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long guardar(Paciente paciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", paciente.getId());
        paramSource.addValue("nombre", paciente.getNombre());
        paramSource.addValue("fechaNacimiento", paciente.getFechaNacimiento());
        paramSource.addValue("telefono", paciente.getTelefono());
        paramSource.addValue("tipoPaciente", paciente.getTipoPaciente().toString());
        return this.customNamedParameterJdbcTemplate.crear(paramSource,sqlCrear);
    }

    @Override
    public Paciente obtener(Long id) {
        return null;
    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
