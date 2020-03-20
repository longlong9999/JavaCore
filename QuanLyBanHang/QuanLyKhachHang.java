/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanHang;

import checkdata.checkdata;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.KhachHang;
import object.NhanVien;

/**
 *
 * @author ASUS
 */
public class QuanLyKhachHang {

    ArrayList<KhachHang> list = new ArrayList<>();
    menuchinh menu_chinh = new menuchinh();
    KhachHang kh = new KhachHang();
    checkdata check = new checkdata();
    Scanner sc = new Scanner(System.in);
    int index;

    public boolean check_exists(String maKH) {
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {

            if (maKH.equalsIgnoreCase(list.get(i).getMaKH())) {
                check = true;
                index = i;
                break;
            }
        }
        return check;
    }

    public void in_dskh() {
        loadfile_KH();
        if (list.isEmpty()) {
            System.err.println(">>>Danh sách rỗng!<<<");
        } else {
            System.out.println("+-------------------------------------------------------------------------------------+");
            System.out.println("|                               Danh Sách KHÁCH HÀNG                                  |");
            System.out.println("+------+------------------+-------------+------------------------+--------------------+");
            System.out.println("|  ID  |    Họ và tên     |Số điện thoại|           Email        |      Địa chỉ       |");
            System.out.println("+------+------------------+-------------+------------------------+--------------------+");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).in_TT();
            }
        }

    }

    public void add_khachhang() {
        sc = new Scanner(System.in);
        String iDKH = "";
        String tenKH = "";
        do {
            System.out.print("Nhập vào mã khách hàng :");
            kh.setMaKH(sc.nextLine());
            iDKH = kh.getMaKH();
            if (!check_exists(iDKH)) {
                do {

                    System.out.print("Nhập vào tên khách hàng :");
                    tenKH = sc.nextLine();
                    if (tenKH.equals("")) {
                        System.err.println(">>>Tên khách hàng không được để trống<<<");
                    } else {
                        tenKH = check.chuanhoaten(tenKH);
                        kh.setHoTen(tenKH);
//                        check.check_ten(kh.getHoTen());

                    }
                } while (tenKH.equals("") || check.check_ten(kh.getHoTen()));

                do {
                    System.out.print("Nhập vào số điện thoại :");
                    kh.setsDT(sc.nextLine());
                } while (check.check_sdt(kh.getsDT()));

                do {
                    System.out.print("Nhập vào địa chỉ Email :");
                    kh.seteMail(sc.nextLine());
                } while (check.check_email(kh.geteMail()));

                System.out.print("Nhập vào địa chỉ của khách hàng :");
                kh.setDiaChi(sc.nextLine());
                System.out.println(kh.getDiaChi());
                list.add(kh);
                System.err.println("Thêm mới khách hàng thành công !");
            } else {
                System.err.println(">>>Khách hàng đã tồn! thêm mới thất bại<<<");
            }
        } while (!check_exists(iDKH));
        save_khachhang();
    }

    public KhachHang seach_KH() {
        KhachHang kh = new KhachHang();
        System.out.print("Nhập vào mã khách hàng cần tìm kiếm :");
        sc = new Scanner(System.in);
        String maKH = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            kh = list.get(i);
            if (maKH.equalsIgnoreCase(kh.getMaKH())) {
                index = i;
                break;
            }

        }
        if (kh == null || !maKH.equals(kh.getMaKH())) {
            System.err.println(">>>Không tìm thấy nhân viên !<<<");
        }
        return kh;
    }

    public void sua_TTKH() {
        Scanner sc = new Scanner(System.in);
        KhachHang kh = new KhachHang();
        System.out.print("Nhập vào mã khách hàng cần tìm kiếm :");
        sc = new Scanner(System.in);
        String maKH = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            kh = list.get(i);
            if (maKH.equalsIgnoreCase(kh.getMaKH())) {
                index = i;
                break;
            }

        }
        if (kh == null || !maKH.equals(kh.getMaKH())) {
            System.err.println(">>>Không tìm thấy nhân viên <<<");
        } else {
            KhachHang kh1 = new KhachHang();
            System.out.println("Cập nhật thông tin mới cho khách hàng :");
            kh1.setMaKH(kh.getMaKH());

            System.out.println("Tên khách hàng cũ là :" + kh.getHoTen());

            do {
                System.out.print("Nhập vào tên mới của khách hàng :");
                kh1.setHoTen(sc.nextLine());
                if (kh1.getHoTen().equals("")) {
                    kh1.setHoTen(kh.getHoTen());
                } else {
                    check.chuanhoaten(kh1.getHoTen());
                }
            } while (check.check_ten(kh1.getHoTen()));

            System.out.println("Số điện thoại khách hàng cũ là :" + kh.getsDT());

            do {
                System.out.print("Nhập vào số điện thoại mới :");
                kh1.setsDT(sc.nextLine());
                if (kh1.getsDT().equals("")) {
                    kh1.setsDT(kh.getsDT());
                }
            } while (check.check_sdt(kh1.getsDT()));

            System.out.println("Email cũ của khách hàng :" + kh.geteMail());

            do {
                System.out.print("Nhập vào Email mới của khách hàng :");
                kh1.seteMail(sc.nextLine());
                if (kh1.geteMail().equals("")) {
                    kh1.seteMail(kh.geteMail());
                }
            } while (check.check_email(kh1.geteMail()));

            System.out.println("Địa chỉ cũ của khách hàng :" + kh.getDiaChi());
            System.out.print("Nhập vào địa chỉ mới của khách hàng :");
            kh1.setDiaChi(sc.nextLine());
            if (kh1.getDiaChi().equals("")) {
                kh1.setDiaChi(kh.getDiaChi());

            }
            list.set(index, kh1);
            System.err.println("Sửa thông tin khách hàng thành công!");
        }
        save_khachhang();
    }

    public void tim_Kien_KH() {
        int dem = 0;
        System.out.print("Nhập vào thông tin khách hàng cần tìm kiếm :");
        sc = new Scanner(System.in);
        String input = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (input.contains(list.get(i).getMaKH()) || input.contains(list.get(i).getHoTen()) || input.contains(list.get(i).getsDT()) || input.contains(list.get(i).geteMail())) {
                System.out.println("+------+------------------+-------------+------------------------+--------------------+");
                list.get(i).in_TT();
                dem++;
            }
        }
        if (dem == 0) {
            System.err.println(">>>Không tìm thấy khách hàng!<<<");
        }
    }

    public void delete_KH() {
        KhachHang kh = new KhachHang();
        System.out.print("Nhập vào mã khách hàng cần tìm kiếm :");
        sc = new Scanner(System.in);
        String maKH = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            kh = list.get(i);
            if (maKH.equalsIgnoreCase(kh.getMaKH())) {
                index = i;

            }

        }
        if (kh == null || !maKH.equals(kh.getMaKH())) {
            System.err.println(">>>Không tìm thấy nhân viên !<<<");
        } else {
            list.remove(kh);
            System.err.println("Xóa thành công khách hàng");

        }
        save_khachhang();
    }

    public void save_khachhang() {
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            for (int i = 0; i < list.size(); i++) {
                String row = "";
                row = row + list.get(i).getMaKH() + "\t";
                row = row + list.get(i).getHoTen() + "\t";
                row = row + list.get(i).getsDT() + "\t";
                row = row + list.get(i).geteMail() + "\t";
                row = row + list.get(i).getDiaChi() + "\n";
                data = data + row;

            }
            fw = new FileWriter("khachhang.txt");
            bw = new BufferedWriter(fw);

            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Lỗi :" + ex.getMessage());
        }
    }

    public void loadfile_KH() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            list = new ArrayList<>();
            fr = new FileReader("khachhang.txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(arr[0]);
                    kh.setHoTen(arr[1]);
                    kh.setsDT(arr[2]);
                    kh.seteMail(arr[3]);
                    kh.setDiaChi(arr[4]);
                    list.add(kh);
                }
            } catch (IOException ex) {
                System.out.println("Lỗi :" + ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi :" + ex.getMessage());
        }
    }

    public void menu_KH() {
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                            QUẢN LÝ KHÁCH HÀNG                              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        1: Xem danh sách khách hàng                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        2: Thêm mới khách hàng                              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        3: Sửa thông tin khách hàng                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        4: Xóa thông tin khách hàng                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        5: Tìm kiếm thông tin khách hàng                    |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        6: Thoát chức năng                                  |");
        System.out.println("+----------------------------------------------------------------------------+");
        int chon;
        do {
            save_khachhang();
            System.out.print("Nhập vào lựa chọn :");
            sc = new Scanner(System.in);
            chon = sc.nextInt();

            switch (chon) {

                case 1:
                    loadfile_KH();
                    System.err.println("Bạn đang chọn chức năng xem danh sách thông tin khách hàng :");
                    in_dskh();
                    menu_KH();
                    break;
                case 2:
                    System.err.println("Bạn đang chọn chức năng thêm mới khách hàng :");
                    add_khachhang();
                    menu_KH();
                    break;
                case 3:
                    System.err.println("Bạn đang chọn chức năng sửa thông tin khách hàng :");
                    sua_TTKH();
                    menu_KH();
                    break;
                case 4:
                    System.err.println("Bạn đang chọn chức năng xóa thông tin khách hàng :");
                    delete_KH();
                    menu_KH();
                    break;
                case 5:
                    System.err.println("Bạn đang chọn chức nawg tìm kiếm thông tin khách hàng");
                    tim_Kien_KH();
                    menu_KH();
                    break;
                case 6:
                    menu_chinh.menuChinh();
                    break;
                default:
                    System.err.println(">>>Lựa trọn không hợp lệ<<<");
            }
        } while (true);
    }
}
