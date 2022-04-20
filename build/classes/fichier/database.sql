-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  mer. 20 avr. 2022 à 15:35
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sygestbiblio_data`
--
CREATE DATABASE IF NOT EXISTS `sygestbiblio_data` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `sygestbiblio_data`;

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

DROP TABLE IF EXISTS `adherents`;
CREATE TABLE IF NOT EXISTS `adherents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `genre` text NOT NULL,
  `dNaissance` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `numeroPiece` text NOT NULL,
  `dadheration` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `emprunt` int(11) NOT NULL,
  `ajour` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  `tel1` varchar(20) NOT NULL,
  `tel2` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`id`, `nom`, `prenom`, `genre`, `dNaissance`, `numeroPiece`, `emprunt`, `ajour`, `matricule`, `tel1`, `tel2`) VALUES
(2, 'OUEDRAOGO', 'SERGEO', 'Masculin', '2 janv. 1995', 'B50566885', 0, 1, '000002', '54916755', '54916755'),
(5, 'OUEDRAOGO', 'SERGEO', 'Masculin', '2 janv. 1995', 'B50566885', 0, 1, '000003', '24785968', '24528752'),
(6, 'OUEDRAOGO', 'SERGEO', 'Masculin', '2 janv. 1995', 'B50566885', 0, 1, '000006', '22548595', '22548595');

-- --------------------------------------------------------

--
-- Structure de la table `emprunts`
--

DROP TABLE IF EXISTS `emprunts`;
CREATE TABLE IF NOT EXISTS `emprunts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adh` text NOT NULL,
  `lv` text NOT NULL,
  `dateEmprunt` text NOT NULL,
  `dateRetour` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `liste_table`
--

DROP TABLE IF EXISTS `liste_table`;
CREATE TABLE IF NOT EXISTS `liste_table` (
  `nom` text NOT NULL,
  `nom_champs` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `liste_table`
--

INSERT INTO `liste_table` (`nom`, `nom_champs`) VALUES
('adherents', 'nom-prenom-genre-dNaissance-numeroPiece-dadheration-emprunt-ajour-matricule-tel1-tel2');

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

DROP TABLE IF EXISTS `livres`;
CREATE TABLE IF NOT EXISTS `livres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` text NOT NULL,
  `edition` text NOT NULL,
  `auteur` text NOT NULL,
  `nbreExemplaire` int(11) NOT NULL,
  `dateAjout` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `livres`
--

INSERT INTO `livres` (`id`, `titre`, `edition`, `auteur`, `nbreExemplaire`, `code`) VALUES
(1, 'Allah n\'est pas obligée', 'Les Soleils d \'Afrique', 'Ahmadou Kourouma1', 53, 'LVS_N1'),
(2, 'L\'enfant noir', 'Les Soleils d\'Afrique', 'Camara Laye', 10, 'LVS_N_2');

-- --------------------------------------------------------

--
-- Structure de la table `livres_perdus`
--

DROP TABLE IF EXISTS `livres_perdus`;
CREATE TABLE IF NOT EXISTS `livres_perdus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_livre` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
