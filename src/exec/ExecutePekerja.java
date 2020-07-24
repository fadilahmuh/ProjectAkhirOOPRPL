package exec;

import com.Pekerja;
import com.Unit;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutePekerja {
    private ExecuteUnit execUnt;
    
    public Pekerja getPekerja(int id){
        Pekerja pkj = new Pekerja();
        String query = "select * from pekerja where id_pekerja=" + id;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            execUnt = new ExecuteUnit();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                pkj.setId_pekerja(rs.getInt("id_pekerja"));
                pkj.setPassword(rs.getString("password"));
                pkj.setNama(rs.getString("nama"));
                pkj.setPosisi(rs.getString("posisi"));
                pkj.setGender_pkrj(rs.getString("gender_pkrj"));
                pkj.setTtl_pkrj(rs.getString("ttl_pkrj"));
                pkj.setAlamat_tinggal(rs.getString("alamat_tinggal"));
                pkj.setGaji_pokok(rs.getInt("gaji_pokok"));

                if (rs.getInt("unit_kerja") == 0) {
                    pkj.setUnit(null);
                } else {
                    Unit unt = execUnt.getUnit(rs.getInt("unit_kerja"));            
                    pkj.setUnit(unt);
                }            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePekerja.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return pkj ;
    }
    
    public List<Pekerja> getListPkj(){
        List<Pekerja> listpkj = new ArrayList<>();
        String query = "select * from pekerja";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            execUnt = new ExecuteUnit();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pekerja pkj = new Pekerja();
                pkj.setId_pekerja(rs.getInt("id_pekerja"));
                pkj.setPassword(rs.getString("password"));
                pkj.setNama(rs.getString("nama"));
                pkj.setPosisi(rs.getString("posisi"));
                pkj.setGender_pkrj(rs.getString("gender_pkrj"));
                pkj.setTtl_pkrj(rs.getString("ttl_pkrj"));
                pkj.setAlamat_tinggal(rs.getString("alamat_tinggal"));
                pkj.setGaji_pokok(rs.getInt("gaji_pokok"));

                if (rs.getInt("unit_kerja") == 0) {
                    pkj.setUnit(null);
                } else {
                    Unit unt = execUnt.getUnit(rs.getInt("unit_kerja"));            
                    pkj.setUnit(unt);
                }         
                
                listpkj.add(pkj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listpkj;
    }
    
    public int updatePekerja(Pekerja pkj){
        int hasil = 0;
        String query = "update pekerja set password='"+ pkj.getPassword()+"' , nama='"+ pkj.getNama()+"' , alamat_tinggal='"+ pkj.getAlamat_tinggal()+ "' where id_pekerja =" + pkj.getId_pekerja();
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
                Statement stm = conn.createStatement();
                hasil = stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(ExecutePekerja.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        conMan.logOff();
        return hasil;
    }
    
    public List<Pekerja> getPerawat(int id){
        List<Pekerja> listpkj = new ArrayList<>();
        String query;
        if (id == 0) {
            query = "select * from pekerja where posisi = 'Perawat' ";
        }else  {
            query = "select * from pekerja where posisi = 'Perawat' and id_pekerja!=" + id;
        }
         
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            execUnt = new ExecuteUnit();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pekerja pkj = new Pekerja();
                pkj.setId_pekerja(rs.getInt("id_pekerja"));
                pkj.setPassword(rs.getString("password"));
                pkj.setNama(rs.getString("nama"));
                pkj.setPosisi(rs.getString("posisi"));
                pkj.setGender_pkrj(rs.getString("gender_pkrj"));
                pkj.setTtl_pkrj(rs.getString("ttl_pkrj"));
                pkj.setAlamat_tinggal(rs.getString("alamat_tinggal"));
                pkj.setGaji_pokok(rs.getInt("gaji_pokok"));

                if (rs.getInt("unit_kerja") == 0) {
                    pkj.setUnit(null);
                } else {
                    Unit unt = execUnt.getUnit(rs.getInt("unit_kerja"));            
                    pkj.setUnit(unt);
                }         
                
                listpkj.add(pkj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteRekamMedis.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listpkj ;
    }
}
