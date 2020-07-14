package com;

import exec.ExecuteUnit;
import java.util.ArrayList;
import java.util.List;

public class ConvertUnitToObject {
    public String[][] getUnit(){
        List<Unit> myun = new ArrayList<Unit>();
        ExecuteUnit eun = new ExecuteUnit();
        myun = eun.getUnit();
        String[][] dataUnit = new String[myun.size()][4];
        int i=0;
        for(Unit un : myun){
            dataUnit[i][0] = ""+un.getKd_unit();
            dataUnit[i][1] = un.getNama_unit();
            dataUnit[i][2] = ""+un.getTarifdasar();
            dataUnit[i][3] = un.getDenah_unit();
            i++;
        }
        return dataUnit;
    }
}
