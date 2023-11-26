

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


--
-- Database: `gtele`
--

-- --------------------------------------------------------

--
-- Table structure for table `clientname`
--

CREATE TABLE `clientname` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `id_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clientname`
--

INSERT INTO `clientname` (`id`, `nom`, `phone_number`, `id_name`) VALUES
(10, 'Client1', '111-222-3333', 'user1'),
(11, 'Client2', '444-555-6666', 'user1'),
(12, 'Client3', '777-888-9999', 'user2'),
(13, 'Client4', '123-456-7890', 'user2'),
(14, 'Client5', '987-654-3210', 'user3');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sexe` char(1) DEFAULT NULL,
  `celibataire` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `sexe`, `celibataire`) VALUES
(17, 'user1', 'pass1', 'M', 0),
(18, 'user2', 'pass2', 'F', 1),
(19, 'user3', 'pass3', 'M', 0),
(20, 'user4', 'pass4', 'F', 1),
(21, 'user5', 'pass5', 'M', 1);



--
-- Indexes for table `clientname`
--
ALTER TABLE `clientname`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `clientname`
--
ALTER TABLE `clientname`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

