package view;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import model.Kursus;

public class FrompendaftaranAwal extends javax.swing.JFrame {
    
    private JTextArea areaHasilPendaftaran;

    public FrompendaftaranAwal() {
         initComponents();
           setLocationRelativeTo(null);

        getContentPane().setBackground(
            new java.awt.Color(251, 251, 240)
        );

        // Membuat area teks di dalam JScrollPane.
        areaHasilPendaftaran = new JTextArea();
        areaHasilPendaftaran.setFont(
            new java.awt.Font(
                "Monospaced",
                java.awt.Font.PLAIN,
                12
            )
        );
        areaHasilPendaftaran.setEditable(false);
        areaHasilPendaftaran.setLineWrap(true);
        areaHasilPendaftaran.setWrapStyleWord(true);

        txtHasilPendaftaran.setViewportView(
            areaHasilPendaftaran
        );

        // Field yang nilainya dihitung otomatis.
        txtKodeKursus.setText("");
        txtBiayaKursus.setEditable(false);
        txtDiskon.setEditable(false);

        // Perbarui biaya saat nama kursus atau level berubah.
        cmbNamaKursus.addActionListener(
            e -> aturBiayaDanDiskon()
        );

        cmbLevelKursus.addActionListener(
            e -> aturBiayaDanDiskon()
        );

        aturBiayaDanDiskon();
    }

    // Format angka sesuai format Indonesia.
    private NumberFormat formatRupiah() {
        return NumberFormat.getNumberInstance(
            new Locale("id", "ID")
        );
    }

    // Menghitung biaya berdasarkan nama kursus dan level.
    private double ambilBiaya(
            String namaKursus,
            String level) {

        double biaya;

        switch (namaKursus) {
            case "Java":
                biaya = 100000;
                break;

            case "Data Science":
                biaya = 200000;
                break;

            case "UI/UX":
                biaya = 300000;
                break;

            default:
                throw new IllegalArgumentException(
                    "Nama kursus tidak valid."
                );
        }

        if ("Basic".equalsIgnoreCase(level)) {
            // Tidak ada tambahan biaya.
        } else if ("Intermediate".equalsIgnoreCase(level)) {
            biaya += 50000;
        } else if ("Advanced".equalsIgnoreCase(level)) {
            biaya += 100000;
        } else {
            throw new IllegalArgumentException(
                "Level kursus tidak valid."
            );
        }

        return biaya;
    }

    // Menentukan diskon berdasarkan level.
    private int ambilDiskon(String level) {

        if ("Basic".equalsIgnoreCase(level)) {
            return 0;
        }

        if ("Intermediate".equalsIgnoreCase(level)) {
            return 10;
        }

        if ("Advanced".equalsIgnoreCase(level)) {
            return 20;
        }

        throw new IllegalArgumentException(
            "Level kursus tidak valid."
        );
    }
    private void aturBiayaDanDiskon() {

        Object pilihanNama =
            cmbNamaKursus.getSelectedItem();

        Object pilihanLevel =
            cmbLevelKursus.getSelectedItem();

        if (pilihanNama == null || pilihanLevel == null) {
            txtBiayaKursus.setText("");
            txtDiskon.setText("");
            return;
        }

        String namaKursus = pilihanNama.toString();
        String level = pilihanLevel.toString();

        try {
            double biaya = ambilBiaya(
                namaKursus,
                level
            );

            int diskon = ambilDiskon(level);

            txtBiayaKursus.setText(
                "Rp " + formatRupiah().format(biaya)
            );

            txtDiskon.setText(
                String.valueOf(diskon)
            );

        } catch (IllegalArgumentException ex) {
            txtBiayaKursus.setText("");
            txtDiskon.setText("");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblKodeKursus = new javax.swing.JLabel();
        lblNamaKursus = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        txtKodeKursus = new javax.swing.JTextField();
        lblBiayaKursus = new javax.swing.JLabel();
        cmbNamaKursus = new javax.swing.JComboBox<>();
        cmbLevelKursus = new javax.swing.JComboBox<>();
        txtBiayaKursus = new javax.swing.JTextField();
        lblDiskon = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        btnProses = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtHasilPendaftaran = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("from pendaftaran kursus");

        lblKodeKursus.setText("kode kursus");

        lblNamaKursus.setText("nama kursus");

        lblLevel.setText("level");

        txtKodeKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeKursusActionPerformed(evt);
            }
        });

        lblBiayaKursus.setText("Biaya Kursus");

        cmbNamaKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Data Science", "UI/UX" }));

        cmbLevelKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basic", "Intermediate", "Advanced", "" }));
        cmbLevelKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLevelKursusActionPerformed(evt);
            }
        });

        lblDiskon.setText("Diskon %");

        txtDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiskonActionPerformed(evt);
            }
        });

        btnProses.setText("proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        btnReset.setText("reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHasilPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblKodeKursus)
                                .addComponent(lblNamaKursus)
                                .addComponent(lblLevel)
                                .addComponent(lblBiayaKursus)
                                .addComponent(lblDiskon))
                            .addGap(58, 58, 58)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtKodeKursus)
                                .addComponent(cmbNamaKursus, 0, 231, Short.MAX_VALUE)
                                .addComponent(cmbLevelKursus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBiayaKursus)
                                .addComponent(txtDiskon)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addComponent(btnProses)
                            .addGap(85, 85, 85)
                            .addComponent(btnReset))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKodeKursus)
                    .addComponent(txtKodeKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaKursus)
                    .addComponent(cmbNamaKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel)
                    .addComponent(cmbLevelKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBiayaKursus)
                    .addComponent(txtBiayaKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiskon)
                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnProses))
                .addGap(43, 43, 43)
                .addComponent(txtHasilPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodeKursusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeKursusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeKursusActionPerformed

    private void txtDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiskonActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       int pilihan = JOptionPane.showConfirmDialog(
            this,
            "Kosongkan seluruh input?",
            "Konfirmasi Reset",
            JOptionPane.YES_NO_OPTION
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            txtKodeKursus.setText("");
            cmbNamaKursus.setSelectedIndex(0);
            cmbLevelKursus.setSelectedIndex(0);

            areaHasilPendaftaran.setText("");

            aturBiayaDanDiskon();

            txtKodeKursus.requestFocus();
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
      String kode = txtKodeKursus.getText().trim();

        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Kode kursus wajib diisi!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );

            txtKodeKursus.requestFocus();
            return;
        }

        Object pilihanNama =
            cmbNamaKursus.getSelectedItem();

        Object pilihanLevel =
            cmbLevelKursus.getSelectedItem();

        if (pilihanNama == null || pilihanLevel == null) {
            JOptionPane.showMessageDialog(
                this,
                "Nama dan level kursus wajib dipilih!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String namaKursus = pilihanNama.toString();
        String level = pilihanLevel.toString();

        try {
            double biaya = ambilBiaya(
                namaKursus,
                level
            );

            int diskon = ambilDiskon(level);

            // Membuat objek model Kursus.
            Kursus kursus = new Kursus(
                kode,
                namaKursus,
                level,
                biaya
            );

            // Menggunakan perhitungan dari model Kursus.
            double totalBayar =
                kursus.hitungBiayaSetelahDiskon(diskon);

            double potongan = biaya - totalBayar;

            NumberFormat format = formatRupiah();

            areaHasilPendaftaran.setText(
                "====================================\n"
                + "       HASIL PENDAFTARAN KURSUS\n"
                + "====================================\n\n"
                + "Kode Kursus  : "
                + kursus.getKode() + "\n"
                + "Nama Kursus  : "
                + kursus.getNama() + "\n"
                + "Level        : "
                + kursus.getLevel() + "\n"
                + "Biaya Kursus : Rp "
                + format.format(kursus.getBiaya()) + "\n"
                + "Diskon       : "
                + diskon + "%\n"
                + "Potongan     : Rp "
                + format.format(potongan) + "\n"
                + "------------------------------------\n"
                + "Total Bayar  : Rp "
                + format.format(totalBayar) + "\n"
                + "===================================="
            );

            areaHasilPendaftaran.setCaretPosition(0);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Data tidak valid",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void cmbLevelKursusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLevelKursusActionPerformed

    }//GEN-LAST:event_cmbLevelKursusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrompendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrompendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrompendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrompendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrompendaftaranAwal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbLevelKursus;
    private javax.swing.JComboBox<String> cmbNamaKursus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBiayaKursus;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblKodeKursus;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblNamaKursus;
    private javax.swing.JTextField txtBiayaKursus;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JScrollPane txtHasilPendaftaran;
    private javax.swing.JTextField txtKodeKursus;
    // End of variables declaration//GEN-END:variables
}
