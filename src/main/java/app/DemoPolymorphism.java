package app;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Instruktur;
import model.Orang;
import model.Peserta;

public class DemoPolymorphism {

    public static void tampilkanInfo(Orang orang) {
        System.out.println(orang.getInfo());
    }

    public static void cetakSemua(List<Orang> daftar) {

        for (Orang orang : daftar) {
            System.out.println(orang.getInfo());
        }
    }

    public static void main(String[] args) {

        Peserta peserta = new Peserta(
            1,
            "Ahmad Fauzan",
            "081234567801",
            "252001",
            "Informatika"
        );

        Instruktur instruktur = new Instruktur(
            2,
            "Dina Pratama",
            "081211110001",
            "Java Desktop"
        );

        Admin admin = new Admin(
            3,
            "Admin Kursus",
            "081200000001",
            "Pengelola"
        );

        List<Orang> daftarOrang =
                new ArrayList<>();

        daftarOrang.add(peserta);
        daftarOrang.add(instruktur);
        daftarOrang.add(admin);

        System.out.println("=== Loop Polymorphism ===");

        cetakSemua(daftarOrang);

        System.out.println(
            "\n=== Polymorphic Argument ==="
        );

        tampilkanInfo(peserta);
        tampilkanInfo(instruktur);
        tampilkanInfo(admin);

        System.out.println("\n=== instanceof ===");

        for (Orang orang : daftarOrang) {

            if (orang instanceof Peserta) {

                Peserta p = (Peserta) orang;

                System.out.println(
                    "NIM khusus peserta: " + p.getNim()
                );
            }
        }

        System.out.println("\n=== Uji Validasi ===");

        try {

            peserta.setNama("");

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Validasi nama: " + e.getMessage()
            );
        }

        try {

            new model.Kursus(
                "K001",
                "Java",
                "Basic",
                -50000
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Validasi biaya: " + e.getMessage()
            );
        }
    }
}