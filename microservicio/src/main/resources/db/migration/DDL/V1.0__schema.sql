create table paciente (
 id int(11) not null,
 nombre varchar(100) not null,
 fecha_nacimiento DATE not null,
 telefono varchar(15),
 sesiones_asesoria int(1) not null,
 tipo_paciente varchar(20) not null,
 primary key (id)
);
