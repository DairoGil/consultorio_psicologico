package com.ceiba.sesion.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.sesion.modelo.entidad.Sesion;
import com.ceiba.sesion.puerto.repositorio.RepositorioSesion;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioSesionMysql implements RepositorioSesion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "sesion", value = "crear")
    private static String sqlCrear;

    public RepositorioSesionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long guardar(Sesion sesion) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", sesion.getId());
        paramSource.addValue("idPaciente", sesion.getPaciente().getId());
        paramSource.addValue("fecha", sesion.getFecha());
        paramSource.addValue("hora", sesion.getHora());
        paramSource.addValue("estado", sesion.getEstado().toString());
        paramSource.addValue("nota", sesion.getNota());
        paramSource.addValue("valor", sesion.getValor());
        paramSource.addValue("idTerapia", sesion.getTerapia());
        return this.customNamedParameterJdbcTemplate.crear(paramSource,sqlCrear);
    }
}
