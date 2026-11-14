-- Dracarys Gym: base de datos para el módulo de socios (MySQL 8+).
-- Ejecute este archivo en MySQL Workbench o el cliente mysql.
CREATE DATABASE IF NOT EXISTS dracarys_gym
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dracarys_gym;

CREATE TABLE IF NOT EXISTS socios (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  documento VARCHAR(20) NOT NULL UNIQUE,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  correo VARCHAR(150) NULL,
  telefono VARCHAR(30) NULL,
  plan ENUM('Mensual', 'Trimestral', 'Anual') NOT NULL DEFAULT 'Mensual',
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
