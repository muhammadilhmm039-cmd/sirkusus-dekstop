package report;

import config.Database;
import java.awt.Component;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public final class ReportService {

    static {
        // Hindari error "Font ... is not available to the JVM"
        try {
            DefaultJasperReportsContext.getInstance().setProperty(
                "net.sf.jasperreports.awt.ignore.missing.font",
                "true"
            );
        } catch (Exception ignored) {
            // abaikan, tidak fatal
        }
    }

    private ReportService() {
    }

    // ================= LAPORAN PESERTA =================

    public static void showPeserta(Component parent) {

        show(
            parent,
            "laporan_peserta",
            new HashMap<String, Object>()
        );
    }

    // ================= LAPORAN PENDAFTARAN =================

    public static void showPendaftaranSemua(
            Component parent) {

        show(
            parent,
            "laporan_pendaftaran_semua",
            new HashMap<String, Object>()
        );
    }

    public static void showPendaftaranById(
            Component parent, int idPendaftaran) {

        Map<String, Object> p =
                new HashMap<>();

        p.put("P_ID_PENDAFTARAN", idPendaftaran);

        show(
            parent,
            "laporan_pendaftaran",
            p
        );
    }

    // ================= LAPORAN PEMBAYARAN =================

    public static void showPembayaranSemua(
            Component parent) {

        show(
            parent,
            "laporan_pembayaran_semua",
            new HashMap<String, Object>()
        );
    }

    public static void showPembayaranPeriode(
            Component parent, Date awal, Date akhir) {

        Map<String, Object> p =
                new HashMap<>();

        p.put("P_TANGGAL_AWAL", awal);
        p.put("P_TANGGAL_AKHIR", akhir);

        show(
            parent,
            "laporan_pembayaran",
            p
        );
    }

    // ================= PROSES UTAMA =================

    private static void show(
            Component parent,
            String baseName,
            Map<String, Object> params) {

        try (Connection conn = Database.getConnection()) {

            JasperReport report = loadReport(baseName);

            JasperPrint print =
                    JasperFillManager.fillReport(
                        report,
                        params,
                        conn
                    );

            if (print.getPages().isEmpty()) {

                JOptionPane.showMessageDialog(
                    parent,
                    "Report tidak memiliki data "
                    + "untuk kriteria yang dipilih."
                );

                return;
            }

            // false = menutup viewer TIDAK menutup aplikasi
            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                parent,
                "Gagal membuka laporan "
                + baseName + ":\n"
                + pesanError(ex),
                "JasperReports",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= LOAD REPORT =================
    // 1) coba baca .jasper
    // 2) jika gagal (mis. beda versi), compile dari .jrxml

    private static JasperReport loadReport(
            String baseName) throws Exception {

        String compiledPath =
                "/report/" + baseName + ".jasper";

        String sourcePath =
                "/report/" + baseName + ".jrxml";

        Exception errorJasper = null;

        URL jasperUrl =
                ReportService.class.getResource(compiledPath);

        if (jasperUrl != null) {

            try (InputStream in = jasperUrl.openStream()) {

                return (JasperReport) JRLoader.loadObject(in);

            } catch (Exception ex) {

                errorJasper = ex;

                System.err.println(
                    "[ReportService] " + baseName
                    + ".jasper gagal dibaca ("
                    + pesanError(ex)
                    + "). Mencoba compile dari .jrxml..."
                );
            }
        }

        URL sourceUrl =
                ReportService.class.getResource(sourcePath);

        if (sourceUrl == null) {

            String info = "Resource report tidak ditemukan: "
                    + compiledPath + " dan " + sourcePath;

            if (errorJasper != null) {
                info += "\nError .jasper: "
                        + pesanError(errorJasper);
            }

            throw new IllegalStateException(info);
        }

        try (InputStream in = sourceUrl.openStream()) {

            return JasperCompileManager.compileReport(in);
        }
    }

    // ================= EXPORT PDF =================

    public static void exportPdf(
            Component parent,
            JasperPrint print,
            String defaultName) {

        JFileChooser chooser =
                new JFileChooser();

        chooser.setSelectedFile(
            new java.io.File(defaultName)
        );

        if (chooser.showSaveDialog(parent)
                != JFileChooser.APPROVE_OPTION) {

            return;
        }

        try {

            JasperExportManager.exportReportToPdfFile(
                print,
                chooser.getSelectedFile().getAbsolutePath()
            );

            JOptionPane.showMessageDialog(
                parent,
                "PDF berhasil disimpan."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                parent,
                "Export PDF gagal: " + pesanError(ex),
                "JasperReports",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= BANTUAN =================
    // Ambil penyebab error paling dalam agar jelas

    private static String pesanError(Throwable ex) {

        Throwable t = ex;

        while (t.getCause() != null && t.getCause() != t) {
            t = t.getCause();
        }

        return t.getClass().getSimpleName()
                + ": " + t.getMessage();
    }
}