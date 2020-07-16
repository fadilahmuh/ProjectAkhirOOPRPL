/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import exec.ExecutePekerja;
import exec.ExecuteRekamMedis;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author fadil
 */
public class scratch {
    private static ExecutePekerja execPkj;
    private static ExecuteRekamMedis execRkm;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        
//        Scanner input = new Scanner(System.in);
//        
//        System.out.print("Masukan Username : ");
//        int id = input.nextInt();
//        System.out.print("Masukan Pass : ");
//        String pass = input.nextLine();
//        
//        execPkj = new ExecutePekerja();
//        Pekerja dokter  = execPkj.getPekerja(id);
//        
//        System.out.println(dokter.toString());
            
//        execPkj = new ExecutePekerja();
//        
//        
//        Pekerja prt1 = execPkj.getPekerja(152018001);
//        System.out.println(prt1.toString());
//        if (prt1.getId_pekerja() != 0  && prt1.getPosisi().equals("Perawat") ){
//            System.out.println("benar, perawat");
//        } else {
//            System.out.println("cek id");
//        }
            
//        Pekerja pkj = new Pekerja();
//        System.out.println(pkj);
        
//        LocalTime jk = LocalTime.now();
//        
//        
//        Calendar c = Calendar.getInstance();
//        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
//
//        if(timeOfDay >= 0 && timeOfDay < 11){
//            System.out.println("pagi");
//        }else if(timeOfDay >= 11 && timeOfDay < 3){
//            System.out.println("siang");
//        }else if(timeOfDay >= 3 && timeOfDay < 18){
//            System.out.println("sore");
//        }else if(timeOfDay >= 18 && timeOfDay < 24){
//            System.out.println("malem");
//        }
        
//        execRkm = new ExecuteRekamMedis();
//        List<RekamMedis> listRkm = execRkm.getRekam(152018010);
//        for(RekamMedis rkm : listRkm){
//            System.out.println(rkm.toString());
//        }
        DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String tanggal = fd.format(LocalDateTime.now());
        System.out.println(tanggal);
    }
    
}
