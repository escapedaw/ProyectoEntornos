-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 14-05-2019 a las 07:47:21
-- Versión del servidor: 5.7.24
-- Versión de PHP: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `escapedaw`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `NIF` varchar(9) NOT NULL,
  `ID` varchar(4) NOT NULL,
  `ID_EMPLE` varchar(4) DEFAULT NULL,
  `NOMBRE` varchar(15) NOT NULL,
  `APELLIDO` varchar(10) DEFAULT NULL,
  `DIRECCION` varchar(20) DEFAULT NULL,
  `TELEFONO` varchar(9) NOT NULL,
  PRIMARY KEY (`NIF`),
  KEY `ID_EMPLE` (`ID_EMPLE`),
  KEY `ID_EMPLE_2` (`ID_EMPLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`NIF`, `ID`, `ID_EMPLE`, `NOMBRE`, `APELLIDO`, `DIRECCION`, `TELEFONO`) VALUES
('11111111A', 'CL1', 'EM1', 'Pablo', 'Martín', 'Calle Hortaleza', '684336603'),
('22222222B', 'CL2', 'EM1', 'Marcos', 'Grao', 'Calle Wibble', '608488772'),
('33333333C', 'CL3', 'EM2', 'Begoña', 'López', 'Calle San Blas', '666554433');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

DROP TABLE IF EXISTS `credenciales`;
CREATE TABLE IF NOT EXISTS `credenciales` (
  `USUARIO` varchar(4) NOT NULL,
  `PASSWORD` varchar(40) NOT NULL,
  `ROL` varchar(1) NOT NULL,
  PRIMARY KEY (`USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`USUARIO`, `PASSWORD`, `ROL`) VALUES
('CL1', 'e48bf9dad3f5d442f9d14d931be6f43e', 'C'),
('CL2', '81cf6107131a3583e2b0b762cb9c2862', 'C'),
('CL3', '9d3d9048db16a7eee539e93e3618cbe7', 'C'),
('EM1', 'a57b8491d1d8fc1014dd54bcf83b130a', 'E'),
('EM2', '66cc12e3c6d68de3fef6de89cf033f67', 'E'),
('JE1', '3b98e2dffc6cb06a89dcb0d5c60a0206', 'J');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `NIF` varchar(9) DEFAULT NULL,
  `ID` varchar(4) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `TELEFONO` varchar(9) NOT NULL,
  `DIRECCION` varchar(30) NOT NULL,
  `SUELDO` int(10) NOT NULL,
  `ID_JEFE` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NIF` (`NIF`),
  KEY `ID_JEFE` (`ID_JEFE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`NIF`, `ID`, `NOMBRE`, `APELLIDO`, `TELEFONO`, `DIRECCION`, `SUELDO`, `ID_JEFE`) VALUES
('51487420B', 'EM1', 'Sandra', 'Lobón', '659697905', 'Calle la Marina', 700, 'JE1'),
('51988745G', 'EM2', 'José', 'González', '648787851', 'Calle la Paloma', 650, 'JE1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jefes`
--

DROP TABLE IF EXISTS `jefes`;
CREATE TABLE IF NOT EXISTS `jefes` (
  `NIF` varchar(9) DEFAULT NULL,
  `ID` varchar(4) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `TELEFONO` varchar(9) NOT NULL,
  `SUELDO` int(10) NOT NULL DEFAULT '0',
  `DIRECCION` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NIF` (`NIF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `jefes`
--

INSERT INTO `jefes` (`NIF`, `ID`, `NOMBRE`, `APELLIDO`, `TELEFONO`, `SUELDO`, `DIRECCION`) VALUES
('52893478X', 'JE1', 'Daniel', 'González', '669518300', 2000, 'Calle Pegaso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

DROP TABLE IF EXISTS `pistas`;
CREATE TABLE IF NOT EXISTS `pistas` (
  `COD_PISTA` varchar(10) NOT NULL DEFAULT '',
  `NSALA` varchar(9) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL,
  `SOLICITADO` tinyint(1) NOT NULL DEFAULT '0',
  `CONFIRMADO` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COD_PISTA`),
  KEY `NSALA` (`NSALA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pistas`
--

INSERT INTO `pistas` (`COD_PISTA`, `NSALA`, `DESCRIPCION`, `SOLICITADO`, `CONFIRMADO`) VALUES
('PI1', 'SA1', 'Mira detrás del cuadro', 0, 0),
('PI2', 'SA1', 'Utiliza la luz', 0, 0),
('PI3', 'SA2', 'Levanta la alfombra', 0, 0),
('PI4', 'SA2', 'Abre el grifo', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE IF NOT EXISTS `reservas` (
  `COD_RESERVA` varchar(4) NOT NULL,
  `FECHA` datetime NOT NULL,
  `NSALA` varchar(9) NOT NULL,
  `ID_EMPLE` varchar(4) NOT NULL,
  `NIF_CLIENTE` varchar(9) NOT NULL,
  `NPERSONAS` int(10) NOT NULL,
  `IMPORTE` int(3) NOT NULL,
  PRIMARY KEY (`COD_RESERVA`),
  UNIQUE KEY `FECHA_NSALA_UNI` (`FECHA`,`NSALA`),
  KEY `ID_EMPLE` (`ID_EMPLE`),
  KEY `NIF_CLIENTE` (`NIF_CLIENTE`),
  KEY `NSALA` (`NSALA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

DROP TABLE IF EXISTS `salas`;
CREATE TABLE IF NOT EXISTS `salas` (
  `NSALA` varchar(9) NOT NULL,
  `ID_EMPLE` varchar(4) DEFAULT NULL,
  `ID_JEFE` varchar(4) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `DIFICULTAD` varchar(10) NOT NULL,
  `NPERSONAS` int(10) NOT NULL,
  `PRECIO` int(10) NOT NULL DEFAULT '15',
  PRIMARY KEY (`NSALA`),
  KEY `ID_EMPLE` (`ID_EMPLE`),
  KEY `ID_JEFE` (`ID_JEFE`),
  KEY `NPERSONAS` (`NPERSONAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`NSALA`, `ID_EMPLE`, `ID_JEFE`, `TIPO`, `DIFICULTAD`, `NPERSONAS`, `PRECIO`) VALUES
('SA1', 'EM1', 'JE1', 'Miedo', 'Alta', 6, 15),
('SA2', 'EM2', 'JE1', 'Risa', 'Baja', 7, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

DROP TABLE IF EXISTS `visitas`;
CREATE TABLE IF NOT EXISTS `visitas` (
  `COD_VISITA` varchar(4) NOT NULL,
  `NSALA` varchar(9) NOT NULL,
  `ID_EMPLE` varchar(4) NOT NULL,
  `NIF_CLIENTE` varchar(9) NOT NULL,
  `FECHA` datetime NOT NULL,
  `TIEMPO` double NOT NULL,
  `NPERSONAS` int(2) NOT NULL,
  `IMPORTE` int(3) NOT NULL,
  PRIMARY KEY (`COD_VISITA`),
  KEY `ID_EMPLE` (`ID_EMPLE`,`NIF_CLIENTE`),
  KEY `NIF_CLIENTE` (`NIF_CLIENTE`),
  KEY `NSALA` (`NSALA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `visitas`
--

INSERT INTO `visitas` (`COD_VISITA`, `NSALA`, `ID_EMPLE`, `NIF_CLIENTE`, `FECHA`, `TIEMPO`, `NPERSONAS`, `IMPORTE`) VALUES
('VI1', 'SA1', 'EM1', '11111111A', '2019-05-14 12:45:00', 0, 4, 60);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`ID_EMPLE`) REFERENCES `empleados` (`ID`);

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`ID_JEFE`) REFERENCES `jefes` (`ID`);

--
-- Filtros para la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD CONSTRAINT `pistas_ibfk_1` FOREIGN KEY (`NSALA`) REFERENCES `salas` (`NSALA`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`ID_EMPLE`) REFERENCES `empleados` (`ID`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`NIF_CLIENTE`) REFERENCES `clientes` (`NIF`),
  ADD CONSTRAINT `reservas_ibfk_3` FOREIGN KEY (`NSALA`) REFERENCES `salas` (`NSALA`);

--
-- Filtros para la tabla `salas`
--
ALTER TABLE `salas`
  ADD CONSTRAINT `salas_ibfk_1` FOREIGN KEY (`ID_EMPLE`) REFERENCES `empleados` (`ID`),
  ADD CONSTRAINT `salas_ibfk_3` FOREIGN KEY (`ID_JEFE`) REFERENCES `jefes` (`ID`);

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `visitas_ibfk_1` FOREIGN KEY (`ID_EMPLE`) REFERENCES `empleados` (`ID`),
  ADD CONSTRAINT `visitas_ibfk_2` FOREIGN KEY (`NIF_CLIENTE`) REFERENCES `clientes` (`NIF`),
  ADD CONSTRAINT `visitas_ibfk_3` FOREIGN KEY (`NSALA`) REFERENCES `salas` (`NSALA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
