package com;

import exec.ExecuteTindakan;
import java.util.ArrayList;
import java.util.List;

public class ConvertTindakanToObject {
    public String[][] getTindakan(){
        List<Tindakan> mytk = new ArrayList<Tindakan>();
        ExecuteTindakan etk = new ExecuteTindakan();
        mytk = etk.getTindakan();
        String[][] dataTindakan = new String[mytk.size()][4];
        int i=0;
        for(Tindakan tk : mytk){
            dataTindakan[i][0] = tk.getId_tindakan();
            dataTindakan[i][1] = ""+tk.getFunit_pengguna();
            dataTindakan[i][2] = tk.getNama_tindakan();
            dataTindakan[i][3] = ""+tk.getHarga_tindakan();
            i++;
        }
        return dataTindakan;
    }
}
