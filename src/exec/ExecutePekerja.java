package exec;

import com.Pekerja;
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
    public List<Pekerja> getPekerja(){
        List<Pekerja> listPekerja = new ArrayList<>();
        String query = "select * from pekerja";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pekerja pk = new Pekerja();
                pk.setId_pekerja(rs.getInt("id_pekerja"));
                pk.setPassword(rs.getString("password"));
                pk.setNama(rs.getString("nama"));
                pk.setPosisi(rs.getString("posisi"));
                pk.setGender_pkrj(rs.getString("gender_pkrj"));
                pk.setTtl_pkrj(rs.getString("ttl_pkrj"));
                pk.setAlamat_tinggal(rs.getString("alamat_tinggal"));
                pk.setGender_pkrj(rs.getString("gender_pkrj"));
                pk.setGaji_pokok(rs.getInt("gaji_pokok"));
                pk.setFunit_kerja(rs.getInt("unit_kerja"));
                listPekerja.add(pk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePekerja.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listPekerja;
    }
}
