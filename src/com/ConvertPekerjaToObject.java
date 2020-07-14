package com;

import exec.ExecutePekerja;
import java.util.ArrayList;
import java.util.List;

public class ConvertPekerjaToObject {
    public String[][] getPekerja(){
        List<Pekerja> mypk = new ArrayList<Pekerja>();
        ExecutePekerja epk = new ExecutePekerja();
        mypk = epk.getPekerja();
        String[][] dataPekerja = new String[mypk.size()][9];
        int i=0;
        for(Pekerja pk : mypk){
            dataPekerja[i][0] = ""+pk.getId_pekerja();
            dataPekerja[i][1] = pk.getPassword();
            dataPekerja[i][2] = pk.getNama();
            dataPekerja[i][3] = pk.getPosisi();
            dataPekerja[i][4] = pk.getGender_pkrj();
            dataPekerja[i][5] = pk.getTtl_pkrj();
            dataPekerja[i][6] = pk.getAlamat_tinggal();
            dataPekerja[i][7] = ""+pk.getGaji_pokok();
            dataPekerja[i][8] = ""+pk.getFunit_kerja();
            i++;
        }
        return dataPekerja;
    }
}
