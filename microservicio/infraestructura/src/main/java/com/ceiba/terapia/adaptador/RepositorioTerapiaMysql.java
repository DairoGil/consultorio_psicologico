package com.ceiba.terapia.adaptador;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.terapia.entidad.EstadoTerapia;
import com.ceiba.terapia.entidad.Terapia;
import com.ceiba.terapia.puerto.RepositorioTerapia;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioTerapiaMysql implements RepositorioTerapia {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoTerapia mapeoTerapia;

    @SqlStatement(namespace = "terapia", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "terapia", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "terapia", value = "obteneractivasporidpaciente")
    private static String sqlObtenerActivasPorIdPaciente;

    public RepositorioTerapiaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoTerapia mapeoTerapia) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoTerapia = mapeoTerapia;
    }

    @Override
    public Long guardar(Terapia terapia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", terapia.getId());
        paramSource.addValue("idPaciente", terapia.getPaciente().getId());
        paramSource.addValue("resumen", terapia.getResumen());
        paramSource.addValue("periodicidadMes", terapia.getPeriodicidadMes());
        paramSource.addValue("estado", terapia.getEstado().toString());
        return this.customNamedParameterJdbcTemplate.crear(paramSource,sqlCrear);
    }

    @Override
    public Terapia obtener(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idTerapia", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(()-> this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId,paramSource, mapeoTerapia));
    }

    @Override
    public void actualizarEstado(Terapia terapia) {

    }

    @Override
    public List<Terapia> obtenerPorIdPaciente(Long idPaciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_paciente", idPaciente);
        paramSource.addValue("estado", EstadoTerapia.ACTIVA.toString());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerActivasPorIdPaciente, paramSource, mapeoTerapia);
    }
}
