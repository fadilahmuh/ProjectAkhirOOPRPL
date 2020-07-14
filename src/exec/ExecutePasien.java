package exec;

import com.Pasien;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutePasien {
    public List<Pasien> getPasien(){
        List<Pasien> listPasien = new ArrayList<>();
        String query = "select * from pasien";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pasien ps = new Pasien();
                ps.setId_pasien(rs.getInt("id_pasien"));
                ps.setNama_pasien(rs.getString("nama_pasien"));
                ps.setGender_pasien(rs.getString("gender_pasien"));
                ps.setTtl_pasien(rs.getString("ttl_pasien"));
                ps.setNik_pasien(rs.getString("nik_pasien"));
                ps.setAsuransi(rs.getString("asuransi"));
                listPasien.add(ps);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listPasien;
    }
}
