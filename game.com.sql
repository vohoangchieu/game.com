-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 02, 2017 at 04:00 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `game.com`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `short_desc` varchar(512) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `long_desc` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `name_vn` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `short_dest` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `full_desc` text COLLATE utf8_unicode_ci NOT NULL,
  `order_weight` int(11) NOT NULL DEFAULT '0',
  `is_promote` int(11) NOT NULL DEFAULT '0',
  `link_youtube` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `is_promote` (`is_promote`),
  KEY `order_weight` (`order_weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `game_category`
--

CREATE TABLE IF NOT EXISTS `game_category` (
  `game_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  UNIQUE KEY `game_id` (`game_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
