/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan;

/**
 *
 * @author USER
 */
import java.util.Scanner;

public class latihanbidangdatar {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int pilihan;

        System.out.println("====================================");
        System.out.println("   PROGRAM LUAS & KELILING");
        System.out.println("        BIDANG DATAR");
        System.out.println("====================================");
        System.out.println("1. Persegi");
        System.out.println("2. Persegi Panjang");
        System.out.println("3. Lingkaran");
        System.out.println("====================================");

        System.out.print("Pilih bidang datar [1-3]: ");
        pilihan = input.nextInt();

        switch (pilihan) {

            case 1:
                // Persegi
                System.out.println("\n--- PERSEGI ---");

                System.out.print("Masukkan sisi: ");
                double sisi = input.nextDouble();

                double luasPersegi = sisi * sisi;
                double kelilingPersegi = 4 * sisi;

                System.out.println("Luas      = " + luasPersegi);
                System.out.println("Keliling  = " + kelilingPersegi);
                break;

            case 2:
                // Persegi Panjang
                System.out.println("\n--- PERSEGI PANJANG ---");

                System.out.print("Masukkan panjang: ");
                double panjang = input.nextDouble();

                System.out.print("Masukkan lebar: ");
                double lebar = input.nextDouble();

                double luasPersegiPanjang = panjang * lebar;
                double kelilingPersegiPanjang =
                        2 * (panjang + lebar);

                System.out.println("Luas      = " + luasPersegiPanjang);
                System.out.println("Keliling  = " + kelilingPersegiPanjang);
                break;

            case 3:
                // Lingkaran
                System.out.println("\n--- LINGKARAN ---");

                System.out.print("Masukkan jari-jari: ");
                double r = input.nextDouble();

                double luasLingkaran = Math.PI * r * r;
                double kelilingLingkaran = 2 * Math.PI * r;

                System.out.println("Luas      = " + luasLingkaran);
                System.out.println("Keliling  = " + kelilingLingkaran);
                break;

            default:
                System.out.println("Pilihan tidak tersedia!");
        }

        System.out.println("\n====================================");
        System.out.println("Program selesai.");
        System.out.println("====================================");

        input.close();
    }
}