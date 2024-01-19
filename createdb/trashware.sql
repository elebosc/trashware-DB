-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: trashware
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS trashware;

CREATE DATABASE IF NOT EXISTS trashware;

USE trashware;

--
-- Table structure for table `altri_componenti_pc`
--

DROP TABLE IF EXISTS `altri_componenti_pc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `altri_componenti_pc` (
  `IDComponente` varchar(10) NOT NULL,
  `IDPC` varchar(10) NOT NULL,
  PRIMARY KEY (`IDComponente`),
  KEY `FKAlt_PC` (`IDPC`),
  CONSTRAINT `FKAlt_COM_FK` FOREIGN KEY (`IDComponente`) REFERENCES `componenti` (`IDComponente`),
  CONSTRAINT `FKAlt_PC` FOREIGN KEY (`IDPC`) REFERENCES `pc` (`IDPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chassis`
--

DROP TABLE IF EXISTS `chassis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chassis` (
  `IDComponente` varchar(10) NOT NULL,
  `Colore` varchar(15) NOT NULL,
  PRIMARY KEY (`IDComponente`),
  CONSTRAINT `FKInfoCase_FK` FOREIGN KEY (`IDComponente`) REFERENCES `componenti` (`IDComponente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `completamento`
--

DROP TABLE IF EXISTS `completamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `completamento` (
  `IDRichiesta` varchar(10) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`IDRichiesta`),
  CONSTRAINT `FKRegistCompl_FK` FOREIGN KEY (`IDRichiesta`) REFERENCES `richieste` (`IDRichiesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `componenti`
--

DROP TABLE IF EXISTS `componenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `componenti` (
  `IDComponente` varchar(10) NOT NULL,
  `Tipo` varchar(35) NOT NULL,
  `Marca` varchar(128) NOT NULL,
  `Modello` varchar(128) NOT NULL,
  `Note` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`IDComponente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `consegna`
--

DROP TABLE IF EXISTS `consegna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consegna` (
  `IDRichiesta` varchar(10) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`IDRichiesta`),
  CONSTRAINT `FKRegistConsegna_FK` FOREIGN KEY (`IDRichiesta`) REFERENCES `completamento` (`IDRichiesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cpu`
--

DROP TABLE IF EXISTS `cpu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cpu` (
  `IDComponente` varchar(10) NOT NULL,
  `Architettura` int NOT NULL,
  PRIMARY KEY (`IDComponente`),
  CONSTRAINT `FKInfoCPU_FK` FOREIGN KEY (`IDComponente`) REFERENCES `componenti` (`IDComponente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `desktop`
--

DROP TABLE IF EXISTS `desktop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desktop` (
  `IDPC` varchar(10) NOT NULL,
  `IDMonitor` varchar(10) DEFAULT NULL,
  `IDCasseAudio` varchar(10) DEFAULT NULL,
  `IDMouse` varchar(10) DEFAULT NULL,
  `IDTastiera` varchar(10) DEFAULT NULL,
  `IDChassis` varchar(10) NOT NULL,
  PRIMARY KEY (`IDPC`),
  KEY `FKCopertura` (`IDChassis`),
  KEY `FKDotazioneTastiera` (`IDTastiera`),
  KEY `FKDotazioneMouse` (`IDMouse`),
  KEY `FKDotazioneCasse` (`IDCasseAudio`),
  KEY `FKDotazioneMonitor` (`IDMonitor`),
  CONSTRAINT `FKCopertura` FOREIGN KEY (`IDChassis`) REFERENCES `chassis` (`IDComponente`),
  CONSTRAINT `FKDotazioneCasse` FOREIGN KEY (`IDCasseAudio`) REFERENCES `periferiche` (`IDPeriferica`),
  CONSTRAINT `FKDotazioneMonitor` FOREIGN KEY (`IDMonitor`) REFERENCES `monitor` (`IDPeriferica`),
  CONSTRAINT `FKDotazioneMouse` FOREIGN KEY (`IDMouse`) REFERENCES `periferiche` (`IDPeriferica`),
  CONSTRAINT `FKDotazioneTastiera` FOREIGN KEY (`IDTastiera`) REFERENCES `periferiche` (`IDPeriferica`),
  CONSTRAINT `FKInfoDesktop_FK` FOREIGN KEY (`IDPC`) REFERENCES `pc` (`IDPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `memoria_di_massa`
--

DROP TABLE IF EXISTS `memoria_di_massa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memoria_di_massa` (
  `IDComponente` varchar(10) NOT NULL,
  `Tipologia` varchar(10) NOT NULL,
  `Dimensione` int NOT NULL,
  PRIMARY KEY (`IDComponente`),
  CONSTRAINT `FKInfoMemMassa_FK` FOREIGN KEY (`IDComponente`) REFERENCES `componenti` (`IDComponente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monitor`
--

DROP TABLE IF EXISTS `monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monitor` (
  `IDPeriferica` varchar(10) NOT NULL,
  `Tipologia` varchar(35) NOT NULL,
  `Dimensione` int NOT NULL,
  `AspectRatio` varchar(6) NOT NULL,
  `VGA` bit(1) NOT NULL,
  `DVI` bit(1) NOT NULL,
  `AudioIntegrato` bit(1) NOT NULL,
  PRIMARY KEY (`IDPeriferica`),
  CONSTRAINT `FKInfoMonitor_FK` FOREIGN KEY (`IDPeriferica`) REFERENCES `periferiche` (`IDPeriferica`),
  CONSTRAINT `AspectRatioFormat` CHECK ((`AspectRatio` like _utf8mb4'%:%'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oggetto_componente`
--

DROP TABLE IF EXISTS `oggetto_componente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oggetto_componente` (
  `IDComponente` varchar(10) NOT NULL,
  `IDOperazione` varchar(10) NOT NULL,
  PRIMARY KEY (`IDOperazione`,`IDComponente`),
  KEY `FKOgg_COM` (`IDComponente`),
  CONSTRAINT `FKOgg_COM` FOREIGN KEY (`IDComponente`) REFERENCES `componenti` (`IDComponente`),
  CONSTRAINT `FKOgg_OPE_COM` FOREIGN KEY (`IDOperazione`) REFERENCES `operazioni` (`IDOperazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oggetto_pc`
--

DROP TABLE IF EXISTS `oggetto_pc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oggetto_pc` (
  `IDPC` varchar(10) NOT NULL,
  `IDOperazione` varchar(10) NOT NULL,
  PRIMARY KEY (`IDOperazione`,`IDPC`),
  KEY `FKOgg_PC` (`IDPC`),
  CONSTRAINT `FKOgg_OPE_PC` FOREIGN KEY (`IDOperazione`) REFERENCES `operazioni` (`IDOperazione`),
  CONSTRAINT `FKOgg_PC` FOREIGN KEY (`IDPC`) REFERENCES `pc` (`IDPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oggetto_periferica`
--

DROP TABLE IF EXISTS `oggetto_periferica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oggetto_periferica` (
  `IDPeriferica` varchar(10) NOT NULL,
  `IDOperazione` varchar(10) NOT NULL,
  PRIMARY KEY (`IDOperazione`,`IDPeriferica`),
  KEY `FKOgg_PER` (`IDPeriferica`),
  CONSTRAINT `FKOgg_OPE_PER` FOREIGN KEY (`IDOperazione`) REFERENCES `operazioni` (`IDOperazione`),
  CONSTRAINT `FKOgg_PER` FOREIGN KEY (`IDPeriferica`) REFERENCES `periferiche` (`IDPeriferica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operazioni`
--

DROP TABLE IF EXISTS `operazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operazioni` (
  `IDOperazione` varchar(10) NOT NULL,
  `Tipo` varchar(35) NOT NULL,
  `DataEffettuazione` date NOT NULL,
  `ElencoDispositivi` varchar(2000) NOT NULL,
  `Note` varchar(2000) DEFAULT NULL,
  `CodiceFiscaleReferente` char(16) NOT NULL,
  PRIMARY KEY (`IDOperazione`),
  KEY `FKEffettuazione` (`CodiceFiscaleReferente`),
  CONSTRAINT `FKEffettuazione` FOREIGN KEY (`CodiceFiscaleReferente`) REFERENCES `referente` (`CodiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pc`
--

DROP TABLE IF EXISTS `pc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pc` (
  `IDPC` varchar(10) NOT NULL,
  `IDMemMassa_02` varchar(10) DEFAULT NULL,
  `IDMemMassa_01` varchar(10) NOT NULL,
  `IDRAM_02` varchar(10) DEFAULT NULL,
  `IDRAM_03` varchar(10) DEFAULT NULL,
  `IDRAM_04` varchar(10) DEFAULT NULL,
  `IDRAM_01` varchar(10) NOT NULL,
  `IDCPU` varchar(10) NOT NULL,
  `Tipo` varchar(35) NOT NULL,
  `Ethernet` bit(1) NOT NULL,
  `WiFi` bit(1) NOT NULL,
  `Bluetooth` bit(1) NOT NULL,
  `Note` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`IDPC`),
  KEY `FKInstallazioneCPU` (`IDCPU`),
  KEY `FKInstallazioneRAM` (`IDRAM_01`),
  KEY `FKInstallazioneRAM_02` (`IDRAM_02`),
  KEY `FKInstallazioneRAM_03` (`IDRAM_03`),
  KEY `FKInstallazioneRAM_04` (`IDRAM_04`),
  KEY `FKInstallazioneMemMassa` (`IDMemMassa_01`),
  KEY `FKInstallazioneMemMassa_02` (`IDMemMassa_02`),
  CONSTRAINT `FKInstallazioneCPU` FOREIGN KEY (`IDCPU`) REFERENCES `cpu` (`IDComponente`),
  CONSTRAINT `FKInstallazioneMemMassa` FOREIGN KEY (`IDMemMassa_01`) REFERENCES `memoria_di_massa` (`IDComponente`),
  CONSTRAINT `FKInstallazioneMemMassa_02` FOREIGN KEY (`IDMemMassa_02`) REFERENCES `memoria_di_massa` (`IDComponente`),
  CONSTRAINT `FKInstallazioneRAM` FOREIGN KEY (`IDRAM_01`) REFERENCES `ram` (`IDComponente`),
  CONSTRAINT `FKInstallazioneRAM_02` FOREIGN KEY (`IDRAM_02`) REFERENCES `ram` (`IDComponente`),
  CONSTRAINT `FKInstallazioneRAM_03` FOREIGN KEY (`IDRAM_03`) REFERENCES `ram` (`IDComponente`),
  CONSTRAINT `FKInstallazioneRAM_04` FOREIGN KEY (`IDRAM_04`) REFERENCES `ram` (`IDComponente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `periferiche`
--

DROP TABLE IF EXISTS `periferiche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periferiche` (
  `IDPeriferica` varchar(10) NOT NULL,
  `Tipo` varchar(35) NOT NULL,
  `Marca` varchar(128) NOT NULL,
  `Modello` varchar(128) NOT NULL,
  `Connettività` varchar(35) NOT NULL,
  `Note` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`IDPeriferica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `portatili`
--

DROP TABLE IF EXISTS `portatili`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portatili` (
  `IDPC` varchar(10) NOT NULL,
  `Marca` varchar(128) NOT NULL,
  `Modello` varchar(128) NOT NULL,
  `Dimensione` int NOT NULL,
  `Colore` varchar(15) NOT NULL,
  PRIMARY KEY (`IDPC`),
  CONSTRAINT `FKInfoPortatile_FK` FOREIGN KEY (`IDPC`) REFERENCES `pc` (`IDPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ram`
--

DROP TABLE IF EXISTS `ram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ram` (
  `IDComponente` varchar(10) NOT NULL,
  `Dimensione` int NOT NULL,
  PRIMARY KEY (`IDComponente`),
  CONSTRAINT `FKInfoRAM_FK` FOREIGN KEY (`IDComponente`) REFERENCES `componenti` (`IDComponente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rappresentanza`
--

DROP TABLE IF EXISTS `rappresentanza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rappresentanza` (
  `CodiceFiscaleReferente` char(16) NOT NULL,
  `PartitaIVASocietà` char(11) NOT NULL,
  `TitoloReferente` varchar(35) NOT NULL,
  PRIMARY KEY (`CodiceFiscaleReferente`,`PartitaIVASocietà`),
  KEY `FKRap_SOC` (`PartitaIVASocietà`),
  CONSTRAINT `FKRap_REF` FOREIGN KEY (`CodiceFiscaleReferente`) REFERENCES `referente` (`CodiceFiscale`),
  CONSTRAINT `FKRap_SOC` FOREIGN KEY (`PartitaIVASocietà`) REFERENCES `società` (`PartitaIVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `referente`
--

DROP TABLE IF EXISTS `referente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referente` (
  `CodiceFiscale` char(16) NOT NULL,
  `Nome` varchar(35) NOT NULL,
  `Cognome` varchar(35) NOT NULL,
  `Luogo_Nascita` varchar(35) NOT NULL,
  `Data_Nascita` date NOT NULL,
  `Città_Residenza` varchar(35) NOT NULL,
  `CAP_Residenza` char(5) NOT NULL,
  `Provincia_Residenza` char(2) NOT NULL,
  `Via_Residenza` varchar(35) NOT NULL,
  `NumCivico_Residenza` int NOT NULL,
  `NumTelefono1` varchar(15) NOT NULL,
  `NumTelefono2` varchar(15) DEFAULT NULL,
  `Fax` varchar(15) DEFAULT NULL,
  `Email` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`CodiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `richieste`
--

DROP TABLE IF EXISTS `richieste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `richieste` (
  `IDRichiesta` varchar(10) NOT NULL,
  `Tipo` varchar(35) NOT NULL,
  `Motivazione` varchar(2000) NOT NULL,
  `DataLimite` date NOT NULL,
  `LivelloPriorità` int NOT NULL,
  `Stato` varchar(35) NOT NULL DEFAULT 'In lavorazione',
  PRIMARY KEY (`IDRichiesta`),
  CONSTRAINT `FKDettagliRichiesta_FK` FOREIGN KEY (`IDRichiesta`) REFERENCES `operazioni` (`IDOperazione`),
  CONSTRAINT `RangePriorità` CHECK ((`LivelloPriorità` between 1 and 5)),
  CONSTRAINT `StatoCorrente` CHECK ((`Stato` in (_utf8mb4'In lavorazione',_utf8mb4'Pronto',_utf8mb4'Consegnato')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sistema_operativo`
--

DROP TABLE IF EXISTS `sistema_operativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sistema_operativo` (
  `IDPC` varchar(10) NOT NULL,
  `Nome` varchar(35) NOT NULL,
  `Versione` varchar(10) NOT NULL,
  `DataUltimoAggiornamento` date NOT NULL,
  PRIMARY KEY (`IDPC`,`Nome`,`Versione`),
  CONSTRAINT `FKInstallazioneSO` FOREIGN KEY (`IDPC`) REFERENCES `pc` (`IDPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `società`
--

DROP TABLE IF EXISTS `società`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `società` (
  `PartitaIVA` char(11) NOT NULL,
  `CodiceFiscale` varchar(16) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Città_SedeLegale` varchar(35) NOT NULL,
  `CAP_SedeLegale` char(5) NOT NULL,
  `Provincia_SedeLegale` char(2) NOT NULL,
  `Via_SedeLegale` varchar(35) NOT NULL,
  `NumCivico_SedeLegale` int NOT NULL,
  PRIMARY KEY (`PartitaIVA`),
  UNIQUE KEY `IDSOCIETÀ_1` (`CodiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-16 18:38:20
