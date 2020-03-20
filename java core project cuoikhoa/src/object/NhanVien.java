/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import checkdata.checkdata;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class NhanVien {
    private String iD;
    private String hoTen;
    private String sDT;
    private String passwords;
    private String eMail;
    private String diaChi;
    Scanner sc = new Scanner (System.in);
    checkdata check = new checkdata();
    public NhanVien(String iD, String hoTen, String sDT,String passwords, String eMail,String diaChi) {
        this.iD = iD;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.passwords=passwords;
        this.eMail = eMail;
        this.diaChi= diaChi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public NhanVien() {
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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
    public void nhapTT(){
        String ten="";
        System.out.print("Nhập vào mã nhân viên :");
        this.iD= sc.nextLine();
        do{ 
            System.out.print("Nhập vào họ và tên nhân viên :");
            ten=sc.nextLine();
            if(ten.equals("")){
                System.err.println("Không được để tên trống!");
            }
            else{
                ten=check.chuanhoaten(ten);
                
                this.hoTen=ten;
                check.check_ten(this.hoTen);
                    
                
            }

           
        }while(ten.equals(""));
        
        do{
            System.out.print("Nhập vào số điện thoại :");
        this.sDT= sc.nextLine();
           
            
           
        }while(check.check_sdt(this.sDT));
        do{
            System.out.print("Nhập vào mật khẩu :");
            this.passwords=sc.nextLine();
            if(getPasswords().equals("")){
                System.out.println("Mật khẩu không được để khoảng trống .");
            }
           
        }while(getPasswords().equals(""));
        
        do{
            System.out.print("Nhập vào emai :");
            this.eMail = sc.nextLine();
            check.check_email(this.eMail);
        }while(check.check_email(this.eMail));
        
        System.out.print("Nhập vào địa chỉ :");
        this.diaChi=sc.nextLine();
    }
    public void inTT(){
        System.out.printf("|%6s|%18s|%13s|%22s|%10s|\n",getiD(),getHoTen(),getsDT(),geteMail(),getDiaChi());
        System.out.println("+------+------------------+-------------+----------------------+----------+");
        
    }
    
}
