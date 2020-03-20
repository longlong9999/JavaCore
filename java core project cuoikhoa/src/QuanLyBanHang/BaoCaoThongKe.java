/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import object.HoaDon;

/**
 *
 * @author ASUS
 */
public class BaoCaoThongKe {

    Scanner sc = new Scanner(System.in);
    ArrayList<HoaDon> listhd = new ArrayList<>();
    Date thoi_Gian = new Date();
    SimpleDateFormat formatdate = new SimpleDateFormat("HH:mm:ss");
    QuanLySanPham qlsp = new QuanLySanPham();
    QuanLyHoaDon qlhd = new QuanLyHoaDon();
    menuchinh menu= new menuchinh();
    String thoiGian = "";

    public void Kho() {
        qlsp.loadfile_SP();

        int dem = 0;
        int dem1 = 0;
        for (int i = 0; i < qlsp.list.size(); i++) {
            if (qlsp.list.get(i).getSoLuong() < 20) {
                dem++;
            } else if (qlsp.list.get(i).getSoLuong() > 60) {
                dem1++;
            }
        }
        System.out.println("Hiện tại có :" + dem1 + " sản phẩm tồn kho!");
        System.out.println("Hiện tại có :" + dem + " sản phẩm sắp hết hàng!");
    }

    public void baoCaoThang() {
        
        int demmm = 0;
        qlhd.loadfile_HoaDon();
        ArrayList<HoaDon> listhd = new ArrayList<>();
        String thang = "";
        do {
            System.out.println("!!!Lưu ý định dạng ngày và tháng nhỏ hơn 10 phải có số 0. Ví dụ 03");
            System.out.print("Nhập vào số tháng cần hiển thị thống kê :");
           
            sc = new Scanner(System.in);
            thang = sc.nextLine();
            for (int i = 0; i < qlhd.list.size(); i++) {
                String arr[] = qlhd.list.get(i).getNgayBan().split("_");

                if (thang.equals(arr[1])) {
                    
                    listhd.add(qlhd.list.get(i));
                    demmm++;
                }

            }
            if (demmm > 0) {
                thoiGian = formatdate.format(thoi_Gian);
                long tongDoanhThu = 0;
                System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|                CỬA HÀNG TRANG THIẾT BỊ QUÂN DỤNG                                                                     |");
                System.out.println("|           Địa chỉ:Ngọc Giang-Vĩnh Ngọc-Đông Anh-Hà Nội                                                               |");
                System.out.println("|            *****Vì nước hi sinh vì dân phục vụ*****                                                                  |");
                System.out.println("|                     Điện thoại :0977.180.943                                                                         |");
                System.out.println("|                                                                                                                      |");
                System.out.println("|                                                   BÁO CÁO THỐNG KÊ                                                   |");
                System.out.printf("|                                  Thời gian thống kê    : %-60s|\n", thoiGian);
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                System.out.println("|  STT  | IDKH   |  IDSP   |        Tên sản phẩm     |Mã nhân viên |   Số lượng mua  |     Đơn giá    |   Thành tiền   |");
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                for (int i = 0; i < listhd.size(); i++) {
                    System.out.printf("|%7d|%8s|%9s|%25s|%13s|%17d|%16d|%16d|\n", i + 1, listhd.get(i).getMaKH(), listhd.get(i).getMaSP(), listhd.get(i).getTenSP(), listhd.get(i).getMaNV(), listhd.get(i).getSoLuongMua(), listhd.get(i).getDonGia(), listhd.get(i).getThanhTien());
                    tongDoanhThu = tongDoanhThu + (long) listhd.get(i).getThanhTien();
                }
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                System.out.printf("|                                                             Tổng số tiền sản phẩm        :          |%16d|\n", tongDoanhThu);
                System.out.println("+----------------------------------------------------------------------------------------------------------------------|");
            } else if (demmm == 0) {
                System.err.println(">>>Không tìm thấy thông tin!<<<");
            }
        } while (demmm == 0);
    }

    public void BCTheoNgay() {
        
        int dem = 0;
        qlhd.loadfile_HoaDon();
        ArrayList<HoaDon> listhd = new ArrayList<>();
        String ngay = "";
        String thang = "";
        
        System.out.println("Nhập vào ngày tháng cần hiển thị thống kê :");
        
        do {
            System.out.println("!!!Lưu ý định dạng ngày và tháng nhỏ hơn 10 phải có số 0. Ví dụ 03");
            System.out.print("Nhập vào ngày :");
            sc = new Scanner(System.in);
            ngay = sc.nextLine();
            System.out.print("Nhập vào tháng :");
            thang = sc.nextLine();
            for (int i = 0; i < qlhd.list.size(); i++) {
                String arr[] = qlhd.list.get(i).getNgayBan().split("_");
                if (ngay.equals(arr[2]) && thang.equals(arr[1])) {
                    
                    listhd.add(qlhd.list.get(i));
                    dem++;
                }
            }
            if (dem > 0) {
                thoiGian = formatdate.format(thoi_Gian);
                long tongDoanhThu = 0;
                System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|                CỬA HÀNG TRANG THIẾT BỊ QUÂN DỤNG                                                                     |");
                System.out.println("|           Địa chỉ:Ngọc Giang-Vĩnh Ngọc-Đông Anh-Hà Nội                                                               |");
                System.out.println("|            *****Vì nước hi sinh vì dân phục vụ*****                                                                  |");
                System.out.println("|                     Điện thoại :0977.180.943                                                                         |");
                System.out.println("|                                                                                                                      |");
                System.out.println("|                                                   BÁO CÁO THỐNG KÊ                                                   |");
                System.out.printf("|                                     Thời gian thống kê : %-60s|\n", thoiGian);
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                System.out.println("|  STT  | IDKH   |  IDSP   |        Tên sản phẩm     |Mã nhân viên |   Số lượng mua  |     Đơn giá    |   Thành tiền   |");
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                for (int i = 0; i < listhd.size(); i++) {
                    System.out.printf("|%7d|%8s|%9s|%25s|%13s|%17d|%16d|%16d|\n", i + 1, listhd.get(i).getMaKH(), listhd.get(i).getMaSP(), listhd.get(i).getTenSP(), listhd.get(i).getMaNV(), listhd.get(i).getSoLuongMua(), listhd.get(i).getDonGia(), listhd.get(i).getThanhTien());
                    tongDoanhThu = tongDoanhThu + (long) listhd.get(i).getThanhTien();
                }
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                System.out.printf("|                                                             Tổng số tiền sản phẩm        :          |%16d|\n", tongDoanhThu);
                System.out.println("+----------------------------------------------------------------------------------------------------------------------|");
            } else if (dem == 0) {
                System.err.println(">>>Không tìm thấy thông tin!<<<");
            }
        } while (dem == 0);
    }
    public void BCTKnhanVien(){
        qlhd.loadfile_HoaDon();
        String maNV="";
        int dem=0;
        ArrayList<HoaDon> listhd= new ArrayList<>();
        
        do{
            System.out.print("Nhập vào mã nhân viên :");
            sc= new Scanner(System.in);
            maNV=sc.nextLine();
            for (int i = 0; i < qlhd.list.size(); i++) {
                if(maNV.equals(qlhd.list.get(i).getMaNV())){
                    listhd.add(qlhd.list.get(i));
                    dem++;
                }
            }
            if(dem>0){
                thoiGian = formatdate.format(thoi_Gian);
                long tongDoanhThu = 0;
                System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
                System.out.println("|                CỬA HÀNG TRANG THIẾT BỊ QUÂN DỤNG                                                                     |");
                System.out.println("|           Địa chỉ:Ngọc Giang-Vĩnh Ngọc-Đông Anh-Hà Nội                                                               |");
                System.out.println("|            *****Vì nước hi sinh vì dân phục vụ*****                                                                  |");
                System.out.println("|                     Điện thoại :0977.180.943                                                                         |");
                System.out.println("|                                                                                                                      |");
                System.out.println("|                                                   BÁO CÁO THỐNG KÊ                                                   |");
                System.out.printf("|                                     Thời gian thống kê : %-60s|\n", thoiGian);
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                System.out.println("|  STT  | IDKH   |  IDSP   |        Tên sản phẩm     |Mã nhân viên |   Số lượng mua  |     Đơn giá    |   Thành tiền   |");
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                for (int i = 0; i < listhd.size(); i++) {
                    System.out.printf("|%7d|%8s|%9s|%25s|%13s|%17d|%16d|%16d|\n", i + 1, listhd.get(i).getMaKH(), listhd.get(i).getMaSP(), listhd.get(i).getTenSP(), listhd.get(i).getMaNV(), listhd.get(i).getSoLuongMua(), listhd.get(i).getDonGia(), listhd.get(i).getThanhTien());
                    tongDoanhThu = tongDoanhThu + (long) listhd.get(i).getThanhTien();
                }
                System.out.println("+-------+--------+---------+-------------------------+-------------+-----------------+----------------+----------------|");
                System.out.printf("|                                                             Tổng số tiền sản phẩm        :          |%16d|\n", tongDoanhThu);
                System.out.println("+----------------------------------------------------------------------------------------------------------------------|");
            }
            else if(dem==0){
                System.err.println(">>>Không tìm thấy thông tin!<<<");
            }
        }while(dem==0);
    }
    public void menu_BCTK(){
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                              BÁO CÁO THỐNG KÊ                              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        1: Xem thống kê theo tháng                          |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        2: Xem thống kê theo ngày tháng nhập vào            |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        3: Xem thống kê của nhân viên bán được              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        4: Thoát chức năng                                  |");
        System.out.println("+----------------------------------------------------------------------------+");
        int chon;
        do{
            System.out.print("Nhập vào lựa chọn :");
            sc= new Scanner(System.in);
            chon = sc.nextInt();
            switch(chon){
                case 1:
                    System.out.println("Bạn đã chọn chức năng thống kê doanh thu trong một tháng : ");
                    baoCaoThang();
                    menu_BCTK();
                    break;
                case 2: 
                    System.out.println("Bạn đã chọn chức năng thống kê doanh thu trong ngày bất kì :");
                    BCTheoNgay();
                    menu_BCTK();
                    break;
                case 3:
                    System.out.println("Bạn đã chọn chức năng thống kê doanh thu nhân viên bán được :");
                    BCTKnhanVien();
                    menu_BCTK();
                    break;
                case 4:
                    System.out.println("Bạn đã chọn thoát chức năng thống kê !");
                    menu.menuChinh();
                    break;
            }
        }while(true);
    }
}
