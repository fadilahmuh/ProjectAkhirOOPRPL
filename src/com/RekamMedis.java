package com;
public class RekamMedis {
    int id_rekam;
    String obat,jenis,tanggal,keterangan,deskripsi;
    Pekerja pemeriksa;
    Pasien id_pasien;

    public RekamMedis() {
    }

    public RekamMedis(int id_rekam, int fid_pasien, String obat, String jenis, String tanggal, String keterangan, String deskripsi, Pekerja pemeriksa, Pasien id_pasien) {
        this.id_rekam = id_rekam;
        this.obat = obat;
        this.jenis = jenis;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.deskripsi = deskripsi;
        this.pemeriksa = pemeriksa;
        this.id_pasien = id_pasien;
    }
    

    public int getId_rekam() {
        return id_rekam;
    }

    public void setId_rekam(int id_rekam) {
        this.id_rekam = id_rekam;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
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

    public Pasien getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(Pasien id_pasien) {
        this.id_pasien = id_pasien;
    }

    @Override
    public String toString() {
        return "RekamMedis{" + "id_rekam=" + id_rekam + ", obat=" + obat + ", jenis=" + jenis + ", tanggal=" + tanggal + ", keterangan=" + keterangan + ", deskripsi=" + deskripsi + ", pemeriksa=" + pemeriksa + ", id_pasien=" + id_pasien + '}';
    }
    
    
}
