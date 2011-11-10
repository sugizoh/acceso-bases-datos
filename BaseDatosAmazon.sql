-- phpMyAdmin SQL Dump
-- version 3.3.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 08, 2011 at 11:24 AM
-- Server version: 5.1.54
-- PHP Version: 5.3.5-1ubuntu7.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Amazon`
--

-- --------------------------------------------------------

--
-- Table structure for table `Amazon`
--

CREATE TABLE IF NOT EXISTS `Amazon` (
  `idLibro` int(11) NOT NULL AUTO_INCREMENT,
  `tituloLibro` varchar(50) NOT NULL,
  `idAutor` int(10) unsigned NOT NULL,
  `editorial` varchar(30) NOT NULL,
  `numPaginas` int(10) unsigned NOT NULL,
  `enStock` tinyint(1) NOT NULL,
  `precio` decimal(6,2) unsigned NOT NULL,
  `descripcion` text NOT NULL,
  `fotografia` varchar(140) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `Amazon`
--

INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(1, 'Los juegos del hambre', 1, 'Rba', 400, 1, '17.10', 'La historia está ambientada en un futuro no muy lejano en el país de Panem (antes Estados Unidos), que se divide en 12 distritos. Antes eran 13 pero, tras una gran rebelión, lo destruyeron hasta los cimientos. Desde entonces se celebran los Juegos del Hambre, una competición a muerte donde los representantes de los diferentes distritos (un chico y una chica elegidos mediante sorteo), llamados ''tributos'', pelean hasta la muerte. Todos los jóvenes mayores de doce años tienen automáticamente una papeleta para ser elegidos, cada año que pasa tendrán una más. Pero los más pobres siempre tienen las de perder, ya que pueden conseguir comida a cambio de una papeleta en el sorteo.\r\nDe ese modo, el día de la ''cosecha'' (el sorteo donde se eligen los tributos) la hermana de Katniss, Prim, saldrá como tributo del distrito 12. Katniss se ofrece como voluntaria para ocupar su lugar y se ve envuelta en un grotesco espectáculo nacional donde tendrá que matar para sobrevivir.\r\nLa protagonista absoluta del libro es Katniss Everdeen ya que nos narra en primera persona todo lo que le ocurre y lo que piensa. Al contrario de lo que me ocurre muchas veces con este tipo de personajes, ella no me ha parecido nada pedante, perfecta, maravillosa e insoportable. Cosa que no pueden decir muchas protagonistas de libros por el estilo'' Katniss es salvaje e imperfecta. Quizá sabe demasiadas cosas para tener sólo 16 años pero, ¿qué importa? Suzanne Collins nos cuenta la historia tan bien que te lo crees todo. ', 'Los_juegos_del_hambre.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(2, 'Harry Potter y la Piedra Filosofal', 2, 'Salamandra', 256, 1, '6.54', 'Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley. Harry se siente muy triste y solo, hasta que un buen día recibe una carta que cambiará su vida para siempre. ', 'hp_piedra_filosofal.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(3, 'Harry Potter y el prisionero de Azkaban', 2, 'Salamandra', 360, 1, '8.07', 'Harry se pelea con su bigotuda tía Marge, a la que convierte en un globo, y debe huir en un autobús mágico. Mientras tanto, de la prisión de Azkabán se ha escapado un terrible villano, Sirius Black, un asesino en serie con poderes mágicos. ', 'hp_prisionero_azkaban.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(4, 'Harry Potter y la Cámara Secreta', 2, 'Salamandra', 288, 0, '7.12', 'Un elfo aparece en su habitación y le advierte que una amenaza mortal se cierne sobre la escuela. Así pues, Harry no se lo piensa dos veces y, acompañado de Ron, su mejor amigo, se dirige a Hogwarts en un coche volador. Pero ¿puede un aprendiz de mago defender la escuela de los malvados que pretenden destruirla? ', 'hp_camara_secreta.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(5, 'Harry Potter y las Reliquias de La Muerte', 2, 'Salamandra', 640, 1, '20.90', 'Harry tiene que realizar una tarea siniestra, peligrosa y aparentemente imposible: el de localizar y de destruir a Horcruxes. Harry nunca se sintió tan sólo ni se enfrentó a un futuro tan incierto. Pero Harry debe encontrar la fuerza necesaria para terminar la tarea que le han dado. ', 'hp_reliquias_muerte');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(6, 'Harry Potter y el Cáliz de Fuego', 2, 'Salamandra', 640, 1, '11.40', 'Tras otro abominable verano con los Dursley, Harry se dispone a iniciar el cuarto curso en Hogwarts, la famosa escuela de magia y hechicería. A sus 14 años, a Harry le gustaría ser un joven mago como los demás.', 'hp_caliz_fuego');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(7, 'Harry potter y el misterio del principe', 2, 'Salamandra', 602, 1, '14.35', '', 'hp_misterio_principe');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(8, 'Harry potter y las reliquias de lamuerte', 2, 'Salamandra', 640, 1, '23.23', 'Harry tiene que realizar una tarea siniestra, peligrosa y aparentemente imposible: el de localizar y de destruir a Horcruxes. Harry nunca se sintió tan sólo ni se enfrentó a un futuro tan incierto. Pero Harry debe encontrar la fuerza necesaria para terminar la tarea que le han dado.', 'hp_reliquias_muerte.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(9, 'El Hobbit', 3, 'Minotauro', 320, 0, '10.40', 'Smaug parecía profundamente dormido cuando Bilbo espió una vez más desde la entrada. ¡Pero fingía estar dormido! ¡Estaba vigilando la entrada del túnel!... Sacado de su cómodo agujero-hobbit por Gandalf y una banda de enanos, Bilbo se encuentra de pronto en medio de una conspiración que pretende apoderarse del tesoro de Smaug el Magnífico, un enorme y muy peligroso dragón...', 'hobbit.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(10, 'El conde de montecristo', 4, 'Mondadori', 1144, 0, '19.00', 'El conde de montecristo es un clásico de enorme poder de sugestión, a través de la figura del hombre solitario que, luego de sobrevivir a la injusta condena y la miserable prisión, regresa para hacer justicia. ', 'conde_montecristo.png');
INSERT INTO `Amazon` (`idLibro`, `tituloLibro`, `idAutor`, `editorial`, `numPaginas`, `enStock`, `precio`, `descripcion`, `fotografia`) VALUES(11, 'El señor de las moscas', 5, 'Alianza Editorial', 288, 1, '8.55', 'Un avión que transporta sólo a unos estudiantes británicos es derribado en periodo de guerra a causa de una fuerte tormenta y se estrella contra una isla desierta, siendo los únicos sobrevivientes. Los niños pasajeros se ven obligados a sobrevivir sin ningún adulto.', 'señor_moscas.png');

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