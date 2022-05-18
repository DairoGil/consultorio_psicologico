UPDATE paciente
SET tipo_paciente = :tipoPaciente,
    sesiones_asesoria = :sesionesAsesoria
WHERE id = :id