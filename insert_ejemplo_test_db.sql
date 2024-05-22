-- Active: 1710450499825@@127.0.0.1@3306@test_db
USE test_db;
--  datos de prueba para el departamento
INSERT INTO
    departamentos (nombre)
VALUES
    ('Almacén');

--  datos de prueba para los trabajadores
INSERT INTO
    trabajadores (nombre, departamento_id)
VALUES
    ('trabajador_1', 1),
    ('trabajador_2', 1),
    ('trabajador_3', 1),
    ('trabajador_4', 1),
    ('trabajador_5', 1),
    ('trabajador_6', 1),
    ('trabajador_7', 1),
    ('trabajador_8', 1),
    ('trabajador_9', 1),
    ('trabajador_10', 1),
    ('trabajador_11', 1),
    ('trabajador_12', 1),
    ('trabajador_13', 1),
    ('trabajador_14', 1),
    ('trabajador_15', 1),
    ('trabajador_16', 1),
    ('trabajador_17', 1),
    ('trabajador_18', 1),
    ('trabajador_19', 1),
    ('trabajador_20', 1);

--  datos de prueba para los turnos
INSERT INTO
    turnos (
        turnoIdGrupo,
        nombre,
        horaInicio,
        horaFin
    )
VALUES
    (1,'Mañana', '06:00:00', '14:00:00'),
    (2,'Tarde', '14:01:00', '22:00:00'),
    (3,'Noche1', '22:01:00', '23:59:59'),
    (3,'Noche2', '00:00:00', '06:00:00');

--  datos de prueba para los puestos
INSERT INTO
    posiciones (nombre, departamento_id)
VALUES
    ('Puesto Mañana 1', 1),
    ('Puesto Mañana 2', 1),
    ('Puesto Mañana 3', 1),
    ('Puesto Tarde 1', 1),
    ('Puesto Tarde 2', 1),
    ('Puesto Tarde 3', 1),
    ('Puesto Tarde 4', 1),
    ('Puesto Noche 1', 1),
    ('Puesto Noche 2', 1),
    ('Puesto Noche 3', 1);

--  datos de prueba para los periodos de ausencia
INSERT INTO
    ausencias (motivo, inicio, fin, trabajador_id)
VALUES
    ('Vacaciones','2024-05-01', '2024-05-05', 1),
    ('Vacaciones','2024-05-20', '2024-05-25', 5),
    ('Vacaciones','2024-05-10', '2024-05-15', 3),
    ('baja','2024-06-01', '2024-06-05', 7),
    ('Vacaciones','2024-06-10', '2024-06-15', 9),
    ('baja','2024-06-20', '2024-06-25', 11),
    ('baja','2024-07-01', '2024-07-05', 13),
    ('Vacaciones','2024-07-10', '2024-07-15', 15),
    ('Vacaciones','2024-07-20', '2024-07-25', 17);

--  datos de prueba para las asignaciones
    INSERT INTO asignaciones (fecha_inicio, fecha_fin, hora_inicio, hora_fin, trabajador_id, turno_id, posicion_id)
VALUES
    ('2022-01-01', '2022-01-01', '08:00:00', '16:00:00', 1, 1, 1),
    ('2022-01-02', '2022-01-02', '16:00:00', '00:00:00', 2, 2, 2),
    ('2022-01-03', '2022-01-03', '00:00:00', '08:00:00', 3, 3, 3),
    ('2022-01-04', '2022-01-04', '08:00:00', '16:00:00', 4, 1, 1),
    ('2022-01-05', '2022-01-05', '16:00:00', '00:00:00', 5, 2, 2),
    ('2022-01-06', '2022-01-06', '00:00:00', '08:00:00', 6, 3, 3),
    ('2022-01-07', '2022-01-07', '08:00:00', '16:00:00', 7, 1, 1),
    ('2022-01-08', '2022-01-08', '16:00:00', '00:00:00', 8, 2, 2),
    ('2022-01-09', '2022-01-09', '00:00:00', '08:00:00', 9, 3, 3),
    ('2022-01-10', '2022-01-10', '08:00:00', '16:00:00', 10, 1, 1),
    ('2022-01-11', '2022-01-11', '16:00:00', '00:00:00', 11, 2, 2),
    ('2022-01-12', '2022-01-12', '00:00:00', '08:00:00', 12, 3, 3);