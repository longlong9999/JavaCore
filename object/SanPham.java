/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author ASUS
 */
public class SanPham {
    private String maSP;
    private String tenSP;
    private int soLuong;
    private int donGia;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, int soLuong, int donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
    public void nhapTTSP(){
        
    }
    public void in_TTSP(){
         System.out.printf("|%6s|%21s|%13d|%21s|\n",getMaSP(),getTenSP(),getSoLuong(),getDonGia());
         System.out.println("+------+---------------------+-------------+---------------------+");
        
    }
}
