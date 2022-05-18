SELECT id, nombre, fecha_nacimiento, telefono, sesiones_asesoria, tipo_paciente
FROM paciente
WHERE paciente.id = :idPaciente