/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanHang;

import java.util.Random;
import java.util.Scanner;
import object.NhanVien;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author ASUS
 */
public class QuanLyDangNhap {
    QuanLyNhanVien qlnv= new QuanLyNhanVien();
    private String passwords;
    static String maNV;
    int viTri;
    String covert="";
    Scanner sc = new Scanner(System.in);
    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public QuanLyDangNhap(String passwords) {
        this.passwords = passwords;
    }

    public QuanLyDangNhap() {
    }
    public void dangnhap(){
        int dem=0;
        
        String id="";
        String mk="";
        NhanVien nv= new NhanVien();
        QuanLyNhanVien qlnv = new QuanLyNhanVien();
        sc = new Scanner(System.in);
        
        do{
            
            System.out.print("Nhập vào mã nhân viên :");
            id=sc.nextLine();
            System.out.print("Nhập vào mật khẩu :");
            mk=sc.nextLine();
            setPasswords(qlnv.md5(mk));
            qlnv.loadfile();
            for (int i = 0; i < qlnv.list.size(); i++) {
                if(id.equals(qlnv.list.get(i).getiD())&&getPasswords().equals(qlnv.list.get(i).getPasswords())){
                    
                    menuchinh menu= new menuchinh();
                    maNV=qlnv.list.get(i).getiD();
                    viTri=i;
                    System.err.println("Chào :"+qlnv.list.get(i).getHoTen());
                    System.err.println("Bạn đang đăng nhập với tư cách quả trị viên :");
                    menu.menuChinh();
                    dem++;
                }
                
            }
            
            if(dem==0){
                System.err.println(">>>ID nhân viên hoặc mật khẩu không chính xác!<<<");
            }
            
        }while(dem==0);
        
    }
    public int rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return randomNum;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public void sendmail() {
        try {
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("duong1200798@gmail.com", "A0977180943a"));

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("duong1200798@gmail.com", "Hệ thống QLBH");

            // Tiêu đề
            email.setSubject("EMAIL RESET PASSWORD"); //Tiêu đề khi gửi email

            // Nội dung email
            covert = String.valueOf(rand(1000, 99999));
            email.setMsg("MẬT KHẨU MỚI CỦA BẠN TRÊN HỆ THỐNG CỦA CHUỖI CỬA HÀNG TRANG THIẾT BỊ QUÂN DỤNG-BỘ QUỐC PHÒNG: " + covert + "\n" + "Hãy thay đổi mật khẩu của bạn ở phần Cập nhật thông tin cá nhân !\n"
                    + ""); //Nội dung email bạn muốn gửi.
            // Người nhận
            email.addTo("duong1200798@gmail.com"); //Đia chỉ email người nhận
            email.send(); //Thực hiện gửi.
            System.err.println("Sent Email Successfull ! Check youremail, please !");
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Gửi không thành công !");
        }
    }
    public void thayDoiMK(){
        qlnv.loadfile();
        String maNV="";
        System.out.print("Nhập vào mã nhân viên :");
        sc= new Scanner(System.in);
        maNV=sc.nextLine();
        for (int i = 0; i < qlnv.list.size(); i++) {
            if(maNV.equals(qlnv.list.get(i).getiD())){
                NhanVien nv = qlnv.list.get(i);
                sendmail();
                nv.setPasswords(qlnv.md5(covert));
                qlnv.list.set(i, nv);
                qlnv.save();
                
            }
        }
        
    }
    public void menu_DangNhap(){
                    System.out.println("+-----------------------------------------------------------------------------+");
                    System.out.println("|                                 ĐĂNG NHẬP                                   |");
                    System.out.println("+-----------------------------------------------------------------------------+");
                    System.out.println("|                              1: Đăng nhập                                   |");
                    System.out.println("+-----------------------------------------------------------------------------+");
                    System.out.println("|                              2: Quên mật khẩu                               |");
                    System.out.println("+-----------------------------------------------------------------------------+");
                    System.out.println("|                              3: Thoát                                       |");
                    System.out.println("+-----------------------------------------------------------------------------+");
                    int chon;
                    do{
                        System.out.print("Nhập vào lựa chọn :");
                        sc= new Scanner(System.in);
                        chon = sc.nextInt();
                        switch(chon){
                            case 1: dangnhap();
                                    break;
                            case 2: thayDoiMK();
                                
                                    menu_DangNhap();
                                    
                                    break;
                            case 3: System.out.println("Good Bye!!! See you again");
                                    System.exit(0);
                            default:
                                System.out.println("Lựa chọn không hợp lệ !");
                        }
                        
                    }while(true);
    }
}
