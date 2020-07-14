package com;

import exec.ExecuteObat;
import java.util.ArrayList;
import java.util.List;

public class ConvertObatToObject {
    public String[][] getObat(){
        List<Obat> myob = new ArrayList<Obat>();
        ExecuteObat eob = new ExecuteObat();
        myob = eob.getObat();
        String[][] dataObat = new String[myob.size()][3];
        int i=0;
        for(Obat ob : myob){
            dataObat[i][0] = ob.getId_obat();
            dataObat[i][1] = ob.getNama_obat();
            dataObat[i][2] = ""+ob.getHarga_obat();
            i++;
        }
        return dataObat;
    }
}
