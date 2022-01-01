-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2022 at 07:49 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apidata`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `subject` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `content`, `date`, `subject`, `type`, `user_id`) VALUES
(3, 'Test content 03', '2022-01-03', 'Test subject 03', 'Reward', '1003'),
(4, 'Test content 04', '2022-01-04', 'Test subject 04', 'Reward', '1004'),
(5, 'Test content 05', '2022-01-05', 'Test subject 05', 'Reward', '1005'),
(6, 'Test content 06', '2022-01-06', 'Test subject 06', 'Promoter', '1006'),
(7, 'Test content 07', '2022-01-03', 'Test subject 07', 'Promoter', '1002'),
(8, 'Test content 08', '2022-01-03', 'Test subject 08', 'Promoter', '1004'),
(9, 'Test content 09', '2022-01-04', 'Test subject 09', 'Event', '1005'),
(10, 'Test content 10', '2022-01-03', 'Test subject 10', 'Event', '1002'),
(11, 'Test content 11', '2022-01-06', 'Test subject 11', 'Unknown', '1005'),
(12, 'Test content 12', '2022-01-08', 'Test subject 12', 'Unknown', '1007'),
(13, 'Test content 13', '2022-01-08', 'Test subject 13', 'Reward', '1008');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
