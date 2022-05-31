SELECT id, fecha, hora, estado, nota
FROM sesion
WHERE sesion.fecha = :fecha
AND sesion.hora = :hora