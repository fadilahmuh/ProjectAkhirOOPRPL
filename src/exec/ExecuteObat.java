package exec;

import com.Obat;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteObat {
    public List<Obat> getObat(){
        List<Obat> listObat = new ArrayList<>();
        String query = "select * from obat ORDER BY nama_obat";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Obat ob = new Obat();
                ob.setId_obat(rs.getString("id_obat"));
                ob.setNama_obat(rs.getString("nama_obat"));
                ob.setHarga_obat(rs.getInt("harga_obat"));
                listObat.add(ob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteObat.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listObat;
    }
}
