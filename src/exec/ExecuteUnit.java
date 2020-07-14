package exec;

import com.Unit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteUnit {
    public List<Unit> getUnit(){
        List<Unit> listUnit = new ArrayList<>();
        String query = "select * from unit";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Unit un = new Unit();
                un.setKd_unit(rs.getInt("kd_unit"));
                un.setNama_unit(rs.getString("nama_unit"));
                un.setTarifdasar(rs.getInt("tarifdasar"));
                un.setDenah_unit(rs.getString("denah_unit"));
                listUnit.add(un);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listUnit;
    }
}
