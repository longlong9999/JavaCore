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
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.NhanVien;

/**
 *
 * @author ASUS
 */
public class QuanLyNhanVien {

    NhanVien nv = new NhanVien();
    ArrayList<NhanVien> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    menuchinh menuchinh = new menuchinh();
    checkdata check = new checkdata();
    int index;

    public void in_danhsach() {
        loadfile();
        if (list.isEmpty()) {
            System.err.println(">>>Danh sách rỗng<<<");
        } else {
            System.out.println("+-------------------------------------------------------------------------+");
            System.out.println("|                            Danh Sách Nhân Viên                          |");
            System.out.println("+------+------------------+-------------+----------------------+----------+");
            System.out.println("|  ID  |    Họ và tên     |Số điện thoại|         Email        | Địa chỉ  |");
            System.out.println("+------+------------------+-------------+----------------------+----------+");

            for (int i = 0; i < list.size(); i++) {
                list.get(i).inTT();
            }
        }

    }

    public void add_nhanvien() {
        String id;
        String ten;
        do {
            System.out.print("\t Nhập vào ID nhân viên :");
            sc = new Scanner(System.in);
            nv.setiD(sc.nextLine());
            id = nv.getiD();
            if (!check_exists(id)) {
                do {
                    System.out.print("\t Nhập vào họ và tên nhân viên :");
                    sc = new Scanner(System.in);
                    ten = sc.nextLine();
                    if (ten.equals("")) {
                        System.err.println(">>>Không được để tên trống!<<<");
                    } else {
                        ten = check.chuanhoaten(ten);
                        nv.setHoTen(ten);
//                        check.check_ten(nv.getHoTen());

                    }

                } while (ten.equals("") || check.check_ten(nv.getHoTen()));

                do {
                    System.out.print("\t Nhập vào số điện thoại :");
                    nv.setsDT(sc.nextLine());

                } while (check.check_sdt(nv.getsDT()));
                do {
                    String mk="";
                    System.out.print("\t Nhập vào mật khẩu :");
                    sc= new Scanner(System.in);
                    mk=sc.nextLine();
                    if (mk.equals("")) {
                        System.err.println(">>>Mật khẩu không được để khoảng trống<<<");
                    }
                    nv.setPasswords(md5(mk));
//                    System.out.println(nv.getPasswords());
                } while (nv.getPasswords().equals(""));
                do {
                    System.out.print("\t Nhập vào emai :");
                    nv.seteMail(sc.nextLine());

                } while (check.check_email(nv.geteMail()));
                System.out.print("\t Nhập vào địa chỉ :");
                nv.setDiaChi(sc.nextLine());
                list.add(nv);
                System.err.println("Thêm mới thành công!");
            } else {
                System.err.println(">>>Nhân viên đã tồn tại ! Thêm mới nhân viên thất bại<<<");
            }
        } while (!check_exists(id));
        save();
    }

    public boolean check_exists(String iD) {
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            if (iD.equalsIgnoreCase(list.get(i).getiD())) {
                check = true;
                index = i;
                break;
            }

        }
        return check;
    }

    public boolean search() {
        boolean check = false;
        System.out.print("\t Nhập vào mã nhân viên cần tìm :");
        sc = new Scanner(System.in);
        String iD = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            nv = list.get(i);
            if (iD.equalsIgnoreCase(nv.getiD())) {
                check = true;
                index = i;
                break;
            }
        }
        return check;
    }

    public void delete_nhanvien() {

        NhanVien nv = new NhanVien();

        System.out.print("Nhập vào mã nhân viên cần tìm :");
        sc = new Scanner(System.in);
        String iD = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            nv = list.get(i);
            if (iD.equalsIgnoreCase(nv.getiD())) {
                index = i;
            }
        }
        if (nv == null || !iD.equalsIgnoreCase(nv.getiD())) {
            System.err.println(">>>Không tìm thấy nhân viên!<<<");
        } else {
            list.remove(nv);
            System.err.println("Xóa nhân viên thành công");
        }
        save();
    }

    public void sua_TT() {
        NhanVien nv = new NhanVien();
        int dem=0;
        System.out.print("Nhập vào mã nhân viên cần tìm :");
        sc = new Scanner(System.in);
        String iD = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            nv = list.get(i);
            if (iD.equalsIgnoreCase(nv.getiD())) {

                index = i;
                break;
            }
        }
        if (nv == null || !iD.equalsIgnoreCase(nv.getiD())) {
            System.err.println(">>>Không tìm thấy nhân viên!<<<");
        } else {
            NhanVien nv1 = new NhanVien();
            System.out.println("Cập nhật thông tin nhân viên :");

            System.out.println("Mã nhân viên :" + nv.getiD());
            nv1.setiD(nv.getiD());
            System.out.println("Họ và tên cũ của nhân viên :" + nv.getHoTen());

            do {

                System.out.print("Nhập vào họ tên nhân viên mới :");
                nv1.setHoTen(sc.nextLine());
//            System.out.println(nv1.getHoTen());
                if (nv1.getHoTen().equals("")) {
                    nv1.setHoTen(nv.getHoTen());

                }
//            System.out.println(nv1.getHoTen());
                check.chuanhoaten(nv1.getHoTen());
//            System.out.println(nv1.getHoTen());
            } while (check.check_ten(nv1.getHoTen()));

            System.out.println("Số điện thoại cũ của nhân viên :" + nv.getsDT());
            do {
                System.out.print("Nhập vào số điện thoại mới của nhân viên :");
                sc = new Scanner(System.in);
                nv1.setsDT(sc.nextLine());
                if (nv1.getsDT().equals("")) {
                    nv1.setsDT(nv.getsDT());
                }

            } while (check.check_sdt(nv1.getsDT()));

            System.out.println("Mật khẩu cũ :" + nv.getPasswords());
            String mk = "";

            System.out.print("Nhập vào mật khẩu mới :");
            nv1.setPasswords(sc.nextLine());
            if (nv1.getPasswords().equals("")) {
                nv1.setPasswords(nv.getPasswords());
            } 
            else {
                do {
                    
                    System.out.print("Nhập lại mật khẩu mới :");
                    sc = new Scanner(System.in);
                    mk = sc.nextLine();

                    if (mk.equals(nv1.getPasswords())) {
                        nv1.setPasswords(md5(mk));
                        dem++;
                    } else if(dem==0) {
                        System.err.println(">>>Mật khẩu nhập lại không khớp mật khẩu mới!<<<");
                    }
                } while (dem==0);
            }

            System.out.println("Địa chỉ Email cũ :" + nv.geteMail());

            do {
                System.out.print("Nhập vào Email mới của nhân viên :");
                nv1.seteMail(sc.nextLine());
                if (nv1.geteMail().equals("")) {
                    nv1.seteMail(nv.geteMail());
                }

            } while (check.check_email(nv1.geteMail()));

            System.out.println("Địa chỉ cũ :" + nv.getDiaChi());
            System.out.print("nhập vào địa chỉ mới :");
            nv1.setDiaChi(sc.nextLine());
            if (nv1.getDiaChi().equals("")) {
                nv1.setDiaChi(nv.getDiaChi());
            }
            list.set(index, nv1);
            System.err.println("Sửa thông tin nhân viên thành công!");
        }
        save();

    }

    public void timKiem_TT() {
        System.out.print("Nhập vào thông tin nhân viên cần tìm kiếm :");
        int dem = 0;
        sc = new Scanner(System.in);
        String input = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (input.contains(list.get(i).getHoTen()) || input.contains(list.get(i).getiD()) || input.contains(list.get(i).getsDT()) || input.contains(list.get(i).getsDT())) {
                 System.out.println("+------+------------------+-------------+----------------------+----------+");
                list.get(i).inTT();
                dem++;
            }

        }
        if (dem == 0) {
            System.err.println(">>>Không tìm thấy nhân viên!<<<");
        }
    }

    public NhanVien search_nhanvien() {
        NhanVien nv = new NhanVien();

        System.out.print("Nhập vào mã nhân viên cần tìm :");
        sc = new Scanner(System.in);
        String iD = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            nv = list.get(i);
            if (iD.equalsIgnoreCase(nv.getiD())) {

                index = i;
                break;
            }
        }
        if (nv == null || !iD.equalsIgnoreCase(nv.getiD())) {
            System.err.println(">>>Không tìm thấy nhân viên!<<<");
        }

        return nv;
    }

    public void save() {
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            for (int i = 0; i < list.size(); i++) {
                String row = "";
                row = row + list.get(i).getiD() + "\t";
                row = row + list.get(i).getHoTen() + "\t";
                row = row + list.get(i).getsDT() + "\t";
                row = row + list.get(i).getPasswords() + "\t";
                row = row + list.get(i).geteMail() + "\t";
                row = row + list.get(i).getDiaChi() + "\n";
                data = data + row;
            }
            fw = new FileWriter("nhanvien.txt");
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            System.out.println("lỗi :" + ex.getMessage());
        }
    }

    public void loadfile() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            list = new ArrayList<>();
            fr = new FileReader("nhanvien.txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    NhanVien nv = new NhanVien();
                    nv.setiD(arr[0]);
                    nv.setHoTen(arr[1]);
                    nv.setsDT(arr[2]);
                    nv.setPasswords(arr[3]);
                    nv.seteMail(arr[4]);
                    nv.setDiaChi(arr[5]);

                    list.add(nv);
                }
            } catch (IOException ex) {
                System.out.println("Fail :" + ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fail :" + ex.getMessage());
        }

    }

    public void menu_nhanvien() {
        
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                            QUẢN LÝ NHÂN VIÊN                               |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                         1: Xem danh sách nhân viên                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                         2: Thêm mới nhân viên                              |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                         3: Sửa thông tin nhân viên                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                         4: Xóa thông tin nhân viên                         |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                         5: Tìm kiếm thông tin nhân viên                    |");
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                         6: Thoát chức năng                                 |");
        System.out.println("+----------------------------------------------------------------------------+");

        int chon;

        do {
            save();
            System.out.print("Nhập vào lựa chọn :");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    loadfile();
                    System.err.println("Bạn đang chọn chức năng xem danh sách nhân viên :");
                    in_danhsach();
                    menu_nhanvien();

                    break;
                case 2:
                    System.err.println("Bạn đang chọn chứ năng thêm mới nhân viên :");
                    add_nhanvien();
                    menu_nhanvien();

                    break;
                case 3:
                    System.err.println("Bạn đang chọn chức năng sửa thông tin nhân viên :");
                    sua_TT();
                    
                    menu_nhanvien();

                    break;
                case 4:
                    System.err.println("Bạn đang chọn chức năng xóa thông tin nhân viên :");
                    delete_nhanvien();
                    menu_nhanvien();

                    break;
                case 5:
                    System.err.println("Bạn đang cọn chức năng tìm kiếm thông tin nhân viên nhân viên");
                    timKiem_TT();
                    menu_nhanvien();
                    break;
                case 6:
                    menuchinh.menuChinh();
                    break;
                default:
                    System.err.println(">>>Không hợp lệ<<<");
            }
        } while (true);
    }

    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
