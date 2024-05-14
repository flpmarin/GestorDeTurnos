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

-- Asignaciones para el 23 de abril
INSERT INTO
    asignaciones (fecha, trabajador_id, turno_id, posicion_id)
VALUES
    ('2024-04-23', 1, 1, 1),
    -- Trabajador 1 en turno de mañana, puesto 1
    ('2024-04-23', 2, 1, 2),
    ('2024-04-23', 3, 1, 3),
    ('2024-04-23', 4, 2, 1),
    ('2024-04-23', 5, 2, 2),
    ('2024-04-23', 6, 2, 3),
    ('2024-04-23', 7, 2, 4),
    ('2024-04-23', 8, 3, 1),
    ('2024-04-23', 9, 3, 2);

-- Trabajador 9 en turno de noche, puesto 2
-- Asignaciones para el 24 de abril
INSERT INTO
    asignaciones (fecha, trabajador_id, turno_id, posicion_id)
VALUES
    ('2024-04-24', 10, 1, 1),
    -- Trabajador 10 en turno de mañana, puesto 1
    ('2024-04-24', 11, 1, 2),
    ('2024-04-24', 12, 1, 3),
    ('2024-04-24', 13, 2, 1),
    ('2024-04-24', 14, 2, 2),
    ('2024-04-24', 15, 2, 3),
    ('2024-04-24', 16, 2, 4),
    ('2024-04-24', 17, 3, 1),
    ('2024-04-24', 18, 3, 2);

-- Trabajador 18 en turno de noche, puesto 2
-- Asignaciones para el 25 de abril
INSERT INTO
    asignaciones (fecha, trabajador_id, turno_id, posicion_id)
VALUES
    ('2024-04-25', 19, 1, 1),
    -- Trabajador 19 en turno de mañana, puesto 1
    ('2024-04-25', 20, 1, 2),
    ('2024-04-25', 1, 1, 3),
    ('2024-04-25', 2, 2, 1),
    ('2024-04-25', 3, 2, 2),
    ('2024-04-25', 4, 2, 3),
    ('2024-04-25', 5, 2, 4),
    ('2024-04-25', 6, 3, 1),
    ('2024-04-25', 7, 3, 2);