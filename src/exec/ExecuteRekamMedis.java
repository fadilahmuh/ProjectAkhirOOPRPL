package exec;

import com.RekamMedis;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteRekamMedis {
    public List<RekamMedis> getRekam(){
        List<RekamMedis> listRekam = new ArrayList<>();
        String query = "select * from rekam_medis";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                RekamMedis rm = new RekamMedis();
                rm.setId_rekam(rs.getInt("id_rekam"));
                rm.setId_pasien(rs.get("nama"));
                sp.setAlamat(rs.getString("alamat"));
                sp.setEmail(rs.getString("email"));
                sp.setNo_hp(rs.getString("no_hp"));
                listSupplier.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listSupplier;
    }
    
    public int insertsupplier(Supplier sp){
        int hasil = 0;
        String query = "Insert into supplier(id_supplier, nama, alamat, email, no_hp)"
                + "value("+ sp.getId_supplier()+",'"+ 
                sp.getNama()+"','"+ sp.getAlamat()+"','"+sp.getEmail()+"','"+ sp.getNo_hp()+"')";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
}
