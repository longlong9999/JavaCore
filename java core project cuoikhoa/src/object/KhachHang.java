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
public class KhachHang {
    private String maKH;
    private String hoTen;
    private String sDT;
    private String eMail;
    private String diaChi;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, String sDT, String eMail, String diaChi) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.eMail = eMail;
        this.diaChi = diaChi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoten) {
        this.hoTen = hoten;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public void nhapTT(){
        
    }
    public void in_TT(){
        System.out.printf("|%6s|%18s|%13s|%24s|%20s|\n",getMaKH(),getHoTen(),getsDT(),geteMail(),getDiaChi());
        System.out.println("+------+------------------+-------------+------------------------+--------------------+");
    }
}
