/*
SQLyog Professional v12.4.3 (64 bit)
MySQL - 10.4.22-MariaDB : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `library`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `nama` varchar(40) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`nama`,`email`,`password`) values 
('mauludinegi','Mauludinegi@gmail.com','1234');

/*Table structure for table `buku` */

DROP TABLE IF EXISTS `buku`;

CREATE TABLE `buku` (
  `idBuku` varchar(50) NOT NULL,
  `judul` varchar(40) NOT NULL,
  `pengarang` varchar(40) NOT NULL,
  `penerbit` varchar(40) NOT NULL,
  `tahun` varchar(40) NOT NULL,
  `status` varchar(50) NOT NULL,
  `tanggal` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`idBuku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `buku` */

insert  into `buku`(`idBuku`,`judul`,`pengarang`,`penerbit`,`tahun`,`status`,`tanggal`) values 
('AAA','BBB','fds','fdfs','fsd','ada','2022-12-24 03:15:21'),
('ACL','World cup','Who','Who','2020','ada','2022-12-25 15:43:40'),
('B002','Kancil','Saha','Saha','2020','ada','2022-12-25 14:27:14'),
('B003','Siapa Saja','Who','Who','2022','ada','2022-12-25 15:59:33'),
('B004','Bisa','Bisa','Bisa','Bisa','ada','2022-12-25 16:02:17');

/*Table structure for table `peminjaman` */

DROP TABLE IF EXISTS `peminjaman`;

CREATE TABLE `peminjaman` (
  `idAnggota` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `noTlp` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `idBuku` varchar(50) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp(),
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`idAnggota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `peminjaman` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
