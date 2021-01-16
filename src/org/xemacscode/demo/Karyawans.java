/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

/**
 *
 * @author Windows 10
 */
public class Karyawans {
    private int id;
    private String nama;
    private String jabatan;
    private int tahunMasuk;
    private int pages;

    public Karyawans(int id, String nama, String jabatan, int tahunMasuk, int pages) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.tahunMasuk = tahunMasuk;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public int getTahunMasuk() {
        return tahunMasuk;
    }

    public int getPages() {
        return pages;
    }
    
}
