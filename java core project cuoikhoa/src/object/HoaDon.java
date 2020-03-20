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
public class HoaDon {
    private String maHD;
    private String maKH;
    private String maNV;
    private String maSP;
    private String tenSP;
    private int donGia;
    private int soLuongMua;
    private int thanhTien;
    private String ngayBan;

    public HoaDon(String maHD, String maKH, String maNV, String maSP, String tenSP, int donGia, int soLuongMua, int thanhTien, String ngayBan) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuongMua = soLuongMua;
        this.thanhTien = thanhTien;
        this.ngayBan = ngayBan;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    

   

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public HoaDon() {
    }

   

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
     public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaSP() {
        return maSP;
    }
    
   
}
