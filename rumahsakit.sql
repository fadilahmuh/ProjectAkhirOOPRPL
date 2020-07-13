-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.11-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for rumahsakit
CREATE DATABASE IF NOT EXISTS `rumahsakit` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `rumahsakit`;

-- Dumping structure for table rumahsakit.obat
CREATE TABLE IF NOT EXISTS `obat` (
  `id_obat` char(10) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `harga_obat` int(11) NOT NULL,
  PRIMARY KEY (`id_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.obat: ~0 rows (approximately)
/*!40000 ALTER TABLE `obat` DISABLE KEYS */;
/*!40000 ALTER TABLE `obat` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.pasien
CREATE TABLE IF NOT EXISTS `pasien` (
  `id_pasien` int(11) NOT NULL AUTO_INCREMENT,
  `nama_pasien` varchar(50) NOT NULL,
  `gender_pasien` char(2) DEFAULT NULL,
  `ttl_pasien` varchar(50) NOT NULL,
  `nik_pasien` varchar(50) NOT NULL,
  `kartu_sehat` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pasien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.pasien: ~0 rows (approximately)
/*!40000 ALTER TABLE `pasien` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasien` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.pekerja
CREATE TABLE IF NOT EXISTS `pekerja` (
  `id_pekerja` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `posisi` varchar(50) NOT NULL,
  `gender_pkrj` varchar(1) NOT NULL,
  `ttl_pkrj` varchar(30) NOT NULL,
  `alamat_tinggal` varchar(50) NOT NULL,
  `gaji_pokok` int(11) DEFAULT NULL,
  `unit_kerja` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pekerja`),
  KEY `FK_pekerja_unit` (`unit_kerja`),
  CONSTRAINT `FK_pekerja_unit` FOREIGN KEY (`unit_kerja`) REFERENCES `unit` (`kd_unit`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1649172186 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.pekerja: ~1 rows (approximately)
/*!40000 ALTER TABLE `pekerja` DISABLE KEYS */;
INSERT INTO `pekerja` (`id_pekerja`, `password`, `nama`, `posisi`, `gender_pkrj`, `ttl_pkrj`, `alamat_tinggal`, `gaji_pokok`, `unit_kerja`) VALUES
	(1649172185, 'password', 'Earlangga Adalah Telinga', 'Dokter', 'L', 'Bandung, 04/07/2020', 'Disana jauh gaboleh ikut', 8000000, 1);
/*!40000 ALTER TABLE `pekerja` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.rekam_medis
CREATE TABLE IF NOT EXISTS `rekam_medis` (
  `id_rekam` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasien` int(11) NOT NULL,
  `tanggal` char(10) NOT NULL,
  `jenis` char(12) NOT NULL,
  `deskripsi` varchar(50) DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  `tndk_obat` char(50) DEFAULT NULL,
  `pemeriksa` int(11) NOT NULL,
  PRIMARY KEY (`id_rekam`),
  KEY `FK_rekam_medis_pasien` (`id_pasien`),
  KEY `FK_rekam_medis_obat` (`tndk_obat`),
  KEY `FK_rekam_medis_pekerja` (`pemeriksa`),
  CONSTRAINT `FK_rekam_medis_obat` FOREIGN KEY (`tndk_obat`) REFERENCES `obat` (`id_obat`) ON UPDATE CASCADE,
  CONSTRAINT `FK_rekam_medis_pasien` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON UPDATE CASCADE,
  CONSTRAINT `FK_rekam_medis_pekerja` FOREIGN KEY (`pemeriksa`) REFERENCES `pekerja` (`id_pekerja`) ON UPDATE CASCADE,
  CONSTRAINT `FK_rekam_medis_tindakan` FOREIGN KEY (`tndk_obat`) REFERENCES `tindakan` (`id_tindakan`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.rekam_medis: ~0 rows (approximately)
/*!40000 ALTER TABLE `rekam_medis` DISABLE KEYS */;
/*!40000 ALTER TABLE `rekam_medis` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.tindakan
CREATE TABLE IF NOT EXISTS `tindakan` (
  `id_tindakan` char(10) NOT NULL,
  `unit_pengguna` int(11) NOT NULL,
  `nama_tindakan` varchar(50) NOT NULL DEFAULT '',
  `harga_tindakan` int(11) NOT NULL,
  PRIMARY KEY (`id_tindakan`),
  KEY `FK_tindakan_unit` (`unit_pengguna`),
  CONSTRAINT `FK_tindakan_unit` FOREIGN KEY (`unit_pengguna`) REFERENCES `unit` (`kd_unit`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.tindakan: ~0 rows (approximately)
/*!40000 ALTER TABLE `tindakan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tindakan` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.unit
CREATE TABLE IF NOT EXISTS `unit` (
  `kd_unit` int(11) NOT NULL AUTO_INCREMENT,
  `nama_unit` char(50) NOT NULL,
  `tarifdasar` int(11) NOT NULL,
  `denah_unit` varchar(50) NOT NULL,
  PRIMARY KEY (`kd_unit`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.unit: ~3 rows (approximately)
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` (`kd_unit`, `nama_unit`, `tarifdasar`, `denah_unit`) VALUES
	(1, 'Klinik Gigi', 50000, 'Lt 2, Ruang B2'),
	(2, 'Klinik THT', 45000, 'Lt2, Ruang C1'),
	(3, 'Klinik Mata', 55000, 'Lt 1, Ruang A4');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
