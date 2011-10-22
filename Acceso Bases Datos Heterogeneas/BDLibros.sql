CREATE TABLE IF NOT EXISTS `Amazon` (
  `idLibro` INTEGER PRIMARY KEY,
  `tituloLibro` varchar(50) NOT NULL,
  `idAutor` int(10) NOT NULL,
  `editorial` varchar(30) NOT NULL,
  `numPaginas` int(10) NOT NULL,
  `enStock` tinyint(1) NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  `descripcion` text NOT NULL,
  `fotografia` varchar(140) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Autor` (
  `idAutor` int(10) NOT NULL,
  `nombreAutor` varchar(50) NOT NULL,
  `apellidosAutor` varchar(80) NOT NULL,
  `lugarNacimiento` varchar(50) NOT NULL,
  `nacimiento` int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS `CasaDelLibro` (
  `idLibro` INTEGER PRIMARY KEY,
  `titulo` varchar(50) NOT NULL,
  `idAutor` int(10) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `fechaEdicion` int(11) NOT NULL,
  `sinopsis` text NOT NULL,
  `numPaginas` int(10) NOT NULL,
  `fotografia` varchar(140) NOT NULL
);
