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
import jdk.jfr.events.FileWriteEvent;
import object.SanPham;

/**
 *
 * @author ASUS
 */
public class QuanLySanPham {

    ArrayList<SanPham> list = new ArrayList<>();
    SanPham sp = new SanPham();
    checkdata check = new checkdata();
    int index = 0;
    Scanner sc = new Scanner(System.in);

    public boolean check_exists(String maSP) {
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            if (maSP.equalsIgnoreCase(list.get(i).getMaSP())) {
                check = true;
                index = i;
                break;
            }
        }
        return check;
    }

    public void in_DSSP() {

        loadfile_SP();
        if (list.isEmpty()) {
            System.err.println(">>>Danh sách rỗng!<<<");
        } else {
            System.out.println("+----------------------------------------------------------------+");
            System.out.println("|                        DANH SÁCH SẢN PHẨM                      |");
            System.out.println("+------+---------------------+-------------+---------------------+");
            System.out.println("|  ID  |    Tên sản phẩm     |   Số lượng  |        Đơn giá      |");
            System.out.println("+------+---------------------+-------------+---------------------+");

            for (int i = 0; i < list.size(); i++) {
                list.get(i).in_TTSP();
            }
        }
    }

    public void add_SP() {

        String ma_SP = "";
        String ten_Sp = "";
        SanPham sp = new SanPham();
        do {
            System.out.print("Nhập vào mã sản phẩm :");
            sc = new Scanner(System.in);
            sp.setMaSP(sc.nextLine());
            ma_SP = sp.getMaSP();
            if (!check_exists(ma_SP)) {
                do {
                    System.out.print("Nhập vào tên sản phầm :");
                    ten_Sp = sc.nextLine();
                    if (ten_Sp.equals("")) {
                        System.err.println(">>>Tên sản phẩm không được để trống!<<<");

                    } else {
                        ten_Sp=check.chuanhoaten(ten_Sp);
                        sp.setTenSP(ten_Sp);
//                        check.check_ten(sp.getTenSP());
                    }
                } while (ten_Sp.equals("") );
                do {
                    System.out.print("Nhập vào số lượng :");
                    sp.setSoLuong(Integer.parseInt(sc.nextLine()));

                } while (check.check_SoLuong(sp.getSoLuong()));
                do {
                    System.out.print("Nhập vào giá của sản phẩm :");
                    sp.setDonGia(Integer.parseInt(sc.nextLine()));
                } while (check.check_DonGia(sp.getDonGia()));
                list.add(sp);
                save_SP();
                System.err.println("Thêm thành công sản phẩm !");
            } else {
                System.err.println(">>>Sản phẩm đã tồn tại!<<<");
            }
        } while (!check_exists(ma_SP));

    }

    public void sua_TTSP() {
        SanPham sp = new SanPham();
        String maSP = "";
        System.out.print("Nhập vào mã sản phẩm cần sửa thông tin :");
        sc= new Scanner(System.in);
        maSP = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            sp = list.get(i);
            if (maSP.equalsIgnoreCase(list.get(i).getMaSP())) {
                index = i;
                break;
            }

        }
        if (sp == null || !maSP.equalsIgnoreCase(sp.getMaSP())) {
            System.err.println(">>>Không tìm thấy sản phẩm !<<<");
        } else {
            SanPham sp1 = new SanPham();
            System.out.println("Cập nhật thông tin sản phẩm :");
            sp1.setMaSP(sp.getMaSP());
            System.out.println("Tên sản phẩm cũ :" + sp.getTenSP());

            
                System.out.print("Nhập vào tên sản phẩm mới :");
                sp1.setTenSP(sc.nextLine());
                if (sp1.getTenSP().equals("")) {
                    sp1.setTenSP(sp.getTenSP());
                } else {
                    check.chuanhoaten(sp1.getTenSP());
                }
            

            System.out.println("Số lượng cũ của sản phẩm :" + sp.getSoLuong());

            do {
                System.out.print("Nhập vào số lượng mới của sản phẩm :");
                sp1.setSoLuong(Integer.parseInt(sc.nextLine()));
                if (sp1.getSoLuong() ==0) {
                    sp1.setSoLuong(sp.getSoLuong());
                }
            } while (check.check_SoLuong(sp1.getSoLuong()));

            System.out.println("Đơn giá cũ của sản phẩm :" + sp.getDonGia());
            do {
                System.out.print("Nhập vào đơn giá mới của sản phẩm :");
                sp1.setDonGia(Integer.parseInt(sc.nextLine()));
                if (sp1.getDonGia() == 0) {
                    sp1.setDonGia(sp.getDonGia());
                }
            } while (check.check_DonGia(sp1.getDonGia()));
            list.set(index, sp1);
            System.err.println("Sửa thành công sản phẩm");
        }
        save_SP();
    }

    public void delete_SP() {
        SanPham sp = new SanPham();

        System.out.print("Nhập vào mã sản phầm cần tìm :");
        sc = new Scanner(System.in);
        String maSP = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            sp = list.get(i);
            if (maSP.equalsIgnoreCase(list.get(i).getMaSP())) {
                index = i;
                break;
            }
        }
        if (sp == null || !maSP.equalsIgnoreCase(sp.getMaSP())) {
            System.err.println(">>>Không tìm thấy nhân viên !<<<");
        } else {
            list.remove(sp);
            System.err.println("Xóa thành công sản phẩm!");
        }
        save_SP();
    }

    public void timKiem_SP() {
        String input = "";
        int dem = 0;
        System.out.print("Nhập vào thông tin cần tìm kiếm :");
        sc = new Scanner(System.in);
        input = sc.nextLine();

        for (int i = 0; i < list.size(); i++) {
            if (input.contains(list.get(i).getMaSP()) || input.contains(list.get(i).getTenSP())) {
                System.out.println("+------+---------------------+-------------+---------------------+");
                list.get(i).in_TTSP();
                dem++;
            }

        }
        if (dem == 0) {
            System.err.println(">>>Không tìm thấy nhân viên <<<");
        }
    }

    public void save_SP() {
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            for (int i = 0; i < list.size(); i++) {
                String row = "";
                row = row + list.get(i).getMaSP() + "\t";
                row = row + list.get(i).getTenSP() + "\t";
                row = row + list.get(i).getSoLuong() + "\t";
                row = row + list.get(i).getDonGia() + "\n";
                data = data + row;
            }
            fw = new FileWriter("sanpham.txt");
            bw = new BufferedWriter(fw);

            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Lỗi :" + ex.getMessage());
        }

    }

    public void loadfile_SP() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            list = new ArrayList<>();
            fr = new FileReader("sanpham.txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    SanPham sp = new SanPham();
                    sp.setMaSP(arr[0]);
                    sp.setTenSP(arr[1]);
                    sp.setSoLuong(Integer.parseInt(arr[2]));
                    sp.setDonGia(Integer.parseInt(arr[3]));
                    list.add(sp);

                }
            } catch (IOException ex) {
                System.out.println("Lỗi :" + ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Lỗi :" + ex.getMessage());
        }
    }

    public void menu_SP() {
        menuchinh menu_chinh = new menuchinh();
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                            QUẢN LÝ SẢN PHẨM                                |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        1: Xem danh sách sản phẩm                           |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        2: Thêm mới sản phẩm                                |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        3: Sửa thông tin sản phẩm                           |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        4: Xóa thông tin sản phẩm                           |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        5: Tìm kiếm thông tin sản phẩm                      |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                        6: Thoát chức năng                                  |");
        System.out.println("+----------------------------------------------------------------------------+");
        int chon;
        do {
            System.out.print("Nhập vào lựa chọn :");
            sc = new Scanner(System.in);
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    loadfile_SP();
                    System.err.println("Bạn đang chọn chức năng hiển thị danh sách sản phẩm :");
                    in_DSSP();
                    menu_SP();
                    break;
                case 2:
                    System.err.println("Bạn đang chọn chức năng thêm thông tin sản phẩm :");
                    add_SP();
                    save_SP();
                    menu_SP();
                    break;
                case 3:
                    System.err.println("Bạn đang chọn chức năng tìm kiếm thông tin sản phẩm :");
                    sua_TTSP();
                    menu_SP();
                    break;
                case 4:
                    System.err.println("Bạn đang chọn chức năng xóa thông tin sản phẩm :");
                    delete_SP();
                    menu_SP();
                    break;
                case 5:
                    System.err.println("Bạn đang chọn chức năng tìm kiếm thông tin sản phẩm :");
                    timKiem_SP();
                    menu_SP();
                    break;
                case 6:
                    menu_chinh.menuChinh();
                    break;
                default:
                    System.err.println(">>>Không hợp lệ!<<<");
            }
        } while (true);
    }
}
