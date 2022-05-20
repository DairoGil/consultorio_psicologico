create table paciente (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 fecha_nacimiento DATE not null,
 telefono varchar(15),
 sesiones_asesoria int(1) not null,
 tipo_paciente varchar(20) not null,
 primary key (id)
);

create table terapia (
 id int(11) not null auto_increment,
 id_paciente int(11) not null,
 resumen varchar(2000) not null,
 periodicidad_mes int(1) not null,
 estado varchar(20) not null,
 primary key (id)
);

create table sesion (
 id int(11) not null auto_increment,
 id_paciente int(11) not null,
 fecha DATE not null,
 hora int(2) not null,
 estado varchar(20) not null,
 nota varchar(2000),
 valor int(6) not null,
 id_terapia int(11),
 primary key (id)
);

ALTER TABLE terapia
ADD CONSTRAINT paciente_terapia_fk
  FOREIGN KEY (id_paciente)
  REFERENCES paciente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE sesion
ADD CONSTRAINT terapia_fk
  FOREIGN KEY (id_paciente)
  REFERENCES paciente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE sesion
ADD CONSTRAINT paciente_fk
  FOREIGN KEY (id_terapia)
  REFERENCES terapia (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;