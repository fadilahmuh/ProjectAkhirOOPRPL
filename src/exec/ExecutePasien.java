package exec;

import com.Pasien;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutePasien {
    public Pasien getPasien(int id){
        Pasien ps = new Pasien();
        String query = "select * from pasien where id_pasien=" + id;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                ps.setId_pasien(rs.getInt("id_pasien"));
                ps.setNama_pasien(rs.getString("nama_pasien"));
                ps.setGender_pasien(rs.getString("gender_pasien"));
                ps.setTtl_pasien(rs.getString("ttl_pasien"));
                ps.setNik_pasien(rs.getString("nik_pasien"));
                ps.setAsuransi(rs.getString("asuransi"));
                ps.setNo_tlp(rs.getString("no_telp"));
                ps.setAlamat(rs.getString("alamat"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return ps;
    }
    
}
