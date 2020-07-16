package exec;

import com.Obat;
import com.Pasien;
import com.Pekerja;
import com.RekamMedis;
import com.Tindakan;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ExecuteRekamMedis {
    private ExecutePasien execPsn;
    private ExecutePekerja execPkj;
    private ExecuteObat execObt;
    private ExecuteTindakan execTnd;
    
    public List<RekamMedis> getRekam(int idpsn){
        List<RekamMedis> listRekam = new ArrayList<>();
        String query = "select * from rekam_medis";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            execPsn = new ExecutePasien();
            execPkj = new ExecutePekerja();
            execObt = new ExecuteObat();
            execTnd = new ExecuteTindakan();
            Pasien ps = execPsn.getPasien(idpsn);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                RekamMedis rm = new RekamMedis();
                rm.setId_rekam(rs.getInt("id_rekam"));
                rm.setId_pasien(ps);
                rm.setTanggal(rs.getString("tanggal"));                
                rm.setJenis(rs.getString("jenis"));
                rm.setKeterangan(rs.getString("keterangan"));
                
                Pekerja pkj = execPkj.getPekerja(rs.getInt("pemeriksa"));
                rm.setPemeriksa(pkj);
                
                
                if (rm.getJenis().equals("Diagnosis")) {
                    rm.setDeskripsi(rs.getString("deskripsi"));
                } else if (rm.getJenis().equals("Obat")) {
                    Obat ob = execObt.getItemObat(rs.getString("obat"));
                    rm.setDeskripsi(ob.getNama_obat());
                } else if (rm.getJenis().equals("Tindakan")) {
                    Tindakan td = execTnd.getItemTindakan(rs.getString("tindakan"));
                    rm.setDeskripsi(td.getNama_tindakan());
                }
                
                listRekam.add(rm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listRekam;
    }
    
    public String[][] Rekamtoobjek(){
        List<RekamMedis> myRm = new ArrayList<RekamMedis>();
        ExecuteRekamMedis eRm = new ExecuteRekamMedis();
        myRm = eRm.getRekam(152018010);
        String[][] dataRekam = new String[myRm.size()][5];
        int i=0;
        for(RekamMedis Rm : myRm){
            dataRekam[i][0] = Rm.getTanggal();
            dataRekam[i][1] = Rm.getPemeriksa().getNama();
            dataRekam[i][2] = Rm.getJenis();
            dataRekam[i][3] = Rm.getDeskripsi();
            dataRekam[i][4] = Rm.getKeterangan();
            i++;
        }
        return dataRekam;
    }
    
//    public DefaultTableModel Rekamtoobjek (List<RekamMedis> list) {        
//        DefaultTableModel model = new DefaultTableModel();
//        Object[] kolom = new Object[5];
//        
//        kolom[0]="Airport Code";
//        kolom[1]="Airport Names";
//        kolom[2]="City";
//        kolom[3]="Country";
//        kolom[4]="Time Zone";
//
//        
//        model.setColumnIdentifiers(kolom);
//        Object[] data = new Object[5];
//        for(Bandara bnd : list){
//            data[0]= bnd.getKd_bandara();
//            data[1]= bnd.getNama();
//            data[2]= bnd.getKota();
//            data[3]= bnd.getNegara();
//            data[4]= bnd.getUtc();            
//            model.addRow(data);
//        }
//        
//        return model;        
//    }
//    public int insertsupplier(Supplier sp){
//        int hasil = 0;
//        String query = "Insert into supplier(id_supplier, nama, alamat, email, no_hp)"
//                + "value("+ sp.getId_supplier()+",'"+ 
//                sp.getNama()+"','"+ sp.getAlamat()+"','"+sp.getEmail()+"','"+ sp.getNo_hp()+"')";
//        ConnectionManager conMan = new ConnectionManager();
//        Connection conn = conMan.logOn();
//        try {
//            Statement stm = conn.createStatement();
//            hasil = stm.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ExecuteSupplier.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        conMan.logOff();
//        return hasil;
//    }
}
