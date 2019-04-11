-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 10-04-2019 a las 11:48:23
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `escapedaw`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `NIF` varchar(9) NOT NULL,
  `ID_EMPLE` varchar(4) DEFAULT NULL,
  `NOMBRE` varchar(15) NOT NULL,
  `APELLIDO` varchar(10) DEFAULT NULL,
  `DIRECCION` varchar(20) DEFAULT NULL,
  `TELEFONO` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`NIF`),
  KEY `ID_EMPLE` (`ID_EMPLE`),
  KEY `ID_EMPLE_2` (`ID_EMPLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`NIF`, `ID_EMPLE`, `NOMBRE`, `APELLIDO`, `DIRECCION`, `TELEFONO`) VALUES
('55555555H', 'EM01', 'Leónidas', NULL, 'Esparta', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE IF NOT EXISTS `credenciales` (
  `USUARIO` varchar(4) NOT NULL,
  `PASSWORD` varchar(15) NOT NULL,
  `ROL` char(1) NOT NULL,
  PRIMARY KEY (`USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE IF NOT EXISTS `empleados` (
  `NIF` varchar(9) DEFAULT NULL,
  `ID` varchar(4) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `TELEFONO` varchar(9) NOT NULL,
  `DIRECCION` varchar(30) NOT NULL,
  `SUELDO` int(10) NOT NULL DEFAULT '0',
  `ID_JEFE` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NIF` (`NIF`),
  KEY `ID_JEFE` (`ID_JEFE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`NIF`, `ID`, `NOMBRE`, `APELLIDO`, `TELEFONO`, `DIRECCION`, `SUELDO`, `ID_JEFE`) VALUES
('51478525N', 'EM01', 'Leo', 'Kylo', '666778899', 'Calle Alegría 1', 800, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jefes`
--

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE IF NOT EXISTS `pistas` (
  `COD_PISTA` varchar(10) NOT NULL DEFAULT '',
  `NSALA` varchar(9) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL,
  `SOLICITADO` tinyint(1) NOT NULL DEFAULT '0',
  `CONFIRMADO` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`COD_PISTA`),
  KEY `NSALA` (`NSALA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE IF NOT EXISTS `reservas` (
  `COD_RESERVA` varchar(10) NOT NULL DEFAULT '',
  `FECHA` date NOT NULL,
  `ID_EMPLE` varchar(4) NOT NULL,
  `NIF_CLIENTE` varchar(9) NOT NULL,
  `NPERSONAS` int(2) NOT NULL,
  PRIMARY KEY (`COD_RESERVA`),
  KEY `ID_EMPLE` (`ID_EMPLE`),
  KEY `NIF_CLIENTE` (`NIF_CLIENTE`),
  KEY `NPERSONAS` (`NPERSONAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

CREATE TABLE IF NOT EXISTS `salas` (
  `NSALA` varchar(9) NOT NULL,
  `ID_EMPLE` varchar(4) DEFAULT NULL,
  `ID_JEFE` varchar(4) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `DIFICULTAD` varchar(10) NOT NULL,
  `NPERSONAS` int(2) NOT NULL,
  `PRECIO` int(10) NOT NULL DEFAULT '15',
  PRIMARY KEY (`NSALA`),
  KEY `ID_EMPLE` (`ID_EMPLE`),
  KEY `ID_JEFE` (`ID_JEFE`),
  KEY `NPERSONAS` (`NPERSONAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

CREATE TABLE IF NOT EXISTS `visitas` (
  `NSALA` varchar(9) NOT NULL,
  `ID_EMPLE` varchar(4) NOT NULL,
  `NIF_CLIENTE` varchar(9) NOT NULL,
  `FECHA` date NOT NULL,
  `TIEMPO` date NOT NULL,
  `NPERSONAS` int(2) NOT NULL,
  `IMPORTE` int(2) NOT NULL,
  KEY `ID_EMPLE` (`ID_EMPLE`,`NIF_CLIENTE`),
  KEY `NIF_CLIENTE` (`NIF_CLIENTE`),
  KEY `NSALA` (`NSALA`),
  KEY `NPERSONAS` (`NPERSONAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`NIF_CLIENTE`) REFERENCES `clientes` (`NIF`);

--
-- Filtros para la tabla `salas`
--
ALTER TABLE `salas`
  ADD CONSTRAINT `salas_ibfk_1` FOREIGN KEY (`ID_EMPLE`) REFERENCES `empleados` (`ID`),
  ADD CONSTRAINT `salas_ibfk_2` FOREIGN KEY (`ID_JEFE`) REFERENCES `empleados` (`ID`);

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `visitas_ibfk_1` FOREIGN KEY (`ID_EMPLE`) REFERENCES `empleados` (`ID`),
  ADD CONSTRAINT `visitas_ibfk_2` FOREIGN KEY (`NIF_CLIENTE`) REFERENCES `clientes` (`NIF`),
  ADD CONSTRAINT `visitas_ibfk_3` FOREIGN KEY (`NSALA`) REFERENCES `salas` (`NSALA`),
  ADD CONSTRAINT `visitas_ibfk_4` FOREIGN KEY (`NPERSONAS`) REFERENCES `reservas` (`NPERSONAS`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
