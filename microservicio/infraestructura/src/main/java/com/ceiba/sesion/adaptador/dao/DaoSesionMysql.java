package com.ceiba.sesion.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.sesion.modelo.dto.ResumenSesionDTO;
import com.ceiba.sesion.modelo.entidad.EstadoSesion;
import com.ceiba.sesion.puerto.dao.DaoSesion;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoSesionMysql implements DaoSesion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoSesionResumen mapeoSesionResumen;

    @SqlStatement(namespace = "sesion", value = "obtenerpendientesporidpaciente")
    private static String sqlObtenerPendientesPorIdPaciente;

    public DaoSesionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoSesionResumen mapeoSesionResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoSesionResumen = mapeoSesionResumen;
    }

    @Override
    public List<ResumenSesionDTO> listarPendientesPorIdPaciente(Long idPaciente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_paciente", idPaciente);
        paramSource.addValue("estado", EstadoSesion.PENDIENTE.toString());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .query(sqlObtenerPendientesPorIdPaciente, paramSource, mapeoSesionResumen);
    }
}
