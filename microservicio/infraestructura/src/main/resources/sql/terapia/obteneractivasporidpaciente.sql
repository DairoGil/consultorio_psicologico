SELECT id, id_paciente, resumen, periodicidad_mes, estado
FROM terapia
WHERE terapia.id_paciente = :id_paciente
AND terapia.estado = :estado