package exec;

import com.Unit;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteUnit {
    
    public Unit getUnit(int kode){
        Unit unt = new Unit();
        String query = "select * from unit where kd_unit=" + kode;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            rs.next();                                
            unt.setKd_unit(rs.getInt("kd_unit"));
            unt.setNama_unit(rs.getString("nama_unit"));
            unt.setTarifdasar(rs.getInt("tarifdasar"));
            unt.setDenah_unit(rs.getString("denah_unit"));                                
        } catch (SQLException ex) {
           Logger.getLogger(ExecuteUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        
        return unt ;
    } 
}
