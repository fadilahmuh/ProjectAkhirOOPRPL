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

-- Dumping data for table rumahsakit.obat: ~8 rows (approximately)
/*!40000 ALTER TABLE `obat` DISABLE KEYS */;
INSERT INTO `obat` (`id_obat`, `nama_obat`, `harga_obat`) VALUES
	('OB-M1', 'Paracetamol', 40000),
	('OB-M2', 'Amoksilin', 35000),
	('OB-M3', 'Ambroxol', 36000),
	('OB-M4', 'Aspirin', 30000),
	('OB-M5', 'Biotin', 50000),
	('OB-M6', 'Bodrex', 15000),
	('OB-M7', 'Bupropion', 25000),
	('OB-M8', 'Cataflam', 40000);
/*!40000 ALTER TABLE `obat` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.pasien
CREATE TABLE IF NOT EXISTS `pasien` (
  `id_pasien` int(11) NOT NULL AUTO_INCREMENT,
  `nama_pasien` varchar(50) NOT NULL,
  `gender_pasien` char(2) DEFAULT NULL,
  `ttl_pasien` varchar(50) NOT NULL,
  `nik_pasien` varchar(50) NOT NULL,
  `asuransi` varchar(10) NOT NULL,
  `no_telp` char(15) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_pasien`)
) ENGINE=InnoDB AUTO_INCREMENT=152018011 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.pasien: ~1 rows (approximately)
/*!40000 ALTER TABLE `pasien` DISABLE KEYS */;
INSERT INTO `pasien` (`id_pasien`, `nama_pasien`, `gender_pasien`, `ttl_pasien`, `nik_pasien`, `asuransi`, `no_telp`, `alamat`) VALUES
	(152018010, 'Rangga Bayuh', 'L', '00/00/0000, Mars', '321654983190909', 'BPJS', '08126345679', 'Mars setelah Bumi');
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
) ENGINE=InnoDB AUTO_INCREMENT=2147483649 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.pekerja: ~3 rows (approximately)
/*!40000 ALTER TABLE `pekerja` DISABLE KEYS */;
INSERT INTO `pekerja` (`id_pekerja`, `password`, `nama`, `posisi`, `gender_pkrj`, `ttl_pkrj`, `alamat_tinggal`, `gaji_pokok`, `unit_kerja`) VALUES
	(152018002, 'fadil', 'Fannie M Fadilah S', 'Perawat', 'L', 'Bandung, 00/00/0000', 'asjkdnakjsndkajnsdjaksnd', 5000000, NULL),
	(152018021, 'password', 'Earlangga Adalah Telingalangga', 'Dokter', 'L', 'Bandung, 04/07/2020', 'Disana jauh gaboleh ikut', 8000000, 1),
	(152018024, '321654', 'M Nadhif', 'Perawat', 'L', 'Serang, 00/00/0000', 'Ujung kulon dunia', 5000000, NULL);
/*!40000 ALTER TABLE `pekerja` ENABLE KEYS */;

-- Dumping structure for table rumahsakit.rekam_medis
CREATE TABLE IF NOT EXISTS `rekam_medis` (
  `id_rekam` int(11) NOT NULL AUTO_INCREMENT,
  `id_pasien` int(11) NOT NULL,
  `tanggal` char(10) NOT NULL,
  `jenis` char(12) NOT NULL,
  `deskripsi` varchar(50) DEFAULT NULL,
  `keterangan` varchar(50) DEFAULT NULL,
  `pemeriksa` int(11) NOT NULL,
  `obat` char(10) DEFAULT NULL,
  `tindakan` char(10) DEFAULT NULL,
  PRIMARY KEY (`id_rekam`),
  KEY `FK_rekam_medis_pasien` (`id_pasien`),
  KEY `FK_rekam_medis_pekerja` (`pemeriksa`),
  KEY `FK_rekam_medis_obat` (`obat`),
  KEY `FK_rekam_medis_tindakan` (`tindakan`),
  CONSTRAINT `FK_rekam_medis_obat` FOREIGN KEY (`obat`) REFERENCES `obat` (`id_obat`) ON UPDATE CASCADE,
  CONSTRAINT `FK_rekam_medis_pasien` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON UPDATE CASCADE,
  CONSTRAINT `FK_rekam_medis_pekerja` FOREIGN KEY (`pemeriksa`) REFERENCES `pekerja` (`id_pekerja`) ON UPDATE CASCADE,
  CONSTRAINT `FK_rekam_medis_tindakan` FOREIGN KEY (`tindakan`) REFERENCES `tindakan` (`id_tindakan`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table rumahsakit.rekam_medis: ~5 rows (approximately)
/*!40000 ALTER TABLE `rekam_medis` DISABLE KEYS */;
INSERT INTO `rekam_medis` (`id_rekam`, `id_pasien`, `tanggal`, `jenis`, `deskripsi`, `keterangan`, `pemeriksa`, `obat`, `tindakan`) VALUES
	(14, 152018010, '13/07/2020', 'Diagnosis', 'Nyeri sudah 3 hari', 'sudah minum obat tradisional', 152018021, NULL, NULL),
	(15, 152018010, '15/07/2020', 'Diagnosis', 'Karang gigi', 'Graham kanan bawah', 152018021, NULL, NULL),
	(16, 152018010, '15/07/2020', 'Diagnosis', 'Lubang gigi taring atas', 'harus tambal', 152018021, NULL, NULL),
	(18, 152018010, '15/07/2020', 'Tindakan', NULL, '2 lubang', 152018021, NULL, 'TN-G1'),
	(19, 152018010, '15/07/2020', 'Obat', NULL, 'Pereda nyeri, setiap sebelum makan', 152018021, 'OB-M4', NULL);
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

-- Dumping data for table rumahsakit.tindakan: ~4 rows (approximately)
/*!40000 ALTER TABLE `tindakan` DISABLE KEYS */;
INSERT INTO `tindakan` (`id_tindakan`, `unit_pengguna`, `nama_tindakan`, `harga_tindakan`) VALUES
	('TN-G1', 1, 'Tambal Gigi', 200000),
	('TN-G2', 1, 'Cabut Gigi', 100000),
	('TN-G3', 1, 'Bleaching Gigi', 5000000),
	('TN-M1', 3, 'Operasi Lasik', 12000000);
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
