-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 22-10-24 06:00
-- 서버 버전: 10.4.21-MariaDB
-- PHP 버전: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `faceornamental`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `faceuser`
--

CREATE TABLE `faceuser` (
  `uid` int(11) NOT NULL,
  `userId` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `userPw` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `userRegDate` date NOT NULL,
  `userNowCeleb` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `userNowGender` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `userNowAge` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `userNowEmote` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `userNowPose` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- 테이블의 덤프 데이터 `faceuser`
--

INSERT INTO `faceuser` (`uid`, `userId`, `userPw`, `userRegDate`, `userNowCeleb`, `userNowGender`, `userNowAge`, `userNowEmote`, `userNowPose`) VALUES
(1, 'admin', 'pass', '2022-01-01', '강민혁', '남성 (99.96%)', '14~18 (68.75%)', '무표정 (100.0%)', '정면 (99.75%)'),
(9, 'aduino', 'aduino', '2022-10-21', NULL, NULL, NULL, NULL, NULL),
(12, 'adino', 'adino', '2022-10-21', NULL, NULL, NULL, NULL, NULL),
(15, 'aderader', 'aderader', '2022-10-21', NULL, NULL, NULL, NULL, NULL),
(17, 'AFQcyan', 'eQu7Rhy65', '2022-10-21', NULL, NULL, NULL, NULL, NULL),
(18, 'sangjin', 'dddddddd', '2022-10-24', NULL, NULL, NULL, NULL, NULL);

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `faceuser`
--
ALTER TABLE `faceuser`
  ADD PRIMARY KEY (`uid`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `faceuser`
--
ALTER TABLE `faceuser`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
