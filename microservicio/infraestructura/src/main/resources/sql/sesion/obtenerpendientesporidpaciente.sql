SELECT id, fecha, hora, estado, nota
FROM sesion
WHERE sesion.id_paciente = :id_paciente
AND sesion.estado = :estado