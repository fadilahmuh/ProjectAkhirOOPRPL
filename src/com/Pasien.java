package com;
public class Pasien {
    int id_pasien;
    String nama_pasien,gender_pasien,ttl_pasien,nik_pasien,asuransi;

    public Pasien() {
    }

    public Pasien(int id_pasien, String nama_pasien, String gender_pasien, String ttl_pasien, String nik_pasien, String asuransi) {
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.gender_pasien = gender_pasien;
        this.ttl_pasien = ttl_pasien;
        this.nik_pasien = nik_pasien;
        this.asuransi = asuransi;
    }

    public int getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(int id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getGender_pasien() {
        return gender_pasien;
    }

    public void setGender_pasien(String gender_pasien) {
        this.gender_pasien = gender_pasien;
    }

    public String getTtl_pasien() {
        return ttl_pasien;
    }

    public void setTtl_pasien(String ttl_pasien) {
        this.ttl_pasien = ttl_pasien;
    }

    public String getNik_pasien() {
        return nik_pasien;
    }

    public void setNik_pasien(String nik_pasien) {
        this.nik_pasien = nik_pasien;
    }

    public String getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
    }

    @Override
    public String toString() {
        return "Pasien{" + "id_pasien=" + id_pasien + ", nama_pasien=" + nama_pasien + ", gender_pasien=" + gender_pasien + ", ttl_pasien=" + ttl_pasien + ", nik_pasien=" + nik_pasien + ", asuransi=" + asuransi + '}';
    }
    
    
}
