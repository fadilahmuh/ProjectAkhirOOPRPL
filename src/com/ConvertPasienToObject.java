package com;

import exec.ExecutePasien;
import java.util.ArrayList;
import java.util.List;

public class ConvertPasienToObject {
    public String[][] getPasien(){
        List<Pasien> myps = new ArrayList<Pasien>();
        ExecutePasien eps = new ExecutePasien();
        myps = eps.getPasien();
        String[][] dataPasien = new String[myps.size()][7];
        int i=0;
        for(Pasien ps : myps){
            dataPasien[i][0] = ""+ps.getId_pasien();
            dataPasien[i][1] = ps.getNama_pasien();
            dataPasien[i][2] = ps.getGender_pasien();
            dataPasien[i][3] = ps.getGender_pasien();
            dataPasien[i][4] = ps.getTtl_pasien();
            dataPasien[i][5] = ps.getNik_pasien();
            dataPasien[i][6] = ps.getAsuransi();
            i++;
        }
        return dataPasien;
    }
}
