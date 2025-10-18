-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 18, 2025 at 02:34 AM
-- Server version: 8.0.42-0ubuntu0.20.04.1
-- PHP Version: 7.4.3-4ubuntu2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moviesdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `director` varchar(200) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `genre` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `title`, `director`, `year`, `genre`) VALUES
(4, 'The Dark Knight', 'Christopher Nolan', 2008, 'Action'),
(6, 'Fight Club', 'David Fincher', 1999, 'Drama'),
(8, 'Interstellar', 'Christopher Nolan', 2014, 'Sci-Fi'),
(9, 'Gladiator', 'Ridley Scott', 2000, 'Action'),
(11, 'Goodfellas', 'Martin Scorsese', 1990, 'Crime'),
(12, 'The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 2001, 'Fantasy'),
(13, 'The Lord of the Rings: The Two Towers', 'Peter Jackson', 2002, 'Fantasy'),
(14, 'The Lord of the Rings: The Return of the King', 'Peter Jackson', 2003, 'Fantasy'),
(15, 'The Avengers', 'Joss Whedon', 2012, 'Action'),
(16, 'Avengers: Endgame', 'Anthony and Joe Russo', 2019, 'Action'),
(17, 'The Silence of the Lambs', 'Jonathan Demme', 1991, 'Thriller'),
(18, 'Se7en', 'David Fincher', 1995, 'Thriller'),
(19, 'The Prestige', 'Christopher Nolan', 2006, 'Drama'),
(21, 'Jurassic Park', 'Steven Spielberg', 1993, 'Adventure'),
(22, 'Titanic', 'James Cameron', 1997, 'Romance'),
(23, 'The Lion King', 'Roger Allers & Rob Minkoff', 1994, 'Animation'),
(24, 'Spirited Away', 'Hayao Miyazaki', 2001, 'Animation'),
(25, 'Coco', 'Lee Unkrich', 2017, 'Animation'),
(26, 'LEarning Rails', 'LEarning Rails', 2004, 'Drama'),
(27, 'LEarning Django', 'LEarning Django', 2004, 'Drama'),
(28, 'LEarning Django', 'LEarning Django', 2004, 'Drama'),
(29, 'Perl in devops', 'YNS', 2004, 'Coding'),
(30, 'Golang in devops', 'YNS', 2005, 'Cloud'),
(31, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(32, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(33, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(34, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(35, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(36, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(37, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(38, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(39, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(40, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(41, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(42, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(43, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(44, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(45, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(46, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(47, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(48, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(49, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(50, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(51, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(52, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(53, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(54, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(55, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(56, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(57, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(58, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(59, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(60, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(61, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(62, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(63, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(64, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(65, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(66, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(67, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(68, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(69, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(70, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(71, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(72, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(73, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(74, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(75, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(76, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(77, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(78, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(79, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(80, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(81, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(82, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(83, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(84, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(85, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(86, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(87, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(88, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(89, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(90, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(91, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(92, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(93, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(94, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(95, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(96, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(97, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(98, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(99, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(100, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(101, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(102, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(103, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(104, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(105, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(106, 'Matrix', 'Zeus', NULL, 'SC Fiction'),
(107, 'Matrix', 'Zeus', NULL, 'SC Fiction');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` varchar(50) DEFAULT 'USER',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password_hash`, `role`, `created_at`) VALUES
(1, 'younes', 'younes@example.com', 'loremipsum', 'ADMIN', '2025-10-07 22:44:50'),
(2, 'sara', 'sara@example.com', '$2a$10$zyxwvutsrqponmlkjihgf', 'USER', '2025-10-07 22:44:50'),
(3, 'khalid', 'khalid@example.com', '$2a$10$qwertyuiopasdfghjklzxc', 'USER', '2025-10-07 22:44:50');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
