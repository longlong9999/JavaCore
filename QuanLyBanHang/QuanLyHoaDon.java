/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanHang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.HoaDon;
import object.KhachHang;
import object.SanPham;
import object.docSo;
import object.gioHang;

/**
 *
 * @author ASUS
 */
public class QuanLyHoaDon {

    ArrayList<HoaDon> list = new ArrayList<>();
    ArrayList<gioHang> listgh = new ArrayList<>();
    Date mahoadon = new Date();
    SimpleDateFormat formatmahoadon = new SimpleDateFormat("YYYY_MM_dd_HH_mm_sS");
    Date ngay_ban= new Date();
    SimpleDateFormat formatngayban = new SimpleDateFormat("HH/dd/MM/YYYY");
    Scanner sc = new Scanner(System.in);
    KhachHang kh = new KhachHang();
    QuanLySanPham qlsp = new QuanLySanPham();
    QuanLyKhachHang qlkh = new QuanLyKhachHang();
    QuanLyNhanVien qlnv = new QuanLyNhanVien();
    QuanLyDangNhap dn = new QuanLyDangNhap();
    HoaDon hd = new HoaDon();
    gioHang gh = new gioHang();
    SanPham sp = new SanPham();
    docSo docso = new docSo();
    menuchinh menuchinh = new menuchinh();
    int index = 0;
    int mua = 0;
    int viTriKH = 0;
    String maHoaDon="";
    String ngayBAN="";

    public void tim_KiemKH() {
        qlkh.loadfile_KH();
        HoaDon hd = new HoaDon();

        int dem = 0;

        do {
            System.out.print("Nhập vào mã khách hàng :");
            sc = new Scanner(System.in);
            hd.setMaKH(sc.nextLine());

            for (int i = 0; i < qlkh.list.size(); i++) {
                kh = qlkh.list.get(i);
                if (hd.getMaKH().contains(qlkh.list.get(i).getMaKH())) {
                    System.out.println("+------+------------------+-------------+------------------------+--------------------+");
                    System.out.println("|  ID  |    Họ và tên     |Số điện thoại|           Email        |      Địa chỉ       |");
                    System.out.println("+------+------------------+-------------+------------------------+--------------------+");
                    qlkh.list.get(i).in_TT();
                    viTriKH = i;
                    dem++;
//                    hd.setMaHD(qlkh.list.get(i).getMaKH() + "" + formatmahoadon.format(mahoadon));

                }
            }
            if (dem == 0) {
                System.out.println(">>>Không tìm thấy khách hàng !<<<");

                themKH();
            }

        } while (dem == 0);

    }

    public void tim_KiemSP() {
        int dem1 = 0;
        int dem = 0;
        qlsp.loadfile_SP();

        do {
            HoaDon hd = new HoaDon();
            System.out.print("Nhập vào mã sản phẩm cần mua :");
            sc = new Scanner(System.in);
            hd.setMaSP(sc.nextLine());
            for (int i = 0; i < qlsp.list.size(); i++) {
                sp = qlsp.list.get(i);
                if (hd.getMaSP().contains(qlsp.list.get(i).getMaSP())) {

                    System.out.println("+------+---------------------+-------------+---------------------+");
                    System.out.println("|  ID  |    Tên sản phẩm     |   Số lượng  |        Đơn giá      |");
                    System.out.println("+------+---------------------+-------------+---------------------+");
                    qlsp.list.get(i).in_TTSP();

                    dem++;
                    do {
                        System.out.print("Nhập vào số lượng sản phẩm cần mua :");
                        mua = Integer.parseInt(sc.nextLine());
                        qlsp.loadfile_SP();
                        if (mua > 0 && mua <= qlsp.list.get(i).getSoLuong()) {
                            hd.setSoLuongMua(mua);
                            int soLuongCon = qlsp.list.get(i).getSoLuong() - hd.getSoLuongMua();
                            qlsp.list.get(i).setSoLuong(soLuongCon);

                            dem1++;
                            index = i;

                        } else {
                            System.err.println(">>>Số lượng mua phải lớn hơn 0 và nhỏ hơn số lượng còn lại ở trong kho<<<");
                        }
                    } while (dem1 == 0);
                }
            }
            if (dem == 0) {
                System.err.println(">>>Sản phẩm cửa hàng không kinh doanh hoặc đã bán ngừng bán do chính sách của nhà nước! <<<");
            }
            add_TT();
            add_gioHang();
            qlsp.save_SP();

        } while (dem == 0);

    }

    public void menu_BanHang() {
        System.out.println(">>>>>>Bạn có muốn mua tiếp hay không<<<<<<");
        System.out.println("");

        System.out.println("");
        System.out.println("+----------------------------------------+");
        System.out.println("|            1: Tiếp tục mua             |");
        System.out.println("+----------------------------------------+");
        System.out.println("|            2: Thanh toán               |");
        System.out.println("+----------------------------------------+");
        int chon = 0;

        do {
            System.out.print("Nhập vào lựa chọn :");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    System.err.println("Bạn đã chọn chức năng tiếp tục mua hàng :");
                    tim_KiemSP();

                    menu_BanHang();
                    break;
                case 2:
                    System.err.println("Bạn đã chọn chức năng thanh toán :");
                    inHoaDon();
                    menu_BanVuKhi();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    public void add_TT() {
        HoaDon hd = new HoaDon();
        hd.setMaHD(qlkh.list.get(viTriKH).getMaKH() + "_" + formatmahoadon.format(mahoadon));
        hd.setMaKH(qlkh.list.get(viTriKH).getMaKH());
        hd.setMaNV(dn.maNV);
        hd.setMaSP(qlsp.list.get(index).getMaSP());
        hd.setTenSP(qlsp.list.get(index).getTenSP());
        hd.setDonGia(qlsp.list.get(index).getDonGia());
        hd.setSoLuongMua(mua);
        hd.setThanhTien(hd.getSoLuongMua() * hd.getDonGia());
        hd.setNgayBan(formatmahoadon.format(mahoadon));
        list.add(hd);
        save_HD();
    }

    public void add_gioHang() {
        gioHang gh = new gioHang();
        gh.setMaHD(qlkh.list.get(viTriKH).getMaKH() + "_" + formatmahoadon.format(mahoadon));
        gh.setMaKH(qlkh.list.get(viTriKH).getMaKH());
        gh.setTenSP(qlsp.list.get(index).getTenSP());
        gh.setMaNV(dn.maNV);
        gh.setDonGia(qlsp.list.get(index).getDonGia());
        gh.setSoLuongMua(mua);
        gh.setThanhTien(gh.getSoLuongMua() * gh.getDonGia());
        gh.setNgayBan(formatngayban.format(ngay_ban));
        ngayBAN=gh.getNgayBan();
        maHoaDon=gh.getMaHD();
        listgh.add(gh);

    }

    public void inHoaDon() {

        int tienNhan = 0;

        String tienBangChu = "";
        int tienThua = 0;
        int tienThanhToan = 0;

        System.out.print("Nhập vào số tiền nhận của khách :");
        sc = new Scanner(System.in);
        tienNhan = sc.nextInt();
        System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                CỬA HÀNG TRANG THIẾT BỊ QUÂN DỤNG                                                                     |");
        System.out.println("|           Địa chỉ:Ngọc Giang-Vĩnh Ngọc-Đông Anh-Hà Nội                                                               |");
        System.out.println("|            *****Vì nước hi sinh vì dân phục vụ*****                                                                  |");
        System.out.println("|                     Điện thoại :0977.180.943                                                                         |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                   HÓA ĐƠN THANH TOÁN                                                 |");
        System.out.printf("|                                          Mã hóa đơn    : %-60s|\n", maHoaDon);
        System.out.printf("|                                          Thời gian bán : %-60s|\n", ngay_ban);
        System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|                                        Tên khách hàng  : %-60s|\n",qlkh.list.get(index).getHoTen());
        System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|                                        Tên nhân viên   : %-60s|\n",tenNV(dn.maNV));
        System.out.println("+-------+--------+-----------------------------------+-------------+-----------------+----------------+----------------|");
        System.out.println("|  STT  | IDKH   |             Tên sản phẩm          |Mã nhân viên |   Số lượng mua  |     Đơn giá    |   Thành tiền   |");
        System.out.println("+-------+--------+-----------------------------------+-------------+-----------------+----------------+----------------|");

        for (int i = 0; i < listgh.size(); i++) {

            System.out.printf("|%7d|%8s|%35s|%13s|%17d|%16d|%16d|\n", i + 1, listgh.get(i).getMaKH(), listgh.get(i).getTenSP(), listgh.get(i).getMaNV(), listgh.get(i).getSoLuongMua(), listgh.get(i).getDonGia(), listgh.get(i).getThanhTien());
            tienThanhToan = tienThanhToan + listgh.get(i).getThanhTien();
        }

        tienThua = tienNhan - tienThanhToan;
        tienBangChu = String.valueOf(tienThanhToan);
        System.out.println("+-------+--------+-----------------------------------+-------------+-----------------+----------------+----------------|");
        System.out.printf("|                                                             Tổng số tiền sản phẩm        :          |%16d|\n", tienThanhToan);
        System.out.println("+-------+--------+-----------------------------------+-------------+-----------------+----------------+----------------|");
        System.out.printf("|                                                             Tiền nhận của khách hàng     :          |%16d|\n", tienNhan);
        System.out.println("+-----------------------------------------------------------------------------------------------------+----------------+");
        System.out.printf("|                                                             Tiền trả lại khách           :          |%16d|\n", tienThua);
        System.out.println("+-----------------------------------------------------------------------------------------------------+----------------+");
//            if(tienThua<0){
//                System.out.println("+-----------------------------------------------------------------------------------------------------+----------------+");
//            System.out.println("|                                                             Bạn còn nợ một khoản         :          |"+tienThua+"    |");
//                System.out.println("+-----------------------------------------------------------------------------------------------------+----------------+");
//            }
//        System.out.println("|           Tiền thanh toán bằng bằng chữ :" + docso.readNum(tienBangChu) + "                                        |");
//        System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                     Khách hàng :                                    Nhân viên bán hàng :                             |");
        System.out.println("|                      ( Kí tên )                                          ( Kí tên)                                   |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                                                                                                                      |");
        System.out.println("|                    " + qlkh.list.get(viTriKH).getHoTen() + "                                         " + tenNV(dn.maNV) + "                               |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
        System.out.println("Cảm ơn bạn đã sử dụng dịch vụ của chuỗi cửu hàng TRANG THIẾT BỊ QUÂN DỤNG - BỘ QUỐC PHONG  !                           ");

    }

    public void save_HD() {
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            for (int i = 0; i < list.size(); i++) {
                String row = "";
                row = row + list.get(i).getMaHD() + "\t";
                row = row + list.get(i).getMaKH() + "\t";
                row = row + list.get(i).getMaNV() + "\t";
                row = row + list.get(i).getMaSP() + "\t";
                row = row + list.get(i).getTenSP() + "\t";
                row = row + list.get(i).getDonGia() + "\t";
                row = row + list.get(i).getSoLuongMua() + "\t";
                row = row + list.get(i).getThanhTien() + "\t";
                row = row + list.get(i).getNgayBan() + "\n";
                data = data + row;

            }
            fw = new FileWriter("hoadon.txt");
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Lỗi :" + ex.getMessage());
        }

    }

    public void loadfile_HoaDon() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            list = new ArrayList<>();
            fr = new FileReader("hoadon.txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(arr[0]);
                    hd.setMaKH(arr[1]);
                    hd.setMaNV(arr[2]);
                    hd.setMaSP(arr[3]);
                    hd.setTenSP(arr[4]);
                    hd.setDonGia(Integer.parseInt(arr[5]));
                    hd.setSoLuongMua(Integer.parseInt(arr[6]));
                    hd.setThanhTien(Integer.parseInt(arr[7]));
                    hd.setNgayBan(arr[8]);
                    list.add(hd);
                }

            } catch (IOException ex) {
                System.out.println("Lỗi :" + ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi :" + ex.getMessage());
        }
    }

    public void menu_BanVuKhi() {

        System.out.println("");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                            QUẢN LÝ BÁN HÀNG                                |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                              1: Bán hàng                                   |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                              2: Thoát                                      |");
        System.out.println("+----------------------------------------------------------------------------+");
        int chon;
        do {
            System.out.print("Nhập vào lựa chọn :");
            sc = new Scanner(System.in);
            chon = sc.nextInt();
            switch (chon) {
                case 1:

                    tim_KiemKH();
                    tim_KiemSP();
                    menu_BanHang();
                    loadfile_HoaDon();

                    break;
                case 2:
                    System.err.println("Bạn đã chọn thoát chức năng bán hàng!");
                    menuchinh.menuChinh();
                    break;
                default:
                    System.err.println(">>>Lựa chọn không hợp lệ !<<<");
            }
        } while (true);
    }

    public String tenNV(String maNV) {
        qlnv.loadfile();
        for (int i = 0; i < qlnv.list.size(); i++) {
            if (maNV.equals(qlnv.list.get(i).getiD())) {
                return qlnv.list.get(i).getHoTen();
            }

        }
        return "";
    }

    public void themKH() {
        System.out.println("Bạn có muốn thêm khách hàng mới hay không :");
        System.out.println("+---------------------------------------+");
        System.out.println("|            1:Thêm khách hàng          |");
        System.out.println("+---------------------------------------+");
        System.out.println("|            2:Nhập lại mã khách hàng   |");
        System.out.println("+---------------------------------------+");
        int chon;

        System.out.print("Nhập vào lựa chọn :");
        sc = new Scanner(System.in);
        chon = sc.nextInt();
        switch (chon) {
            case 1:
                System.out.println("Bạn đang chọn chức năng thêm mới khách hàng :");
                qlkh.add_khachhang();
                qlkh.save_khachhang();
                break;
            case 2:
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }

    }

}
