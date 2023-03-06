-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-06-2022 a las 02:46:54
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `muebles`
--
CREATE DATABASE IF NOT EXISTS `muebles` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `muebles`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoría`
--

CREATE TABLE `categoría` (
  `id_categoría` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoría`
--

INSERT INTO `categoría` (`id_categoría`, `nombre`) VALUES
(1, 'muebles'),
(2, 'closes'),
(12, 'sieter');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cedula` int(15) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellidos` varchar(40) NOT NULL,
  `telefono` varchar(11) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cedula`, `nombre`, `apellidos`, `telefono`, `direccion`, `email`) VALUES
(2, 'sdfh', 'dsgh', '43', 'dfg', 'dfsh'),
(3, 'sdf', 's', '4', 'adf', 'zxfzgf'),
(11273, 'Charity', 'Farmer', '', 'Ap #931-4651 Ut Ave', 'sit.amet@google.com'),
(102007, 'Quemby', 'Lee', '', 'Ap #403-2351 Lobortis Road', 'at@yahoo.org'),
(129309, 'Jenette', 'Dean', '78957421', '963-1673 Ut St.', 'sapien@aol.net'),
(170112, 'Lynn', 'Langley', '', '541-2967 Rutrum St.', 'feugiat.tellus.lorem@outlook.com'),
(244043, 'Zoe', 'Mosley', '', '7581 Aliquet, Av.', 'fames.ac@yahoo.ca'),
(303241, 'Hoyt', 'Peters', '', '822-5571 Sollicitudin Ave', 'a.sollicitudin@outlook.org'),
(309790, 'Raja', 'Sweeney', '', '217-1730 Blandit Avenue', 'penatibus.et@protonmail.org'),
(313191, 'Samuel', 'Black', '741867', 'Ap #335-8706 Mattis. Rd.', 'mauris.rhoncus@yahoo.net'),
(347868, 'Chandler', 'Stein', '', '4746 Odio Street', 'eget.dictum.placerat@hotmail.edu'),
(357019, 'Molly', 'Barrett', '', '827-6296 Sed Avenue', 'magna@yahoo.ca'),
(384106, 'Leslie', 'Tate', '', '8439 Ut St.', 'rutrum.magna.cras@outlook.org'),
(388260, 'Branden', 'Sharpe', '601584957', 'Ap #345-7004 Non, St.', 'mus@google.edu'),
(418884, 'Kasper', 'Stephenson', '', 'P.O. Box 116, 1908 Ac Street', 'dictum@aol.couk'),
(434335, 'Martin', 'Thornton', '', 'Ap #144-6551 Nisl. Road', 'orci@protonmail.com'),
(460597, 'Lenore', 'Flynn', '', 'Ap #396-452 Erat Av.', 'nunc.sed@yahoo.com'),
(511930, 'Amity', 'Kaufman', '', 'Ap #859-4086 Tellus, St.', 'vitae.aliquet.nec@aol.org'),
(520594, 'Yeo', 'Short', '74172878', '403-2561 Suspendisse St.', 'egestas@yahoo.net'),
(534573, 'Cameron', 'Dawson', '', '375-7315 Tincidunt Rd.', 'turpis.vitae@hotmail.org'),
(621253, 'Herman', 'Woods', '', 'Ap #462-6607 Augue St.', 'tempus.scelerisque@outlook.org'),
(659393, 'Hilel', 'Cooley', '', '2254 Neque. Rd.', 'magna.sed.eu@outlook.net'),
(724197, 'Meghan', 'Houston', '', '177-1910 Eu Avenue', 'suspendisse.eleifend.cras@aol.net'),
(735506, 'Laura', 'Joyce', '', '307-8823 Donec Av.', 'praesent.eu@google.edu'),
(888671, 'Gavin', 'Mcknight', '', '130 Ut Ave', 'dictum@aol.net'),
(889609, 'Jamal', 'Cochran', '', 'P.O. Box 247, 4080 Duis Ave', 'aenean.massa@icloud.com'),
(889924, 'Anne', 'Clayton', '', '117-6334 Auctor, Rd.', 'quisque.porttitor@aol.couk'),
(954944, 'Lunea', 'Tate', '', 'Ap #454-7139 Praesent Road', 'nam@outlook.ca'),
(965945, 'Rana', 'Mercer', '', '564-4135 Pede. Road', 'egestas.urna@outlook.org'),
(979288, 'Duncan', 'Copeland', '', 'P.O. Box 269, 1256 Nunc St.', 'nunc.sed@google.couk'),
(1050976804, 'jhonnier', 'castro montero', '', 'cra 33 #30 23', 'jhonnier_199@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL,
  `nonbre` varchar(40) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  `fecha` varchar(40) NOT NULL,
  `costo` decimal(10,0) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad`, `total`) VALUES
(2, 'bnhsj', 1200, '12', '1000', 1, '1000'),
(3, '43', 1200, '16', '1000', 2, '2000'),
(14, 'wfg', 1200, '34', '2000', 2, '4000'),
(21, 'bnhsj', 1200, '12', '1000', 2, '2000'),
(22, 'bnhsj', 1200, '12', '10000', 1, '10000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `cedula` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `cargo` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cedula`, `nombre`, `apellido`, `direccion`, `cargo`) VALUES
(111, 'jhonier', 'castro montero', 'alcibia', 'usuario'),
(37234839, 'Keegan Church', 'Osborn', 'P.O. Box 806, 1136 Iaculis Road', 'usuario'),
(207718155, 'Xaviera Sharp', 'Day', '609-6643 Neque St.', 'usuario'),
(210163921, 'Justin Pickett', 'Macdonald', 'Ap #632-9699 Dapibus St.', 'usuario'),
(260819198, 'Linus Bray', 'Morales', '982-1433 A Street', 'usuario'),
(449982438, 'Daquan Byrd', 'Patel', 'Ap #464-3830 Praesent Ave', 'usuario'),
(595946736, 'Teegan Howell', 'Trevino', 'P.O. Box 395, 3576 Rutrum Rd.', 'usuario'),
(632397096, 'Harding Wilkerson', 'Andrews', 'P.O. Box 101, 7847 Etiam Rd.', 'usuario'),
(652046064, 'Asher Prince', 'Rocha', '631-7316 Lorem, Av.', 'usuario'),
(705669465, 'Raphael Stevenson', 'Estrada', '769-8654 Luctus Rd.', 'usuario'),
(743114401, 'Ulric Stephens', 'Rollins', '214-4142 Vehicula Av.', 'usuario'),
(884092300, 'Oscar Mckee', 'Middleton', '447-5138 Aenean St.', 'usuario'),
(979606106, 'Berk Guerra', 'Wilson', 'P.O. Box 265, 1049 Aliquam St.', 'usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `id_categorias` int(11) NOT NULL,
  `cantida` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `id_categorias`, `cantida`) VALUES
(1, 'silla artesanal de paja', 1, 4),
(2, 'escaparate tamboreadoy', 1, 133),
(4, 'escaparate tamboreado', 2, 13),
(5, 'escaparate tamboreado', 2, 13),
(6, 'escaparate tamboreado', 2, 13),
(7, 'escaparate tamboreado', 2, 13),
(8, 'escaparate tamboreado', 2, 13),
(9, 'escaparate tamboreado', 2, 13),
(10, 'escaparate tamboreado', 2, 13),
(44, 'gffdg', 2, 10),
(5656, 'ASGDHTRSJ', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `idProveedor` int(15) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `telefono` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idProveedor`, `nombre`, `direccion`, `telefono`) VALUES
(1129, 'Cras Sed Ltd', 'Ap #460-4820 Ligula. Av.', 2434614),
(1200, 'sansungmder', 'alcibia', 23456789),
(1201, 'sansungmder', 'alcibia', 23456789),
(1223, 'Enim Corp.', '432 Et Av.', 2225735),
(1601, 'Cursus Luctus Corporation', '2863 Molestie St.', 7425219),
(1637, 'Justo Institute', '9029 Ultricies St.', 635718),
(1777, 'Sociis Natoque Penatibus Ltd', 'Ap #935-4409 Ut Rd.', 7684315),
(1950, 'Nunc Quis Incorporated', 'Ap #317-5959 Morbi Avenue', 9433238),
(1997, 'Magna Corporation', 'Ap #869-7540 Diam Road', 468221),
(2029, 'Facilisi Sed Neque Associates', '353 Facilisis Rd.', 1115864),
(2115, 'Mollis Industries', '133-4241 Orci. St.', 2610124),
(2529, 'Odio Sagittis Limited', 'P.O. Box 681, 6644 Varius Road', 5927675),
(2673, 'Nulla Interdum Ltd', '2421 Amet, St.', 3685583),
(3299, 'Aliquam Limited', '968-1286 Luctus. Road', 1521012),
(3442, 'Vel Quam Ltd', 'Ap #267-4538 Blandit Street', 8533155),
(3573, 'Laoreet Lectus Quis Limited', 'Ap #101-1497 Aliquet St.', 8006555),
(3876, 'Libero Lacus Varius Foundation', '664-5394 Auctor Road', 8477523),
(3943, 'Nec Quam Foundation', '5692 Neque Avenue', 7254689),
(4213, 'Ac LLC', '437-9541 Cum St.', 9901578),
(4553, 'Lacus Varius Consulting', 'Ap #476-5062 Etiam St.', 5884433),
(4640, 'Velit Associates', 'Ap #849-6407 Euismod Rd.', 5121446),
(4875, 'Nunc Quis Ltd', '286-114 Malesuada St.', 1669510),
(5962, 'Viverra Maecenas Incorporated', 'P.O. Box 391, 5030 Turpis Rd.', 2211634),
(6271, 'Sem Consequat Corporation', 'Ap #567-5425 Nunc St.', 5484805),
(6677, 'Morbi Tristique Corp.', '9158 Mi Rd.', 1414712),
(6988, 'Sapien Imperdiet Industries', '840-191 Aliquam Rd.', 1840886),
(7008, 'Neque Industries', '107-8607 Auctor Road', 1267773),
(7364, 'Nisi Associates', '6579 Donec St.', 7266978),
(7370, 'Nulla Inc.', 'Ap #680-9009 Tristique Road', 4968326),
(7578, 'Posuere Cubilia Associates', '898-8081 Erat St.', 8068120),
(8207, 'Porttitor Tellus Non PC', 'P.O. Box 443, 9002 Lacus. St.', 9888679),
(8213, 'Enim Inc.', 'P.O. Box 547, 8419 Ac Ave', 5813277),
(8771, 'Dui Suspendisse Incorporated', 'Ap #211-2235 Gravida. Road', 7866735),
(8986, 'Nam Ligula Elit Ltd', '713-5746 Elit, Ave', 6563158),
(9061, 'Ultricies Ligula Corporation', '107-2995 Non Ave', 1474887),
(9714, 'Rhoncus Proin LLC', 'Ap #222-1434 Enim. Rd.', 2321380),
(9916, 'Donec Porttitor Tellus Inc.', '290-9751 Nam Av.', 8355087),
(12232, '2222', '22222', 22222);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usu` varchar(40) NOT NULL,
  `cargo` varchar(40) NOT NULL,
  `contraseña` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre_usu`, `cargo`, `contraseña`) VALUES
(2279, 'Tyrone', '', 'MFK51FJC1OI'),
(2854, 'user', 'admin', '123456'),
(2861, 'Geraldine', '', 'VYP85QFV7NW'),
(3001, 'jhocastro', 'usuario', '12345'),
(3074, 'Hall', '', 'OQT86RED8ZZ'),
(3955, 'alexander', '', '12345'),
(8863, 'Judith', '', 'DCV64RWU7KW'),
(9060, 'Amethyst', '', 'GUU21PTR8YI'),
(1010120, 'rodrigo', 'admin', '12345'),
(101010101, '1010', '1010', '10101');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `fecha` varchar(15) NOT NULL,
  `monto` double NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cedula_cliente` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario`) VALUES
(2, '675', 1000, 1, 2, 2279),
(112245, 'current_timesta', 11111, 2, 388260, 3955);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoría`
--
ALTER TABLE `categoría`
  ADD PRIMARY KEY (`id_categoría`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`idCompra`),
  ADD KEY `idProveedor` (`idProveedor`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_categoria` (`id_categorias`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`idProveedor`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_Producto` (`id_producto`),
  ADD KEY `cedula_cliente` (`cedula_cliente`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `idProveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedores` (`idProveedor`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `id_categoria` FOREIGN KEY (`id_categorias`) REFERENCES `categoría` (`id_categoría`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `cedula_cliente` FOREIGN KEY (`cedula_cliente`) REFERENCES `cliente` (`cedula`),
  ADD CONSTRAINT `id_Producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON UPDATE CASCADE,
  ADD CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
