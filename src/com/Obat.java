package com;
public class Obat {
    String id_obat,nama_obat;
    int harga_obat;

    public Obat() {
    }

    public Obat(String id_obat, String nama_obat, int harga_obat) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.harga_obat = harga_obat;
    }

    public String getId_obat() {
        return id_obat;
    }

    public void setId_obat(String id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public int getHarga_obat() {
        return harga_obat;
    }

    public void setHarga_obat(int harga_obat) {
        this.harga_obat = harga_obat;
    }

    @Override
    public String toString() {
        return "Obat{" + "id_obat=" + id_obat + ", nama_obat=" + nama_obat + ", harga_obat=" + harga_obat + '}';
    }
    
}
