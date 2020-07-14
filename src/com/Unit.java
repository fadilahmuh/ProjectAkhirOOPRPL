package com;
public class Unit {
    int kd_unit,tarifdasar;
    String nama_unit,denah_unit;

    public Unit() {
    }
    

    public Unit(int kd_unit, int tarifdasar, String nama_unit, String denah_unit) {
        this.kd_unit = kd_unit;
        this.tarifdasar = tarifdasar;
        this.nama_unit = nama_unit;
        this.denah_unit = denah_unit;
    }

    public int getKd_unit() {
        return kd_unit;
    }

    public void setKd_unit(int kd_unit) {
        this.kd_unit = kd_unit;
    }

    public int getTarifdasar() {
        return tarifdasar;
    }

    public void setTarifdasar(int tarifdasar) {
        this.tarifdasar = tarifdasar;
    }

    public String getNama_unit() {
        return nama_unit;
    }

    public void setNama_unit(String nama_unit) {
        this.nama_unit = nama_unit;
    }

    public String getDenah_unit() {
        return denah_unit;
    }

    public void setDenah_unit(String denah_unit) {
        this.denah_unit = denah_unit;
    }

    @Override
    public String toString() {
        return "Unit{" + "kd_unit=" + kd_unit + ", tarifdasar=" + tarifdasar + ", nama_unit=" + nama_unit + ", denah_unit=" + denah_unit + '}';
    }
    
    
}
