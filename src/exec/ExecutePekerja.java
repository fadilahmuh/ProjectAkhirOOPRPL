package exec;

import com.Pekerja;
import com.Unit;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
