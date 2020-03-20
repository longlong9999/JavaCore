/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanHang;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class menuchinh {
    public void menuChinh(){
          Scanner sc = new Scanner (System.in);
        QuanLyNhanVien qlnv= new QuanLyNhanVien();
        QuanLyKhachHang qlkh= new QuanLyKhachHang();
        QuanLySanPham qlsp= new QuanLySanPham();
        QuanLyHoaDon qlhd = new QuanLyHoaDon();
        BaoCaoThongKe bctk= new BaoCaoThongKe();
        QuanLyDangNhap qldn = new QuanLyDangNhap();
        int chon;
        bctk.Kho();
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                              QUẢN LÝ BÁN HÀNG                              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       1: Quản lý thông tin nhân viên                       |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       2: Quản lý thông tin khách hàng                      |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       3: Quản lý thông tin sản phẩm                        |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       4: Chức năng bán hàng                                |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       5: Xem báo cáo thống kê                              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       6: Đăng xuất                                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        
        do{
            System.out.print("Nhập vào lựa chọn :");
        chon =sc.nextInt();
            switch(chon){
                case 1 :
                    qlnv.loadfile();
                    qlnv.menu_nhanvien();
                    break;
                case 2:
                    qlkh.loadfile_KH();
                    qlkh.menu_KH();
                    break;
                case 3:
                    qlsp.loadfile_SP();
                    qlsp.menu_SP();
                    break;
                case 4: 
                    System.err.println("Bạn đã chọn chức năng bán hàng :");
                    qlhd.loadfile_HoaDon();
                    qlhd.menu_BanVuKhi();
                    break;
                case 5:
                    System.out.println("Bạn đã chọn chức năng xem thống kê :");
                    qlhd.loadfile_HoaDon();
                    bctk.menu_BCTK();
                case 6:
                    System.out.println("Bạn đã chọn chức năng đăng xuất :");
                    qldn.menu_DangNhap();
            default:
                System.err.println(">>>Không hợp lệ<<<");
            }
        }while (true);
    }
}
