package report;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import net.sf.jasperreports.engine.JasperCompileManager;

public class ReportCompiler {

    private static final String[] REPORTS = {

        "laporan_peserta",
        "laporan_pendaftaran_semua",
        "laporan_pendaftaran",
        "laporan_pembayaran_semua",
        "laporan_pembayaran"

    };

    public static void main(String[] args) {

        String versi = JasperCompileManager.class
                .getPackage().getImplementationVersion();

        System.out.println(
            "JasperReports versi: "
            + (versi == null ? "(tidak terdeteksi)" : versi)
        );

        File dir = new File("src/main/resources/report");

        if (!dir.isDirectory()) {

            System.err.println(
                "Folder report tidak ditemukan: "
                + dir.getAbsolutePath()
            );

            System.err.println(
                "Jalankan dari root project "
                + "(folder tempat pom.xml berada)."
            );

            return;
        }

        // Salinan di target/classes agar tidak wajib Clean and Build
        File targetDir = new File("target/classes/report");

        int berhasil = 0;
        int gagal = 0;

        for (String name : REPORTS) {

            File src = new File(dir, name + ".jrxml");
            File dst = new File(dir, name + ".jasper");

            if (!src.isFile()) {

                System.err.println(
                    "File tidak ditemukan: " + src.getName()
                );

                gagal++;
                continue;
            }

            try {

                JasperCompileManager.compileReportToFile(
                    src.getAbsolutePath(),
                    dst.getAbsolutePath()
                );

                if (targetDir.isDirectory()) {

                    Files.copy(
                        src.toPath(),
                        new File(targetDir, src.getName()).toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                    );

                    Files.copy(
                        dst.toPath(),
                        new File(targetDir, dst.getName()).toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                    );
                }

                System.out.println("OK    : " + dst.getPath());
                berhasil++;

            } catch (Exception ex) {

                System.err.println(
                    "GAGAL : " + src.getName()
                );

                ex.printStackTrace();
                gagal++;
            }
        }

        System.out.println(
            "Selesai. Berhasil: " + berhasil
            + ", Gagal: " + gagal
        );
    }
}