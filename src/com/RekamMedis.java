package com;
public class RekamMedis {
    int id_rekam;
    String jenis,tanggal,keterangan,deskripsi;
    Pekerja pemeriksa;
<<<<<<< HEAD
    Pasien pasien;

=======
    Pasien id_pasien;
//contructor kosong
>>>>>>> 4df53bfa31a5d44792334a9d5f5d2ba1037dfc9a
    public RekamMedis() {
    }
//constructor full
    public RekamMedis(int id_rekam, int fid_pasien, String obat, String jenis, String tanggal, String keterangan, String deskripsi, Pekerja pemeriksa, Pasien id_pasien) {
        this.id_rekam = id_rekam;
        this.jenis = jenis;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.deskripsi = deskripsi;
        this.pemeriksa = pemeriksa;
        this.pasien = id_pasien;
    }
    
//getter and setter
    public int getId_rekam() {
        return id_rekam;
    }

    public void setId_rekam(int id_rekam) {
        this.id_rekam = id_rekam;
    }       

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Pekerja getPemeriksa() {
        return pemeriksa;
    }

    public void setPemeriksa(Pekerja pemeriksa) {
        this.pemeriksa = pemeriksa;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien id_pasien) {
        this.pasien = id_pasien;
    }
//toString
    @Override
    public String toString() {
        return "RekamMedis{" + "id_rekam=" + id_rekam  + ", jenis=" + jenis + ", tanggal=" + tanggal + ", keterangan=" + keterangan + ", deskripsi=" + deskripsi + ", pemeriksa=" + pemeriksa + ", pasien=" + pasien + '}';
    }
    
    
}
