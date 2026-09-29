/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan;      //package

/**
 *
 * @author USER
 */
public class latihanbiayakursus {     // class

   public static void main(String[] args) {

        String kode = "UKT-001";
        String nama = "Pembayaran UKT";
        
        double ukt = 2100000;
        double biayaRegistrasi = 500000;
        double diskon;

        boolean aktif = true;

        // Menentukan diskon
        if (ukt >= 3500000) {
            diskon = 0.15;
        } else if (ukt >= 1500000) {
            diskon = 0.10;
        } else if (ukt >= 1000000) {
            diskon = 0.05;
        } else {
            diskon = 0;
        }

        // Menghitung potongan
        double potongan = ukt * diskon;

        // Menghitung total pembayaran
        double total = ukt - potongan + biayaRegistrasi;

        // Menentukan status
        String status;

        if (ukt >= 3000000) {
            status = "MAHAL";
        } else {
            status = "TERJANGKAU";
        }

        // Menampilkan hasil
        System.out.println("======================================");
        System.out.println("          DATA PEMBAYARAN UKT         ");
        System.out.println("======================================");

        System.out.println("Kode                 : " + kode);
        System.out.println("Nama                 : " + nama);
        System.out.println("Aktif                : " + aktif);

        System.out.printf("UKT                  : Rp%,.0f%n", ukt);
        System.out.printf("Diskon               : %.0f%%%n", diskon * 100);
        System.out.printf("Potongan Diskon      : Rp%,.0f%n", potongan);
        System.out.printf("Biaya Registrasi     : Rp%,.0f%n", biayaRegistrasi);
        System.out.printf("Total Pembayaran     : Rp%,.0f%n", total);

        System.out.println("Status               : " + status);

        System.out.println("======================================");
    }
}