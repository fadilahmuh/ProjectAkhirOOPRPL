package com;
public class Tindakan {
    String id_tindakan,nama_tindakan;
    int funit_pengguna,harga_tindakan;
//constructor kosong
    public Tindakan() {
    }
//constructor full
    public Tindakan(String id_tindakan, String nama_tindakan, int funit_pengguna, int harga_tindakan) {
        this.id_tindakan = id_tindakan;
        this.nama_tindakan = nama_tindakan;
        this.funit_pengguna = funit_pengguna;
        this.harga_tindakan = harga_tindakan;
    }
//getter and setter
    public String getId_tindakan() {
        return id_tindakan;
    }

    public void setId_tindakan(String id_tindakan) {
        this.id_tindakan = id_tindakan;
    }

    public String getNama_tindakan() {
        return nama_tindakan;
    }

    public void setNama_tindakan(String nama_tindakan) {
        this.nama_tindakan = nama_tindakan;
    }

    public int getFunit_pengguna() {
        return funit_pengguna;
    }

    public void setFunit_pengguna(int funit_pengguna) {
        this.funit_pengguna = funit_pengguna;
    }

    public int getHarga_tindakan() {
        return harga_tindakan;
    }

    public void setHarga_tindakan(int harga_tindakan) {
        this.harga_tindakan = harga_tindakan;
    }
//toString
    @Override
    public String toString() {
        return "Tindakan{" + "id_tindakan=" + id_tindakan + ", nama_tindakan=" + nama_tindakan + ", funit_pengguna=" + funit_pengguna + ", harga_tindakan=" + harga_tindakan + '}';
    }
    
    
}
