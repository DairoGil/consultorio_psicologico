package com.ceiba.paciente.adaptador;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPacienteMysql implements RepositorioPaciente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoPaciente mapeoPaciente;
    private static final String TIPO_PACIENTE_PARAM_NAME = "tipoPaciente";

    @SqlStatement(namespace = "paciente", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "paciente", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "paciente", value = "actualizartipo")
    private static String sqlActualizarTipo;

    public RepositorioPacienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoPaciente mapeoPaciente) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoPaciente = mapeoPaciente;
    }

    @Override
    public Long guardar(Paciente paciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", paciente.getId());
        paramSource.addValue("nombre", paciente.getNombre());
        paramSource.addValue("fechaNacimiento", paciente.getFechaNacimiento());
        paramSource.addValue("telefono", paciente.getTelefono());
        paramSource.addValue("sesionesAsesoria", paciente.getSesionesAsesoria());
        paramSource.addValue(TIPO_PACIENTE_PARAM_NAME, paciente.getTipoPaciente().toString());
        return this.customNamedParameterJdbcTemplate.crear(paramSource,sqlCrear);
    }

    @Override
    public Paciente obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPaciente", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(()-> this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId,paramSource, mapeoPaciente));
    }

    @Override
    public void actualizarTipo(Paciente paciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", paciente.getId());
        paramSource.addValue(TIPO_PACIENTE_PARAM_NAME, paciente.getTipoPaciente().toString());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarTipo, paramSource);
    }

    @Override
    public void actualizarAsesoria(Paciente paciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", paciente.getId());
        paramSource.addValue(TIPO_PACIENTE_PARAM_NAME, paciente.getTipoPaciente().toString());
        paramSource.addValue("sesionesAsesoria", paciente.getSesionesAsesoria());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarTipo, paramSource);
    }
}
