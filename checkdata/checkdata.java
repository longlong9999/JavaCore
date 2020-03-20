/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkdata;

/**
 *
 * @author ASUS
 */
public class checkdata {
    public String chuanhoaten(String ten){
        String arr[ ]= ten.split(" ");
        String noichuoi="";
        String chuanhoa="";
        for (int i = 0; i < arr.length; i++) {
            String ten1= arr[i].substring(0,1);
            String ten2= arr[i].substring(1,arr[i].length());
            String ten3= ten1.toUpperCase();
            String ten4= ten3.concat(ten2);
            noichuoi= noichuoi+ " "+ten4;
             chuanhoa=noichuoi.trim();
        }
        return chuanhoa;
    }
    
    public boolean check_ten(String hoTen){
        boolean check = false ;
        for (int i = 0; i < 10; i++) {
            if(hoTen.contains(i+"")==true|| hoTen.equalsIgnoreCase("")){
                System.err.println(">>>Tên nhân viên không được chưa số và không được để trống <<<!");
                check = true;
            }
        }
        return check;
    }
    public boolean check_sdt(String sDT){
        boolean check = false;
        try {
            int soDienThoai = Integer.parseInt(sDT);
            if(sDT.startsWith(0+"")==false){
                System.err.println(">>>Số điện thoại bắt đầu bằng chữ số 0<<<");
                check = true;
                
            }else if(sDT.length()<10){
                System.err.println(">>>thoại phải lớn hơn 10 số<<<");
                check=true;
                
            }else if(sDT.length()>11){
                System.err.println(">>>Số điện thoại phải nhở hơn hoặc bằng 11 chữ số<<<");
                check=true;
            }
        } catch(NumberFormatException e){
            System.err.println(">>>Số điện thoại phải được nhập bằng số<<<");
            check=true;
        } catch (Exception e) {
            System.err.println("Lỗi :"+e.getMessage());
            check = true;
        }
        return check;
    }
    public boolean check_email(String eMail){
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = eMail.matches(dinhDangEmail);
        if (ktEmail == false) {
            System.err.println(">>>Email sai, nhập lại theo dạng abc@domain.com<<<");
            return true;
        }
        return false;
    }
    public boolean check_SoLuong(int soLuong){
        boolean check = false;
        if(soLuong<=0){
            System.err.println(">>>Số lượng sản phẩm phải lớn hơn 0<<<");
            check=true;
        }
        return check;
    }
    public boolean check_DonGia(int donGia){
        boolean check= false;
        if(donGia<=0){
            System.err.println(">>>Giá của sản phẩm phài lớn hơn 0<<<");
            check = true;
        }
        return check;
    }
}
