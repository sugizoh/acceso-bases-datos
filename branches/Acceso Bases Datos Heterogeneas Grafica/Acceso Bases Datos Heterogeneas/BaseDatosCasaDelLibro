-- phpMyAdmin SQL Dump
-- version 3.3.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 08, 2011 at 11:25 AM
-- Server version: 5.1.54
-- PHP Version: 5.3.5-1ubuntu7.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `CasaDelLibro`
--

-- --------------------------------------------------------

--
-- Table structure for table `Autor`
--

CREATE TABLE IF NOT EXISTS `Autor` (
  `idAutor` int(10) unsigned NOT NULL,
  `nombreAutor` varchar(50) NOT NULL,
  `apellidosAutor` varchar(80) NOT NULL,
  `lugarNacimiento` varchar(50) NOT NULL,
  `nacimiento` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Autor`
--

INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(1, 'Suzanne', 'Collins', 'Estados Unidos', 1964);
INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(2, 'J.K.', 'Rowling', 'Inglaterra', 1965);
INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(3, 'J.R.', 'Tolkien', 'Sudáfrica', 1892);
INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(4, 'Alexandre', 'Dumas ', 'Puys', 1802);
INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(5, 'William', 'Gerald Golding', 'Reino Unido', 1911);
INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(6, 'Stephen', 'King', 'Portland (Estados Unidos)', 1947);
INSERT INTO `Autor` (`idAutor`, `nombreAutor`, `apellidosAutor`, `lugarNacimiento`, `nacimiento`) VALUES(7, 'Orson', 'Scott Card', 'Estados Unidos', 1951);

-- --------------------------------------------------------

--
-- Table structure for table `CasaDelLibro`
--

CREATE TABLE IF NOT EXISTS `CasaDelLibro` (
  `idLibro` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `idAutor` int(10) unsigned NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `fechaEdicion` int(11) NOT NULL,
  `sinopsis` text NOT NULL,
  `numPaginas` int(10) unsigned NOT NULL,
  `fotografia` varchar(140) NOT NULL,
  `precio` decimal(6,2) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `CasaDelLibro`
--

INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(1, 'Harry Potter y la piedra filosofal', 2, '9788478884452', 1999, 'Harry Potter y la piedra filosofal es el primer libro de la serie del joven mago Harry Potter.\r\n\r\nHarry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley. Harry se siente muy triste y solo, hasta que un buen día recibe una carta que cambiará su vida para siempre.', 254, 'hp_piedra_filosofal.png', '8.99');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(2, 'Harry Potter y las reliquias de la muerte', 2, '9788498381405', 2008, 'Harry Potter tiene que realizar una tarea siniestra, peligrosa y aparentemente imposible: el de localizar y de destruir a Horcruxes. Harry nunca se sintió tan sólo ni se enfrentó a un futuro tan incierto. Pero Harry Potter debe encontrar la fuerza necesaria para terminar la tarea que le han dado. Él debe dejar el calor, la seguridad y el compañerismo de La Madriguera y seguir sin miedo el camino inexorable marcado para él.\r\nEn este final, la séptima entrega de la serie Harry Potter, J.K. Rowling revela de manera espectacular las respuestas a las muchas preguntas que se han estado esperando con tanta impaciencia. Su rica prosa y su narrativa, llena de giros inesperados, han hecho que estos libros sean libro para leer y releer una y otra vez. ', 640, 'hp_reliquias_muerte.png', '28.74');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(3, 'Harry Potter y el prisionero de Azkaban', 2, '9788478885190', 2000, 'Por la cicatriz que lleva en la frente, sabemos que Harry Potter no es un niño como los demás, sino el héroe que venció a Lord Voldemort, el más temible y maligno mago de todos los tiempos y culpable de la muerte de los padres de Harry. Desde entonces, Harry no tiene más remedio que vivir con sus pesados tíos y su insoportable primo Dudley, todos ellos muggles, o sea, personas no magas, que desprecian a su sobrino debido a sus poderes. Este ya es el tercer año en Hogwarts y como siempre, además de a los examénes y a los partidos Harry Potterdeberá enfrentarse a su terrible enemigo', 360, 'hp_prisionero_azkaban.png', '11.09');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(4, 'El retorno del rey (El señor de los anillos)', 3, '9788445073742', 2002, 'Ningún escritor del género ha aprovechado tanto como Tolkien las propiedades características de la Misión, el viaje heroico, el Objeto Numinoso, satisfaciendo nuestro sentido de la realidad histórica y social... Tolkien ha triunfado donde fracasó Milton. -- W. H. Auden Ecologista avant-la-lettre, Tolkien adora los árboles, los cielos, los ríos, la luz. -- Marie Claude de Brunhoff', 296, 'retorno_rey.png', '15.05');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(5, 'La comunidad del anillo (El señor de los anillos)', 3, '9788445073728', 2002, 'Este libro es como un relámpago en un cielo claro. Decir que la novela heroica, espléndida, elocuente, desinhibida, ha retornado de pronto en una época de un antirromanticismo casi patológico, sería inadecuado. Para quienes vivimos en esa extraña época, el retorno -y el alivio que nos trae- es sin duda lo más importante. Pero para la historia misma de la novela -una historia que se remonta a la Odisea y a antes de la Odisea- no es un retorno, sino un paso adelante o una revolución: la conquista de un territorio nuevo. -- C. S. Lewis, Time & Tide\r\n', 477, 'comunidad_anillo.png', '24.00');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(6, 'Las dos torres (El señor de los anillos)', 3, '9788445073735', 2002, 'Espléndida traducción castellana de esta prodigiosa y fantástica trilogía... Nadie podrá regatearle a J. R. R. Tolkien el oficio de buen narrador, de buen escritor que sabe ganarse incondicionalmente el ánimo de los lectores. -- Francesc Parcerisas', 405, 'dos_torres.png', '28.81');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(7, 'Los ojos del dragón', 6, '9788497930192', 2003, 'Stephen King convierte un clásico cuento de hadas en una obra maestra del género de terror. Los ojos del dragón es un apasionante relato de aventuras, con héroes arquetípicos, dragones, príncipes y hechiceros... El joven príncipe Peter, hijo del difunto rey, da la talla de monarca y espera heredar el reino. Pero el mago de la corte dispone que sea ungido el príncipe Thomas, un muchacho al que manipula de acuerdo con sus siniestros propósitos. Sin embargo, Thomas posee un secreto que nadie ha sido capaz de adivinar.', 400, 'ojos_dragon.png', '10.93');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(8, 'Carrie', 6, '9788497595698', 2003, 'El escalofriante caso de una joven de apariencia insignificante que se transformó en un ser de poderes anormales, sembrando el terror en la ciudad. Con pulso mágico para mantener la tensión a lo largo de todo el libro, Stephen King narra la atormentada adolescencia de Carrie, y nos envuelve en una atmósfera sobrecogedora cuando la muchacha realiza una serie de descubrimientos hasta llegar al terrible momento de la venganza. Esta novela fue llevada al cine con un inmenso éxito de público y crítica.', 256, 'carrie.png', '10.93');
INSERT INTO `CasaDelLibro` (`idLibro`, `titulo`, `idAutor`, `ISBN`, `fechaEdicion`, `sinopsis`, `numPaginas`, `fotografia`, `precio`) VALUES(9, 'El juego de Ender', 7, '9788496581579', 2006, 'La Tierra está amenazada por una especie extraterrestre de insectos que pretende destruir la humanidad. Para vencerlos se precisa la intervención de un genio militar, por lo cual se permite el nacimiento de Ender, tercer hijo de una pareja en un mundo que limita a dos el número de descendientes. Ender se entrenará en una estación espacial, superará a sus rivales y se convertirá en la persona capaz de dirigir las flotas terrestres contra los insectos de otros mundos.', 368, 'juego_ender.png', '9.55');