package com;
public class Pekerja {
    int id_pekerja, gaji_pokok;
    String password,nama,posisi,gender_pkrj,ttl_pkrj,alamat_tinggal;
    Unit unit;
//contructor kosong
    public Pekerja() {
    }
//constructor full
    public Pekerja(int id_pekerja, int gaji_pokok, String password, String nama, String posisi, String gender_pkrj, String ttl_pkrj, String alamat_tinggal, Unit id_unit) {
        this.id_pekerja = id_pekerja;
        this.gaji_pokok = gaji_pokok;
        this.password = password;
        this.nama = nama;
        this.posisi = posisi;
        this.gender_pkrj = gender_pkrj;
        this.ttl_pkrj = ttl_pkrj;
        this.alamat_tinggal = alamat_tinggal;
        this.unit = id_unit;
    }
//getter and setter
    public int getId_pekerja() {
        return id_pekerja;
    }

    public void setId_pekerja(int id_pekerja) {
        this.id_pekerja = id_pekerja;
    }

    public int getGaji_pokok() {
        return gaji_pokok;
    }

    public void setGaji_pokok(int gaji_pokok) {
        this.gaji_pokok = gaji_pokok;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getGender_pkrj() {
        return gender_pkrj;
    }

    public void setGender_pkrj(String gender_pkrj) {
        this.gender_pkrj = gender_pkrj;
    }

    public String getTtl_pkrj() {
        return ttl_pkrj;
    }

    public void setTtl_pkrj(String ttl_pkrj) {
        this.ttl_pkrj = ttl_pkrj;
    }

    public String getAlamat_tinggal() {
        return alamat_tinggal;
    }

    public void setAlamat_tinggal(String alamat_tinggal) {
        this.alamat_tinggal = alamat_tinggal;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit id_unit) {
        this.unit = id_unit;
    }
//toString
    @Override
    public String toString() {
        return "Pekerja{" + "id_pekerja=" + id_pekerja + ", gaji_pokok=" + gaji_pokok + ", password=" + password + ", nama=" + nama + ", posisi=" + posisi + ", gender_pkrj=" + gender_pkrj + ", ttl_pkrj=" + ttl_pkrj + ", alamat_tinggal=" + alamat_tinggal + ", id_unit=" + unit + '}';
    }

    
}
