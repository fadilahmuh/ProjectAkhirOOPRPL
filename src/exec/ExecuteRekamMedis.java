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
        String query = "select * from rekam_medis where id_pasien=" + idpsn + " Order by tanggal desc";
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
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, deskripsi, keterangan, pemeriksa)"
                + "value("+ rkm.getPasien().getId_pasien()+",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getDeskripsi()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+")";
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
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, keterangan, pemeriksa, tindakan)"
                + "value("+ rkm.getPasien().getId_pasien()+",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+",'"+ rkm.getDeskripsi()+"')";
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
                String query = "Insert into rekam_medis(id_pasien, tanggal, jenis, keterangan, pemeriksa, obat)"
                 + "value("+ rkm.getPasien().getId_pasien() + ",'"+ rkm.getTanggal()+"','"+rkm.getJenis()+"','"+ rkm.getKeterangan()+"',"+ rkm.getPemeriksa().getId_pekerja()+",'"+ rkm.getDeskripsi()+"')";
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
}
