INSERT INTO paciente(id, nombre, fecha_nacimiento, telefono, sesiones_asesoria, tipo_paciente) values(1,'Paciente 1', TO_DATE('03/05/1995', 'mm/dd/yyyy'), '3134587521', 0, 'VALORACION');
INSERT INTO paciente(id, nombre, fecha_nacimiento, sesiones_asesoria, tipo_paciente) values(2,'Paciente 2', TO_DATE('03/05/1995', 'mm/dd/yyyy'), 0, 'VALORACION');
INSERT INTO paciente(id, nombre, fecha_nacimiento, sesiones_asesoria, tipo_paciente) values(6,'Paciente 6', TO_DATE('02/07/1977', 'mm/dd/yyyy'), 0, 'VALORACION');
INSERT INTO paciente(id, nombre, fecha_nacimiento, sesiones_asesoria, tipo_paciente) values(7,'Paciente 7', TO_DATE('04/26/1970', 'mm/dd/yyyy'), 3, 'ASESORIA');

INSERT INTO sesion(id, id_paciente, fecha, hora, estado, valor)
VALUES (5, 6, TO_DATE('07/13/2022', 'mm/dd/yyyy'), 8, 'PENDIENTE', 80000);

INSERT INTO sesion(id, id_paciente, fecha, hora, estado, valor)
VALUES (9, 7, TO_DATE('07/13/2022', 'mm/dd/yyyy'), 9, 'PENDIENTE', 0);