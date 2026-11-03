
package view;

import dao.PesertaDAO;
import model.Peserta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormPeserta extends JFrame {

    private PesertaDAO dao = new PesertaDAO();

    private int idTerpilih = -1;

    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;
    private JTextField txtCari;

    private JComboBox<String> cmbJenisKelamin;

    private JTable tblPeserta;
    private DefaultTableModel modelTabel;

    private JButton btnSimpan;
    private JButton btnUbah;
    private JButton btnHapus;
    private JButton btnReset;
    private JButton btnCari;
    private JButton btnCetakSemua;

    public FormPeserta() {

        setTitle("Master Peserta - SIKURSUS Desktop");
        setSize(1050, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buatTampilan();
        tampilData();
    }

    // MEMBUAT TAMPILAN FORM
    private void buatTampilan() {

        JPanel utama = new JPanel(new BorderLayout(10, 10));
        utama.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        utama.setBackground(new Color(235, 245, 250));

        // HEADER
        JLabel lblJudul = new JLabel(
                "MASTER DATA PESERTA",
                SwingConstants.CENTER
        );

        lblJudul.setFont(new Font("Arial", Font.BOLD, 22));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setOpaque(true);
        lblJudul.setBackground(new Color(21, 87, 160));
        lblJudul.setPreferredSize(new Dimension(100, 65));

        utama.add(lblJudul, BorderLayout.NORTH);

        // PANEL TENGAH
        JPanel tengah = new JPanel(new BorderLayout(10, 10));
        tengah.setBackground(new Color(235, 245, 250));

        tengah.add(panelForm(), BorderLayout.NORTH);
        tengah.add(panelData(), BorderLayout.CENTER);

        utama.add(tengah, BorderLayout.CENTER);

        add(utama);
    }

    // PANEL FORM PESERTA
    private JPanel panelForm() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(BorderFactory.createTitledBorder("Form Peserta"));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(7, 10, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNim = new JTextField(30);
        txtNama = new JTextField(30);
        txtProdi = new JTextField(30);
        txtNoHp = new JTextField(30);
        txtEmail = new JTextField(30);

        cmbJenisKelamin = new JComboBox<>(
                new String[]{"L", "P"}
        );

        tambahBaris(panel, gbc, 0, "NIM", txtNim);
        tambahBaris(panel, gbc, 1, "Nama Peserta", txtNama);
        tambahBaris(panel, gbc, 2, "Jenis Kelamin", cmbJenisKelamin);
        tambahBaris(panel, gbc, 3, "Program Studi", txtProdi);
        tambahBaris(panel, gbc, 4, "Nomor HP", txtNoHp);
        tambahBaris(panel, gbc, 5, "Email", txtEmail);

        // TOMBOL
        JPanel panelTombol = new JPanel();

        panelTombol.setBackground(Color.WHITE);

        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");
        btnCetakSemua = new JButton("Cetak Semua");

        btnSimpan.setBackground(new Color(21, 130, 190));
        btnUbah.setBackground(new Color(21, 130, 190));
        btnHapus.setBackground(new Color(190, 60, 60));
        btnReset.setBackground(new Color(100, 130, 150));
        btnCetakSemua.setBackground(new Color(20, 160, 120));

        btnSimpan.setForeground(Color.WHITE);
        btnUbah.setForeground(Color.WHITE);
        btnHapus.setForeground(Color.WHITE);
        btnReset.setForeground(Color.WHITE);
        btnCetakSemua.setForeground(Color.WHITE);

        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnReset);
        panelTombol.add(btnCetakSemua);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(panelTombol, gbc);

        // EVENT TOMBOL
        btnSimpan.addActionListener(e -> simpanData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnReset.addActionListener(e -> resetForm());
        btnCetakSemua.addActionListener(e -> cetakData());

        return panel;
    }

    // MENAMBAHKAN LABEL DAN INPUT
    private void tambahBaris(
            JPanel panel,
            GridBagConstraints gbc,
            int baris,
            String label,
            JComponent komponen) {

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = baris;
        gbc.weightx = 0.2;

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(komponen, gbc);
    }

    // PANEL DATA PESERTA
    private JPanel panelData() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setBorder(BorderFactory.createTitledBorder("Data Peserta"));
        panel.setBackground(Color.WHITE);

        // PENCARIAN
        JPanel panelCari = new JPanel();

        panelCari.setBackground(Color.WHITE);

        txtCari = new JTextField(25);
        btnCari = new JButton("Cari");

        panelCari.add(new JLabel("Pencarian"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        panel.add(panelCari, BorderLayout.NORTH);

        btnCari.addActionListener(e -> cariData());

        txtCari.addActionListener(e -> cariData());

        // TABEL
        String[] kolom = {
            "ID",
            "NIM",
            "Nama",
            "JK",
            "Prodi",
            "No. HP",
            "Email"
        };

        modelTabel = new DefaultTableModel(kolom, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblPeserta = new JTable(modelTabel);

        tblPeserta.setRowHeight(25);
        tblPeserta.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scroll = new JScrollPane(tblPeserta);

        panel.add(scroll, BorderLayout.CENTER);

        // MEMILIH DATA DARI TABEL
        tblPeserta.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int baris = tblPeserta.getSelectedRow();

                if (baris >= 0) {

                    idTerpilih = Integer.parseInt(
                            modelTabel.getValueAt(baris, 0).toString()
                    );

                    txtNim.setText(
                            modelTabel.getValueAt(baris, 1).toString()
                    );

                    txtNama.setText(
                            modelTabel.getValueAt(baris, 2).toString()
                    );

                    cmbJenisKelamin.setSelectedItem(
                            modelTabel.getValueAt(baris, 3).toString()
                    );

                    txtProdi.setText(
                            modelTabel.getValueAt(baris, 4).toString()
                    );

                    txtNoHp.setText(
                            modelTabel.getValueAt(baris, 5).toString()
                    );

                    txtEmail.setText(
                            modelTabel.getValueAt(baris, 6).toString()
                    );
                }
            }
        });

        return panel;
    }

    // READ: MENAMPILKAN DATA
    private void tampilData() {

        modelTabel.setRowCount(0);

        try {

            List<Object[]> daftar = dao.getAllRows();

            for (Object[] data : daftar) {
                modelTabel.addRow(data);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menampilkan data: " + e.getMessage()
            );
        }
    }

    // CREATE: SIMPAN DATA
    private void simpanData() {

        try {

            if (!validasiInput()) {
                return;
            }

            Peserta peserta = ambilDataForm();

            String jk = cmbJenisKelamin.getSelectedItem().toString();
            String email = txtEmail.getText().trim();

            boolean berhasil = dao.insert(peserta, jk, email);

            if (berhasil) {

                JOptionPane.showMessageDialog(
                        this,
                        "Data peserta berhasil disimpan!"
                );

                tampilData();
                resetForm();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menyimpan data: " + e.getMessage()
            );
        }
    }

    // UPDATE: UBAH DATA
    private void ubahData() {

        if (idTerpilih == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data peserta dari tabel terlebih dahulu!"
            );

            return;
        }

        try {

            if (!validasiInput()) {
                return;
            }

            Peserta peserta = ambilDataForm();

            String jk = cmbJenisKelamin.getSelectedItem().toString();
            String email = txtEmail.getText().trim();

            boolean berhasil = dao.update(
                    idTerpilih,
                    peserta,
                    jk,
                    email
            );

            if (berhasil) {

                JOptionPane.showMessageDialog(
                        this,
                        "Data peserta berhasil diubah!"
                );

                tampilData();
                resetForm();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal mengubah data: " + e.getMessage()
            );
        }
    }

    // DELETE: HAPUS DATA
    private void hapusData() {

        if (idTerpilih == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data yang ingin dihapus!"
            );

            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah kamu yakin ingin menghapus data ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {

            try {

                boolean berhasil = dao.delete(idTerpilih);

                if (berhasil) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Data berhasil dihapus!"
                    );

                    tampilData();
                    resetForm();
                }

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menghapus data: " + e.getMessage()
                );
            }
        }
    }

    // SEARCH: CARI DATA
    private void cariData() {

        String keyword = txtCari.getText()
                .trim().toLowerCase();

        modelTabel.setRowCount(0);

        try {

            List<Object[]> daftar = dao.getAllRows();

            for (Object[] data : daftar) {

                String nim = String.valueOf(data[1]).toLowerCase();
                String nama = String.valueOf(data[2]).toLowerCase();

                if (nim.contains(keyword)
                        || nama.contains(keyword)) {

                    modelTabel.addRow(data);
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pencarian gagal: " + e.getMessage()
            );
        }
    }

    // RESET FORM
    private void resetForm() {

        txtNim.setText("");
        txtNama.setText("");
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtCari.setText("");

        cmbJenisKelamin.setSelectedIndex(0);

        idTerpilih = -1;

        tblPeserta.clearSelection();

        tampilData();
    }

    // VALIDASI INPUT
    private boolean validasiInput() {

        if (txtNim.getText().trim().isEmpty()
                || txtNama.getText().trim().isEmpty()
                || txtProdi.getText().trim().isEmpty()
                || txtNoHp.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Semua kolom harus diisi!"
            );

            return false;
        }

        return true;
    }

    // MENGAMBIL DATA DARI FORM
    private Peserta ambilDataForm() {

        return new Peserta(
                0,
                txtNama.getText().trim(),
                txtNoHp.getText().trim(),
                txtNim.getText().trim(),
                txtProdi.getText().trim()
        );
    }

    // CETAK DATA
    private void cetakData() {

        try {

            boolean berhasil = tblPeserta.print();

            if (berhasil) {

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil dicetak!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal mencetak data: " + e.getMessage()
            );
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new FormPeserta().setVisible(true);

        });
    }
}
