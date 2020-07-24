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
        String query = "select * from rekam_medis where id_pasien=" + idpsn + " and STATUS NOT IN ('Dihapus', 'Diubah')  Order by id_rekam desc";
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
                rm.setPasien(ps);
                rm.setTanggal(rs.getString("tanggal"));                
                rm.setJenis(rs.getString("jenis"));
                rm.setKeterangan(rs.getString("keterangan"));
                rm.setStatus(rs.getString("status"));
                
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
    public List<RekamMedis> getRekam2(int idpsn, int iddkt, String tanggal){
        List<RekamMedis> listRekam = new ArrayList<>();
        String query = "select * from rekam_medis where id_pasien=" + idpsn + " and pemeriksa="+ iddkt+" and tanggal='" + tanggal+"' Order by id_rekam desc";
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
                rm.setPasien(ps);
                rm.setTanggal(rs.getString("tanggal"));                
                rm.setJenis(rs.getString("jenis"));
                rm.setKeterangan(rs.getString("keterangan"));
                rm.setStatus(rs.getString("status"));
                
                Pekerja pkj = execPkj.getPekerja(rs.getInt("pemeriksa"));
                rm.setPemeriksa(pkj);
                
                
                if (rm.getJenis().equals("Diagnosis")) {
                    rm.setDeskripsi(rs.getString("deskripsi"));
                } else if (rm.getJenis().equals("Obat")) {
                    Obat ob = execObt.getItemObat(rs.getString("obat"));
                    rm.setDeskripsi(ob.getNama_obat()+ ", "+rs.getString("obat"));
                } else if (rm.getJenis().equals("Tindakan")) {
                    Tindakan td = execTnd.getItemTindakan(rs.getString("tindakan"));
                    rm.setDeskripsi(td.getNama_tindakan()+ ", "+rs.getString("tindakan"));
                }
                
                listRekam.add(rm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listRekam;
    }
    public RekamMedis getItemRekam(int id){
        RekamMedis rm = new RekamMedis();
        String query = "select * from rekam_medis where id_rekam=" + id;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            execPsn = new ExecutePasien();
            execPkj = new ExecutePekerja();
            execObt = new ExecuteObat();
            execTnd = new ExecuteTindakan();            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                rm.setId_rekam(rs.getInt("id_rekam"));
                Pasien ps = execPsn.getPasien(rs.getInt("id_pasien"));
                rm.setPasien(ps);
                rm.setTanggal(rs.getString("tanggal"));                
                rm.setJenis(rs.getString("jenis"));
                rm.setKeterangan(rs.getString("keterangan"));
                rm.setStatus(rs.getString("status"));
                
                Pekerja pkj = execPkj.getPekerja(rs.getInt("pemeriksa"));
                rm.setPemeriksa(pkj);
                
                
                if (rm.getJenis().equals("Diagnosis")) {
                    rm.setDeskripsi(rs.getString("deskripsi"));
                } else if (rm.getJenis().equals("Obat")) {
                    Obat ob = execObt.getItemObat(rs.getString("obat"));
                    rm.setDeskripsi(ob.getNama_obat()+ ", "+rs.getString("obat"));
                } else if (rm.getJenis().equals("Tindakan")) {
                    Tindakan td = execTnd.getItemTindakan(rs.getString("tindakan"));
                    rm.setDeskripsi(td.getNama_tindakan()+ ", "+rs.getString("tindakan"));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return rm;
    }
    public List<String> getRekamPasien(int iddkt, String tanggal ){
        List<String> listPasien = new ArrayList<>();
        String query = "SELECT * FROM rekam_medis where pemeriksa= " +iddkt+" and tanggal='"+ tanggal+"'GROUP BY id_pasien";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            execPsn = new ExecutePasien();
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pasien ps = execPsn.getPasien(rs.getInt("id_pasien"));
                String rm = String.valueOf(rs.getInt("id_pasien")) + ", " +ps.getNama_pasien();                
                
                listPasien.add(rm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listPasien;
    }
    
    public String[][] Rekamtoobjek(int id){
        List<RekamMedis> myRm = new ArrayList<>();
        ExecuteRekamMedis eRm = new ExecuteRekamMedis();
        myRm = eRm.getRekam(id);
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
    
    
    public int insertRekam(List<RekamMedis> newRm){
        int hasil = 0;
        
        for(RekamMedis rkm : newRm){
            if (rkm.getJenis().equals("Diagnosis")) {
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, deskripsi, keterangan, pemeriksa, status)"
                + "value("+ rkm.getPasien().getId_pasien()+",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getDeskripsi()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja() +",'"+ rkm.getStatus() +"')";
            ConnectionManager conMan = new ConnectionManager();
            Connection conn = conMan.logOn();
            try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
            }
            conMan.logOff();
            } else if (rkm.getJenis().equals("Tindakan")) {
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, keterangan, pemeriksa, tindakan, status)"
                + "value("+ rkm.getPasien().getId_pasien()+",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+",'"+ rkm.getDeskripsi()+"','"+ rkm.getStatus()+"')";
            ConnectionManager conMan = new ConnectionManager();
            Connection conn = conMan.logOn();
            try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
            }
            conMan.logOff();
            }  else if (rkm.getJenis().equals("Obat")) {
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, keterangan, pemeriksa, obat, status)"
                 + "value("+ rkm.getPasien().getId_pasien() + ",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+",'"+ rkm.getDeskripsi()+"','"+ rkm.getStatus()+"')";
            ConnectionManager conMan = new ConnectionManager();
            Connection conn = conMan.logOn();
            try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
            }
            conMan.logOff();
            }
        }
        
        return hasil;
    }
    public int insertItemRekam(RekamMedis rkm){
        int hasil = 0;
        
        
            if (rkm.getJenis().equals("Diagnosis")) {
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, deskripsi, keterangan, pemeriksa, status)"
                + "value("+ rkm.getPasien().getId_pasien()+",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getDeskripsi()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja() +",'"+ rkm.getStatus() +"')";
            ConnectionManager conMan = new ConnectionManager();
            Connection conn = conMan.logOn();
            try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
            }
            conMan.logOff();
            } else if (rkm.getJenis().equals("Tindakan")) {
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, keterangan, pemeriksa, tindakan, status)"
                + "value("+ rkm.getPasien().getId_pasien()+",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+",'"+ rkm.getDeskripsi()+"','"+ rkm.getStatus() +"')";
            ConnectionManager conMan = new ConnectionManager();
            Connection conn = conMan.logOn();
            try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
            }
            conMan.logOff();
            }  else if (rkm.getJenis().equals("Obat")) {
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, keterangan, pemeriksa, obat, status)"
                 + "value("+ rkm.getPasien().getId_pasien() + ",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+",'"+ rkm.getDeskripsi()+"','"+ rkm.getStatus() +"')";
            ConnectionManager conMan = new ConnectionManager();
            Connection conn = conMan.logOn();
            try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
            }
            conMan.logOff();
            }
        
        
        return hasil;
    }
    public int updateItemRekam(RekamMedis rkm){
        int hasil = 0;

        String query = "update rekam_medis set status='"+rkm.getStatus()+"' where id_rekam=" + rkm.getId_rekam();
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
            
        
        
        return hasil;
    }
    
    public DefaultTableModel Rekamtoobjek (List<RekamMedis> list) {        
        DefaultTableModel model = new DefaultTableModel();
        Object[] kolom = new Object[5];
        
        kolom[0]="Id rekam";
        kolom[1]="Jenis";
        kolom[2]="Deskripsi";
        kolom[3]="Keterangan";
        kolom[4]="Status";
        
        model.setColumnIdentifiers(kolom);
        Object[] data = new Object[5];
        for(RekamMedis jdw : list){
            data[0]= jdw.getId_rekam();
            data[1]= jdw.getJenis();
            data[2]= jdw.getDeskripsi();
            data[3]= jdw.getKeterangan();
            data[4]= jdw.getStatus();
            
            model.addRow(data);            
        }        
        return model;        
    }       
        
}
