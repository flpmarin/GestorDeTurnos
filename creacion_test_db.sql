-- Active: 1710450499825@@127.0.0.1@3306
CREATE DATABASE IF NOT EXISTS test_db CHARACTER SET 'utf8mb4' COLLATE utf8mb4_unicode_ci;


use test_db;
CREATE TABLE IF NOT EXISTS departamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS trabajadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    departamento_id INT NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS turnos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    horaInicio TIME NOT NULL,
    horaFin TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS posiciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    departamento_id INT NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ausencias (
    inicio DATE NOT NULL,
    fin DATE NOT NULL,
    trabajador_id INT NOT NULL,
    PRIMARY KEY (trabajador_id, inicio, fin),
    FOREIGN KEY (trabajador_id) REFERENCES trabajadores(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS asignaciones (
    fecha DATE NOT NULL,
    trabajador_id INT NOT NULL,
    turno_id INT NOT NULL,
    posicion_id INT NOT NULL,
    PRIMARY KEY (fecha, trabajador_id),
    FOREIGN KEY (trabajador_id) REFERENCES trabajadores(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (turno_id) REFERENCES turnos(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (posicion_id) REFERENCES posiciones(id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS preferencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    trabajador_id INT NOT NULL,
    FOREIGN KEY (trabajador_id) REFERENCES trabajadores(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS trabajadores_posiciones (
    trabajador_id INT,
    posicion_id INT,
    PRIMARY KEY (trabajador_id, posicion_id),
    FOREIGN KEY (trabajador_id) REFERENCES trabajadores(id),
    FOREIGN KEY (posicion_id) REFERENCES posiciones(id)
);


CREATE TABLE IF NOT EXISTS asignaciones_horas_extras (
    fecha DATE NOT NULL,
    trabajador_id INT NOT NULL,
    turno_id INT NOT NULL,
    posicion_id INT NOT NULL,
    PRIMARY KEY (fecha, trabajador_id),
    FOREIGN KEY (trabajador_id) REFERENCES trabajadores(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (turno_id) REFERENCES turnos(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (posicion_id) REFERENCES posiciones(id) ON UPDATE CASCADE ON DELETE CASCADE
);


-- Este trigger se activa antes de insertar un nuevo trabajador en la tabla 'trabajadores'. Si el nuevo trabajador no tiene un nombre asignado (es decir, si 'NEW.nombre' es NULL), el trigger asigna automáticamente un nombre basado en el ID más grande existente en la tabla de trabajadores más uno, y añade un UUID para garantizar la unicidad. Esto permite que el administrador pueda crear trabajadores sin tener que asignarles un nombre inicialmente. El administrador solo necesita determinar la cantidad de trabajadores a crear y luego puede actualizar los nombres y puestos de los trabajadores según sea necesario. Si el nuevo trabajador ya tiene un nombre asignado al insertarlo, el trigger no cambiará ese nombre. Nota: Este trigger no tiene en cuenta la eliminación de trabajadores, por lo que el número seguirá aumentando incluso si se eliminan trabajadores.
DELIMITER //
CREATE TRIGGER antes_de_insertar_trabajador BEFORE INSERT
ON trabajadores FOR EACH ROW 
BEGIN 
    DECLARE next_trabajador_id INT;
    IF NEW.nombre IS NULL THEN
        IF (SELECT COUNT(*) FROM trabajadores) = 0 THEN
            SET next_trabajador_id = 1;
        ELSE
            SET next_trabajador_id = (SELECT IFNULL(MAX(id), 0) + 1 FROM trabajadores);
        END IF;

        SET NEW.nombre = CONCAT('trabajador_', next_trabajador_id, '_', UUID());
    END IF;
END;
//
DELIMITER ;
