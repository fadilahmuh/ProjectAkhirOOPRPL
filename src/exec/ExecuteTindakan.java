package exec;

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

public class ExecuteTindakan {
    public List<Tindakan> getTindakan(int unit){
        List<Tindakan> listTindakan = new ArrayList<>();
        String query = "select * from tindakan where unit_pengguna = " + unit + " order by nama_tindakan ASC";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Tindakan tk = new Tindakan();
                tk.setId_tindakan(rs.getString("id_tindakan"));
                tk.setFunit_pengguna(rs.getInt("unit_pengguna"));
                tk.setNama_tindakan(rs.getString("nama_tindakan"));
                tk.setHarga_tindakan(rs.getInt("harga_tindakan"));
                listTindakan.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteTindakan.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listTindakan;
    }
    public Tindakan getItemTindakan(String id){
        Tindakan tk = new Tindakan();
        String query = "select * from tindakan where id_tindakan = '" + id + "'" ;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            rs.next();
            tk.setId_tindakan(rs.getString("id_tindakan"));
            tk.setFunit_pengguna(rs.getInt("unit_pengguna"));
            tk.setNama_tindakan(rs.getString("nama_tindakan"));
            tk.setHarga_tindakan(rs.getInt("harga_tindakan"));
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteTindakan.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return tk;
    }
}
