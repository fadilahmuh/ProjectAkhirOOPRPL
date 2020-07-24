/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.Obat;
import com.Pasien;
import com.Pekerja;
import com.RekamMedis;
import com.Tindakan;
import exec.ExecuteObat;
import exec.ExecutePasien;
import exec.ExecutePekerja;
import exec.ExecuteRekamMedis;
import exec.ExecuteTindakan;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fadil
 */
public class main_form extends javax.swing.JFrame {
    private static Pekerja dokter,prt1,prt2;
    private static Pasien psn;
    private static String sf,tanggal;
    private static RekamMedis selRkm,newRkm;
    private static ExecutePekerja execPkj;
    private static ExecutePasien execPsn;
    private static ExecuteObat execObt;
    private static ExecuteTindakan execTnd;
    private static ExecuteRekamMedis execRkm;
    private static String[][] dataRekam;
    
    
    

    /**
     * Creates new form form_periksa
     * @param dkt
     */
    public main_form(Pekerja dkt) {
        initComponents();
        login2.show();
        dokter = dkt;
        addition.hide();
        DateTimeFormatter fd = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tanggal = fd.format(LocalDateTime.now());

        set_dok();
        set_cbbobat();
        set_cbbtndk();
        set_cbbrekpas();
        set_cbbperaw1();
    }

    private main_form() {
        initComponents();
        set_cbbrekpas();
        addition.hide();
    }
    
    private void set_dok() {
       String[] nama = dokter.getNama().split(" ");
       nama_dokter.setText("Dokter  : Dr." + nama[0] + " " + nama[1]);
       
       nama_klinik.setText(dokter.getUnit().getNama_unit());
       
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String shift = null;
        if(timeOfDay >= 0 && timeOfDay < 11){
            shift = "Shift   : Pagi";
        }else if(timeOfDay >= 11 && timeOfDay < 15){
            lbl_shift.setText("Shift   : Siang");        
            shift = "Shift   : Siang";
        }else if(timeOfDay >= 15 && timeOfDay < 18){
            shift = "Shift   : Sore";
        }else if(timeOfDay >= 18 && timeOfDay < 24){
            shift = "Shift   : Malam";            
        }
        lbl_shift.setText(shift);       
    }
    
    private void set_cbbobat() {
        execObt = new ExecuteObat();
        
        List<Obat> listObt = execObt.getObat();
        String[] listobt2 = new String[listObt.size()];
        for (int i = 0; i < listobt2.length; i++) {
            listobt2[i] = listObt.get(i).getNama_obat() + ", " + listObt.get(i).getId_obat();
        }     
        DefaultComboBoxModel model = new DefaultComboBoxModel(listobt2);
        cbb_obat.setModel(model);  
        jComboBox2.setModel(model);
    }
    private void set_cbbtndk() {
        execTnd = new ExecuteTindakan();
        
        List<Tindakan> listTnd = execTnd.getTindakan(dokter.getUnit().getKd_unit());
        String[] listtnd2 = new String[listTnd.size()];
        for (int i = 0; i < listtnd2.length; i++) {
            listtnd2[i] = listTnd.get(i).getNama_tindakan() + ", " + listTnd.get(i).getId_tindakan();
        }     
        DefaultComboBoxModel model = new DefaultComboBoxModel(listtnd2);
        cbb_tindakan.setModel(model);
        jComboBox1.setModel(model);
    }
    private void set_lblpasiem() {
        lbl_id.setText(String.valueOf(psn.getId_pasien()));
        lbl_nama.setText(psn.getNama_pasien());
        if (psn.getGender_pasien().equals("L")) {
            lbl_gender.setText("Laki-laki");
        } else {
            lbl_gender.setText("Perempuan");
        }
        lbl_ttl.setText(psn.getTtl_pasien());
        lbl_asuransi.setText(psn.getAsuransi()); 
    }
    private void reset() {
        DefaultTableModel td = (DefaultTableModel) tabel_diagnosis.getModel();
        td.setRowCount(0);
        td.addRow(new Object[]{});
        
        DefaultTableModel tt = (DefaultTableModel) tabel_tindakan.getModel();
        tt.setRowCount(0);
        tt.addRow(new Object[]{});
        
        DefaultTableModel to = (DefaultTableModel) tabel_obat.getModel();
        to.setRowCount(0);
        to.addRow(new Object[]{});
    }
    private void set_cbbrekpas() {
        execRkm = new ExecuteRekamMedis();
        
        List<String> listNama = execRkm.getRekamPasien(dokter.getId_pekerja(),tanggal);      
        String[] listbnd2 = new String[listNama.size()+1];
        listbnd2[0] = "-";
        for (int i = 1; i < listbnd2.length; i++) {
            listbnd2[i] = listNama.get(i-1);
        }     
        DefaultComboBoxModel model = new DefaultComboBoxModel(listbnd2);
        cbb_pasien.setModel(model);
    }
    private void set_cbbperaw1() {
        execPkj = new ExecutePekerja();
        
        List<Pekerja> listPkj = execPkj.getPerawat(0);      
        String[] listpkj2 = new String[listPkj.size()+1];
        listpkj2[0] = "-";
        for (int i = 1; i < listpkj2.length; i++) {
            String[] nama = listPkj.get(i-1).getNama().split(" ");
            listpkj2[i] = String.valueOf(listPkj.get(i-1).getId_pekerja()) +", "+ nama[0];
        }     
        DefaultComboBoxModel model = new DefaultComboBoxModel(listpkj2);
        cbb_pera1.setModel(model);
    }
    private void set_cbbperaw2(int id) {
        execPkj = new ExecutePekerja();
        
        List<Pekerja> listPkj = execPkj.getPerawat(id);      
        String[] listpkj2 = new String[listPkj.size()+1];
        listpkj2[0] = "-";
        for (int i = 1; i < listpkj2.length; i++) {
            String[] nama = listPkj.get(i-1).getNama().split(" ");
            listpkj2[i] = String.valueOf(listPkj.get(i-1).getId_pekerja()) +", "+ nama[0];
        }     
        DefaultComboBoxModel model = new DefaultComboBoxModel(listpkj2);
        cbb_pera2.setModel(model);
    }
    private void setDataRekam(int id){

        execRkm = new ExecuteRekamMedis();
        List<RekamMedis> listRkm = execRkm.getRekam2(id,dokter.getId_pekerja(),tanggal);
        DefaultTableModel model = execRkm.Rekamtoobjek(listRkm);
 
        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        nama_klinik = new javax.swing.JLabel();
        nama_rs = new javax.swing.JLabel();
        nama_dokter = new javax.swing.JLabel();
        lbl_shift = new javax.swing.JLabel();
        lbl_perawat = new javax.swing.JLabel();
        lbl_perawat1 = new javax.swing.JLabel();
        lbl_perawat2 = new javax.swing.JLabel();
        bg_header = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        login2 = new javax.swing.JPanel();
        field_idpasien = new javax.swing.JTextField();
        btn_next = new javax.swing.JButton();
        cbb_pera2 = new javax.swing.JComboBox();
        cbb_pera1 = new javax.swing.JComboBox();
        btn_rekam = new javax.swing.JLabel();
        bg_body1 = new javax.swing.JLabel();
        pemeriksaan = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        nama_klinik1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_diagnosis = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        id = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        lbl_nama = new javax.swing.JLabel();
        ttl = new javax.swing.JLabel();
        lbl_ttl = new javax.swing.JLabel();
        gender = new javax.swing.JLabel();
        lbl_gender = new javax.swing.JLabel();
        asuransi = new javax.swing.JLabel();
        lbl_asuransi = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        nama_klinik2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_tindakan = new javax.swing.JTable();
        cbb_tindakan = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        nama_klinik3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        cbb_obat = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        btn_selesai1 = new javax.swing.JButton();
        btn_selesai = new javax.swing.JButton();
        background_periksa = new javax.swing.JLabel();
        addition = new javax.swing.JPanel();
        edit_rekam = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        cbb_pasien = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        field_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        field_jenis = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        field_desc = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        field_ket = new javax.swing.JTextArea();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nama_klinik.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        nama_klinik.setForeground(new java.awt.Color(112, 112, 112));
        nama_klinik.setText("Klinik . . . . . . . . . ");
        header.add(nama_klinik, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 680, -1));

        nama_rs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nama_rs.setForeground(new java.awt.Color(112, 112, 112));
        nama_rs.setText("Rumah Sakit Al-Boromeous");
        header.add(nama_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        nama_dokter.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        nama_dokter.setForeground(new java.awt.Color(112, 112, 112));
        nama_dokter.setText("Dokter  : Dr. Broto Asmoro uwu");
        header.add(nama_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, -1));

        lbl_shift.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_shift.setForeground(new java.awt.Color(112, 112, 112));
        lbl_shift.setText("Shift   : Pagi");
        header.add(lbl_shift, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 290, -1));

        lbl_perawat.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_perawat.setForeground(new java.awt.Color(112, 112, 112));
        lbl_perawat.setText("Perawat : ");
        header.add(lbl_perawat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 70, 100, -1));

        lbl_perawat1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_perawat1.setForeground(new java.awt.Color(112, 112, 112));
        lbl_perawat1.setText("1. -");
        header.add(lbl_perawat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 70, 210, -1));

        lbl_perawat2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_perawat2.setForeground(new java.awt.Color(112, 112, 112));
        lbl_perawat2.setText("2. -");
        header.add(lbl_perawat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 100, 210, -1));

        bg_header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/header.png"))); // NOI18N
        header.add(bg_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 160));

        getContentPane().add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 160));

        body.setLayout(new java.awt.CardLayout());

        login2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        field_idpasien.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        login2.add(field_idpasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 230, 30));

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 3 – 1.png"))); // NOI18N
        btn_next.setBorder(null);
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        login2.add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        cbb_pera2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        cbb_pera2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cbb_pera2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_pera2ActionPerformed(evt);
            }
        });
        login2.add(cbb_pera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 330, -1));

        cbb_pera1.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        cbb_pera1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_pera1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_pera1ActionPerformed(evt);
            }
        });
        login2.add(cbb_pera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 330, -1));

        btn_rekam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/open-book.png"))); // NOI18N
        btn_rekam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_rekam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_rekamMouseClicked(evt);
            }
        });
        login2.add(btn_rekam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 630, 50, 50));

        bg_body1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/body1.png"))); // NOI18N
        login2.add(bg_body1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 700));

        body.add(login2, "card2");

        pemeriksaan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jPanel1.setOpaque(false);

        nama_klinik1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        nama_klinik1.setForeground(new java.awt.Color(112, 112, 112));
        nama_klinik1.setText("Diagnosis");

        tabel_diagnosis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tabel_diagnosis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Deskripsi", "Keterangan"
            }
        ));
        tabel_diagnosis.setFillsViewportHeight(true);
        tabel_diagnosis.setOpaque(false);
        tabel_diagnosis.setRowHeight(25);
        jScrollPane1.setViewportView(tabel_diagnosis);
        if (tabel_diagnosis.getColumnModel().getColumnCount() > 0) {
            tabel_diagnosis.getColumnModel().getColumn(1).setMinWidth(375);
            tabel_diagnosis.getColumnModel().getColumn(1).setMaxWidth(375);
        }
        tabel_diagnosis.setBackground(new Color (0,0,0,0));
        ((DefaultTableCellRenderer)tabel_diagnosis.getDefaultRenderer(Object.class)).setBackground(new Color(254,254,254,1));
        //jTable1.setGridColor (Color.WHITE);
        //jTable1.setForeground (Color.WHITE);
        tabel_diagnosis.setShowGrid(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 7 – 1.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");
        jSeparator1.setAlignmentX(1.0F);
        jSeparator1.setAlignmentY(1.0F);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(179, 179, 179), 3));
        jSeparator1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jSeparator1.setMinimumSize(new java.awt.Dimension(50, 15));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 15));

        id.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        id.setForeground(new java.awt.Color(112, 112, 112));
        id.setText("ID Pasien     :");

        lbl_id.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_id.setForeground(new java.awt.Color(112, 112, 112));
        lbl_id.setText("136498718646164");

        nama.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        nama.setForeground(new java.awt.Color(112, 112, 112));
        nama.setText("Nama Pasien   :");

        lbl_nama.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_nama.setForeground(new java.awt.Color(112, 112, 112));
        lbl_nama.setText("Jailangkung");

        ttl.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        ttl.setForeground(new java.awt.Color(112, 112, 112));
        ttl.setText("TTL           :");

        lbl_ttl.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_ttl.setForeground(new java.awt.Color(112, 112, 112));
        lbl_ttl.setText("12/12/2012, Bandung");

        gender.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        gender.setForeground(new java.awt.Color(112, 112, 112));
        gender.setText("Jenis Kelamin :");

        lbl_gender.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_gender.setForeground(new java.awt.Color(112, 112, 112));
        lbl_gender.setText("Laki-Perempuan");

        asuransi.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        asuransi.setForeground(new java.awt.Color(112, 112, 112));
        asuransi.setText("Asuransi      :");

        lbl_asuransi.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lbl_asuransi.setForeground(new java.awt.Color(112, 112, 112));
        lbl_asuransi.setText("BPRSJ");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 8 – 1.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(437, 437, 437)
                        .addComponent(nama_klinik1)
                        .addGap(18, 426, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(119, 119, 119)))))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(asuransi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ttl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_ttl, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(lbl_gender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_asuransi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(105, 105, 105))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(nama_klinik1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id)
                            .addComponent(lbl_id))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nama)
                            .addComponent(lbl_nama))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ttl)
                            .addComponent(lbl_ttl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gender)
                            .addComponent(lbl_gender))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(asuransi)
                            .addComponent(lbl_asuransi))
                        .addGap(89, 89, 89)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new Color (0,0,0,0));
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        jTabbedPane1.addTab("Pemeriksaan", jPanel1);

        jPanel2.setOpaque(false);

        nama_klinik2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        nama_klinik2.setForeground(new java.awt.Color(112, 112, 112));
        nama_klinik2.setText("Daftar Tindakan");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 7 – 1.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tabel_tindakan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tabel_tindakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Deskripsi", "Keterangan"
            }
        ));
        tabel_tindakan.setFillsViewportHeight(true);
        tabel_tindakan.setOpaque(false);
        tabel_tindakan.setRowHeight(25);
        jScrollPane2.setViewportView(tabel_tindakan);
        if (tabel_tindakan.getColumnModel().getColumnCount() > 0) {
            tabel_tindakan.getColumnModel().getColumn(1).setMinWidth(375);
            tabel_tindakan.getColumnModel().getColumn(1).setMaxWidth(375);
        }
        tabel_tindakan.setBackground(new Color (0,0,0,0));
        ((DefaultTableCellRenderer)tabel_tindakan.getDefaultRenderer(Object.class)).setBackground(new Color(254,254,254,1));
        tabel_tindakan.setShowGrid(true);

        cbb_tindakan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbb_tindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_tindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tindakanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbb_tindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jButton3)
                .addGap(292, 292, 292))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(nama_klinik2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(nama_klinik2)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_tindakan))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jScrollPane2.setBackground(new Color (0,0,0,0));
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);

        jTabbedPane1.addTab("Tindakan", jPanel2);

        jPanel3.setOpaque(false);

        nama_klinik3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        nama_klinik3.setForeground(new java.awt.Color(112, 112, 112));
        nama_klinik3.setText("Daftar Obat");

        tabel_obat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tabel_obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Deskripsi", "Keterangan"
            }
        ));
        tabel_obat.setFillsViewportHeight(true);
        tabel_obat.setOpaque(false);
        tabel_obat.setRowHeight(25);
        jScrollPane3.setViewportView(tabel_obat);
        if (tabel_obat.getColumnModel().getColumnCount() > 0) {
            tabel_obat.getColumnModel().getColumn(1).setMinWidth(375);
            tabel_obat.getColumnModel().getColumn(1).setMaxWidth(375);
        }
        tabel_obat.setBackground(new Color (0,0,0,0));
        ((DefaultTableCellRenderer)tabel_obat.getDefaultRenderer(Object.class)).setBackground(new Color(254,254,254,1));
        tabel_obat.setShowGrid(true);

        cbb_obat.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbb_obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_obatActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 7 – 1.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cbb_obat, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jButton5)
                .addGap(292, 292, 292))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(nama_klinik3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(nama_klinik3)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_obat))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jScrollPane3.setBackground(new Color (0,0,0,0));
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);

        jTabbedPane1.addTab("Obat", jPanel3);

        pemeriksaan.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1380, 610));

        btn_selesai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 13 – 1.png"))); // NOI18N
        btn_selesai1.setBorder(null);
        btn_selesai1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_selesai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesai1ActionPerformed(evt);
            }
        });
        pemeriksaan.add(btn_selesai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, -1, 40));

        btn_selesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 12 – 1.png"))); // NOI18N
        btn_selesai.setBorder(null);
        btn_selesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });
        pemeriksaan.add(btn_selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 640, -1, -1));

        background_periksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/body2.png"))); // NOI18N
        background_periksa.setText("+");
        pemeriksaan.add(background_periksa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 690));

        body.add(pemeriksaan, "card3");

        getContentPane().add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1440, 690));

        addition.setLayout(new java.awt.CardLayout());

        edit_rekam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 13 – 1.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        edit_rekam.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 770, -1, -1));

        cbb_pasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_pasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_pasienActionPerformed(evt);
            }
        });
        edit_rekam.add(cbb_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 560, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        edit_rekam.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 1380, 210));

        field_id.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        field_id.setEnabled(false);
        edit_rekam.add(field_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 160, -1));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        jLabel2.setText("ID");
        edit_rekam.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, -1, 30));

        jComboBox1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        edit_rekam.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, 280, -1));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        jLabel3.setText("Deskripsi");
        edit_rekam.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, 30));

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        jLabel4.setText("Keterangan");
        edit_rekam.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, 30));

        field_jenis.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        field_jenis.setEnabled(false);
        edit_rekam.add(field_jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 160, -1));

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        jLabel5.setText("Tindakan");
        edit_rekam.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, -1, 30));

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        jLabel6.setText("Jenis");
        edit_rekam.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, -1, 30));

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        jLabel7.setText("Obat");
        edit_rekam.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 570, -1, 30));

        jComboBox2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        edit_rekam.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 570, 280, -1));

        field_desc.setColumns(20);
        field_desc.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        field_desc.setLineWrap(true);
        field_desc.setRows(3);
        jScrollPane7.setViewportView(field_desc);

        edit_rekam.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 270, 90));

        field_ket.setColumns(20);
        field_ket.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        field_ket.setLineWrap(true);
        field_ket.setRows(3);
        jScrollPane8.setViewportView(field_ket);

        edit_rekam.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, 270, 90));

        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 13 – 2.png"))); // NOI18N
        btn_update.setBorder(null);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        edit_rekam.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 750, -1, -1));

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Component 13 – 3.png"))); // NOI18N
        btn_delete.setBorder(null);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        edit_rekam.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 750, -1, -1));

        addition.add(edit_rekam, "card2");

        getContentPane().add(addition, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 850));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabel_diagnosis.getModel();
        model.addRow(new Object[]{} );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabel_tindakan.getModel();
        model.addRow(new Object[]{} );
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabel_obat.getModel();
        model.addRow(new Object[]{} );
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbb_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_obatActionPerformed
        int row = tabel_obat.getRowCount();
        
        tabel_obat.setValueAt(cbb_obat.getSelectedItem() , row - 1, 0);
    }//GEN-LAST:event_cbb_obatActionPerformed

    private void cbb_tindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tindakanActionPerformed
        int row = tabel_tindakan.getRowCount();
        
        tabel_tindakan.setValueAt(cbb_tindakan.getSelectedItem() , row - 1, 0);
    }//GEN-LAST:event_cbb_tindakanActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new rekam_medis(psn).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        execPsn = new ExecutePasien();

        psn = execPsn.getPasien(Integer.valueOf(field_idpasien.getText()));
        if (psn.getId_pasien()!= 0){
            addition.hide();
            login2.hide();
            pemeriksaan.show();
            set_lblpasiem();
            field_idpasien.setText("");
            jTabbedPane1.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Cek kembali ID Pasien !!");
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_selesai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesai1ActionPerformed
        login2.show();
        pemeriksaan.hide();
    }//GEN-LAST:event_btn_selesai1ActionPerformed

    private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
        List<RekamMedis> newRm = new ArrayList<>();        
        
        int row = tabel_diagnosis.getRowCount();        
        for (int i = 0; i < row; i++) {
            if (tabel_diagnosis.getValueAt( i, 0) == null) {
                break;
            }
            RekamMedis rm = new RekamMedis();
            rm.setDeskripsi(tabel_diagnosis.getValueAt( i, 0).toString());
            rm.setPasien(psn);
            rm.setId_rekam(0);
            rm.setJenis("Diagnosis");
            rm.setKeterangan(tabel_diagnosis.getValueAt( i, 1).toString());              
            rm.setPemeriksa(dokter);
            rm.setTanggal(tanggal); 
            rm.setStatus("Asli");
            newRm.add(rm);
        }
        
        row = tabel_tindakan.getRowCount();        
        for (int i = 0; i < row; i++) {
            if (tabel_tindakan.getValueAt( i, 0) == null) {
                break;
            }
            RekamMedis rm = new RekamMedis();
            String[] desc = tabel_tindakan.getValueAt( i, 0).toString().split(", ");
            rm.setDeskripsi(desc[1]);
            rm.setPasien(psn);
            rm.setId_rekam(0);
            rm.setJenis("Tindakan");
            rm.setKeterangan(tabel_tindakan.getValueAt( i, 1).toString());
            rm.setPemeriksa(dokter);
            rm.setTanggal(tanggal);            
            rm.setStatus("Asli");
            newRm.add(rm);   
        }
        row = tabel_obat.getRowCount();        
        for (int i = 0; i < row; i++) {
            if (tabel_obat.getValueAt( i, 0) == null) {
                break;
            }
            RekamMedis rm = new RekamMedis();
            String[] desc = tabel_obat.getValueAt( i, 0).toString().split(", ");
            rm.setDeskripsi(desc[1]);
            rm.setPasien(psn);
            rm.setId_rekam(0);
            rm.setJenis("Obat");
            rm.setKeterangan(tabel_obat.getValueAt( i, 1).toString());
            rm.setPemeriksa(dokter);
            rm.setTanggal(tanggal);            
            rm.setStatus("Asli");
            newRm.add(rm);   
        }

        
        execRkm = new ExecuteRekamMedis();
        
        int hasil = execRkm.insertRekam(newRm);
        if(hasil == 1){
                JOptionPane.showMessageDialog(rootPane, "Berhasil!! Terima Kasih telah melayani Pasien dengan Baik!");
                reset();
                login2.show();
                pemeriksaan.hide();
        }
        
        set_cbbrekpas();
    }//GEN-LAST:event_btn_selesaiActionPerformed

    private void btn_rekamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rekamMouseClicked
        addition.show();
        edit_rekam.show();
        header.hide();
        body.hide();
    }//GEN-LAST:event_btn_rekamMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        edit_rekam.hide();
        addition.hide();
        header.show();
        body.show();
        login2.show();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cbb_pasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_pasienActionPerformed
        if (!(cbb_pasien.getSelectedItem().equals("-"))) {
            String[] pilih = cbb_pasien.getSelectedItem().toString().split(", ");
            int id = Integer.valueOf(pilih[0]);
            setDataRekam(id);
        } else {
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
    },
    new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
    }
));
        }
    }//GEN-LAST:event_cbb_pasienActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        execRkm = new ExecuteRekamMedis();
        selRkm = execRkm.getItemRekam(Integer.valueOf(jTable1.getValueAt(row, 0).toString()));
        System.out.println(selRkm.toString());
        field_id.setText(jTable1.getValueAt(row, 0).toString());
        field_ket.setText(jTable1.getValueAt(row, 3).toString());
        field_jenis.setText(jTable1.getValueAt(row, 1).toString());
        if (field_jenis.getText().equals("Diagnosis")) {
            field_desc.setEnabled(true);
            jComboBox1.setEnabled(false);
            jComboBox2.setEnabled(false);
            field_desc.setText(jTable1.getValueAt(row, 2).toString());
        } else if (field_jenis.getText().equals("Obat")) {
            field_desc.setEnabled(false);
            field_ket.setEnabled(true);
            jComboBox1.setEnabled(false);
            jComboBox2.setEnabled(true);
            field_desc.setText("");
            jComboBox2.setSelectedItem(jTable1.getValueAt(row, 2).toString());
        } else if (field_jenis.getText().equals("Tindakan")) {
            field_desc.setEnabled(false);
            field_ket.setEnabled(true);
            jComboBox1.setEnabled(true);
            field_desc.setText("");
            jComboBox2.setEnabled(false);
            jComboBox1.setSelectedItem(jTable1.getValueAt(row, 2).toString());
        }
        
        if (jTable1.getValueAt(row, 4).toString().equals("Dihapus")) {
            btn_update.setEnabled(false);
        } else {
            btn_update.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cbb_pera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_pera1ActionPerformed
        if (cbb_pera1.getSelectedIndex() == 0) {
            prt1 = new Pekerja();
            lbl_perawat1.setText("-");
        } else {
            String[] data = cbb_pera1.getSelectedItem().toString().split(", ");
            int id = Integer.valueOf(data[0]);
            prt1 = execPkj.getPekerja(id);
            String[] sprt1 = prt1.getNama().split(" ");
            lbl_perawat1.setText("1. " + sprt1[0] + " " + sprt1[1]);
            set_cbbperaw2(id);
        }
        prt2 = new Pekerja();
        lbl_perawat2.setText("-");
    }//GEN-LAST:event_cbb_pera1ActionPerformed

    private void cbb_pera2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_pera2ActionPerformed
       if (cbb_pera2.getSelectedIndex() == 0) {
            prt2 = new Pekerja();
            lbl_perawat2.setText("-");
        } else {
            String[] data = cbb_pera2.getSelectedItem().toString().split(", ");
            int id = Integer.valueOf(data[0]);
            prt2 = execPkj.getPekerja(id);
            String[] sprt1 = prt2.getNama().split(" ");
            lbl_perawat2.setText("1. " + sprt1[0] + " " + sprt1[1]);
        }
    }//GEN-LAST:event_cbb_pera2ActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        newRkm = selRkm;
        newRkm.setKeterangan(field_ket.getText());
        newRkm.setStatus("Ditambahkan");
        
        if (field_jenis.getText().equals("Diagnosis")) {
            newRkm.setDeskripsi(field_desc.getText());            
        } else if (field_jenis.getText().equals("Tindakan")) {
            String[] pil = cbb_tindakan.getSelectedItem().toString().split(", ");
            newRkm.setDeskripsi(pil[1]);
        } else if (field_jenis.getText().equals("Obat")) {
            String[] pil = cbb_obat.getSelectedItem().toString().split(", ");
            newRkm.setDeskripsi(pil[1]);
        }
        
        int hasil = execRkm.insertItemRekam(newRkm);
        if (hasil == 1 ) {
            selRkm.setStatus("Diubah");
            int hasil2 = execRkm.updateItemRekam(selRkm);
            if (hasil2 == 1) {
                JOptionPane.showMessageDialog(rootPane, "Edit data berhasil!"); 
                setDataRekam(newRkm.getPasien().getId_pasien());
            }                         
        } else  {
            JOptionPane.showMessageDialog(rootPane, "Edit data gagal!");   
        } 
            
        
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        selRkm.setStatus("Dihapus");
            int hasil2 = execRkm.updateItemRekam(selRkm);
            if (hasil2 == 1) {
                JOptionPane.showMessageDialog(rootPane, "Hapus data berhasil!"); 
                setDataRekam(selRkm.getPasien().getId_pasien());
            }                         
    }//GEN-LAST:event_btn_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addition;
    private javax.swing.JLabel asuransi;
    private javax.swing.JLabel background_periksa;
    private javax.swing.JLabel bg_body1;
    private javax.swing.JLabel bg_header;
    private javax.swing.JPanel body;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_next;
    private javax.swing.JLabel btn_rekam;
    private javax.swing.JButton btn_selesai;
    private javax.swing.JButton btn_selesai1;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox cbb_obat;
    private javax.swing.JComboBox cbb_pasien;
    private javax.swing.JComboBox cbb_pera1;
    private javax.swing.JComboBox cbb_pera2;
    private javax.swing.JComboBox cbb_tindakan;
    private javax.swing.JPanel edit_rekam;
    private javax.swing.JTextArea field_desc;
    private javax.swing.JTextField field_id;
    private javax.swing.JTextField field_idpasien;
    private javax.swing.JTextField field_jenis;
    private javax.swing.JTextArea field_ket;
    private javax.swing.JLabel gender;
    private javax.swing.JPanel header;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_asuransi;
    private javax.swing.JLabel lbl_gender;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_nama;
    private javax.swing.JLabel lbl_perawat;
    private javax.swing.JLabel lbl_perawat1;
    private javax.swing.JLabel lbl_perawat2;
    private javax.swing.JLabel lbl_shift;
    private javax.swing.JLabel lbl_ttl;
    private javax.swing.JPanel login2;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel nama_dokter;
    private javax.swing.JLabel nama_klinik;
    private javax.swing.JLabel nama_klinik1;
    private javax.swing.JLabel nama_klinik2;
    private javax.swing.JLabel nama_klinik3;
    private javax.swing.JLabel nama_rs;
    private javax.swing.JPanel pemeriksaan;
    private javax.swing.JTable tabel_diagnosis;
    private javax.swing.JTable tabel_obat;
    private javax.swing.JTable tabel_tindakan;
    private javax.swing.JLabel ttl;
    // End of variables declaration//GEN-END:variables
}
