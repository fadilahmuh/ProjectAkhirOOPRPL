package exec;

import com.Tindakan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteTindakan {
    public List<Tindakan> getTindakan(){
        List<Tindakan> listTindakan = new ArrayList<>();
        String query = "select * from tindakan";
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
}
