package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import com.db.DBConnect;
import java.sql.PreparedStatement;

public class UtiDatabase {

    public int numRowdatabase(String inputSQL) throws Exception//นับข้อมูลใน DataBase
    {

        int a = 0;
        DBConnect objcon = new DBConnect();
        Connection con = objcon.openNewConnection();
        ResultSet rs = con.createStatement().executeQuery(inputSQL);
        try {
            while (rs.next()) {
                a = rs.getInt("num");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return a;
    }
    public int numRowdatabase(String inputSQL,Connection con) throws Exception//นับข้อมูลใน DataBase
    {

        int a = 0;
        ResultSet rs = con.createStatement().executeQuery(inputSQL);
        try {
            while (rs.next()) {
                a = rs.getInt("num");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return a;
    }
///********************************************โชว์ Combobox แบบ ดึงมาจากฐานข้อมูล

    public String ShowSelectBox(String inputSQL, String Value, String ShowData, String HTMLIDandName) throws Exception {
        String output;
        DBConnect objcon = new DBConnect();
        Connection con = objcon.openNewConnection();
        ResultSet rs = con.createStatement().executeQuery(inputSQL);
        try {
            output = "<select name='" + HTMLIDandName + "'id='" + HTMLIDandName + "'>";
            while (rs.next()) {
                output += "<option value='" + rs.getString(Value) + "'>" + rs.getString(ShowData) + "</option>";
            }
            output += "</select>";
        } finally {
            try {
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }
    public String ShowSelectBox(String inputSQL, String Value, String ShowData, String HTMLIDandName,String othervalue,String othershow) throws Exception {
        String output;
        DBConnect objcon = new DBConnect();
        Connection con = objcon.openNewConnection();
        ResultSet rs = con.createStatement().executeQuery(inputSQL);
        try {
            output = "<select name='" + HTMLIDandName + "'id='" + HTMLIDandName + "'>";
            output += "<option value='" + othervalue + "'>" + othershow + "</option>";
            while (rs.next()) {
                output += "<option value='" + rs.getString(Value) + "'>" + rs.getString(ShowData) + "</option>";
            }
            output += "</select>";
        } finally {
            try {
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }
    //ใช้ตอนจะนำข้อมูลลงฐานข้อมูลข้อมูลจะเป็นแบบ new Line

    public String EncodeNewLine(String input_string) {
        return input_string.replaceAll("<br>", "\n");
    }
    //ใช้ตอนที่ดึงข้อมูลออกจากฐานข้อมูลแล้วทำใส่BR เพื่อใช้ใน Textarea

    public String DecodeNewLine(String input_string) {
        return input_string.replaceAll("\n", "<br>");
    }
    //ไม่ให้แสดงคำว่าNull

    public String NotNull(String input_str) {
        if (input_str == null) {
            input_str = "";
        }
        return input_str;
    }
    public String NotNull(String input_str,String number_value) {
        if (input_str == null || input_str.equals("")) {
            input_str = number_value;
        }
        return input_str;
    }
    ///********************************************โชว์ Combobox แบบ ดึงมาจากฐานข้อมูล(ใน Search_Config2)
//<editor-fold defaultstate="collapsed" desc="โชว์ Combobox แบบ ดึงมาจากฐานข้อมูล(ใน Search_Config2)">
    public String ShowSelectBoxSearch(String inputSQL, String Value, String ShowData, String HTMLIDandName) throws Exception {
        String output;
        DBConnect objcon = new DBConnect();
        Connection con = objcon.openNewConnection();
        ResultSet rs = con.createStatement().executeQuery(inputSQL);
        try {
            output = "<select name='" + HTMLIDandName + "'id='" + HTMLIDandName + "' onclick=SelectBoxSearch(\'" + Value + "\',\'" + HTMLIDandName + "\','stadment')>";
            while (rs.next()) {
                output += "<option value='" + rs.getString(Value) + "'>" + rs.getString(ShowData) + "</option>";
            }
            output += "</select>";
        } finally {
            try {
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }
    
    public String ShowSelectBoxSearch(String Select_value,String[] Fill_Name,String[] Value,String HTMLIDandName)throws Exception{
        String string_output = new String();
        try{
            string_output = "<select name='" + HTMLIDandName + "'id='" + HTMLIDandName + "' onclick=\"Click_Select_Search(this)\">";
            for(int i = 0 ; i< Fill_Name.length;i++){
                string_output += "<option "+(Value[i].equals(Select_value)?"selected":"")+" value='" + Value[i] + "' >" + Fill_Name[i] + "</option>";
            }
            string_output +="</select>";
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return string_output;
        }
    }
//</editor-fold>
    public String RunLine_no(String Table_name, String Doc_id) throws Exception {
        String Str_Line_no = "", SQL = "";
        DBConnect objcon = new DBConnect();
        Connection con = objcon.openNewConnection();
        SQL = "Select line_no from " + Table_name + " where doc_id = '" + Doc_id + "' and delete_flag = 'N'  order by to_number(line_no,'999') desc";
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {
                if (rs.isFirst()) {
                    Str_Line_no = Integer.toString(rs.getInt("line_no") + 1);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            return Str_Line_no;
        }
    }

    public String Format_Date_System(String inputDate) {
        return Integer.toString(Integer.parseInt(inputDate.substring(6, 10)) - 543) + "-" + inputDate.substring(3, 5) + "-" + inputDate.substring(0, 2);

    }

    public String End_Date(String Month, String Year) {
        String retrun_Date = "";
        if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
            retrun_Date = Year + "-" + Month + "-" + "30";
        } else if (Month.equals("02")) {
            if ((Integer.parseInt(Year) % 4) == 0) {
                retrun_Date = Year + "-" + Month + "-" + "29";
            } else {
                retrun_Date = Year + "-" + Month + "-" + "28";
            }
        } else {
            retrun_Date = Year + "-" + Month + "-" + "31";
        }
        return retrun_Date;
    }

    public String End_Date(String Month, String Year, String Location) {
        String retrun_Date = "";
        if (Location.equals("TH")) {
            if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                retrun_Date = "30-"+Month+"-"+(Integer.parseInt(Year)+543) ;
            } else if (Month.equals("02")) {
                if ((Integer.parseInt(Year) % 4) == 0) {
                    retrun_Date = "29-"+Month+"-"+(Integer.parseInt(Year)+543);
                } else {
                    retrun_Date = "28-"+Month+"-"+(Integer.parseInt(Year)+543);
                }
            } else {
                retrun_Date = "31-"+Month+"-"+(Integer.parseInt(Year)+543);
            }
            return retrun_Date;
        } else {
            if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
                retrun_Date = Year + "-" + Month + "-" + "30";
            } else if (Month.equals("02")) {
                if ((Integer.parseInt(Year) % 4) == 0) {
                    retrun_Date = Year + "-" + Month + "-" + "29";
                } else {
                    retrun_Date = Year + "-" + Month + "-" + "28";
                }
            } else {
                retrun_Date = Year + "-" + Month + "-" + "31";
            }
        }
        return retrun_Date;
    }
    public void Return_Document(String Doc_id,String Column_Return_Count,String[] Return_Table_name,String[] Delete_Table_name)throws Exception{
        Connection con = new DBConnect().openNewConnection();
        PreparedStatement p = null;
        String SQL = "";
        try{
            for(int i = 0;i<Return_Table_name.length;i++){
                SQL = "UPDATE "+Return_Table_name[i]+" SET complete_flag = 'N'  where doc_id = '"+Doc_id+"' and complete_flag = 'Y' and delete_flag = 'N'";
                p = con.prepareStatement(SQL);
                p.executeUpdate();
                p.clearParameters();
            }
            for(int i = 0;i<Delete_Table_name.length;i++){
                SQL = "DELETE FROM "+Delete_Table_name[i]+" WHERE doc_id = '"+Doc_id+"'";
                p = con.prepareStatement(SQL);
                p.executeUpdate();
                p.clearParameters();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(p != null){
                p.close();
            }
            if(con != null){
                con.close();
            }
        }

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ทำการส่งเอกสารกลับคืนTableแม่และลบข้อมูลในTableลูก">
    public void Return_Document(String Doc_id,String[] Return_Table_name,String[] Delete_Table_name)throws Exception{
        Connection con = new DBConnect().openNewConnection();
        PreparedStatement p = null;
        String SQL = "";
        try{
            for(int i = 0;i<Return_Table_name.length;i++){
                SQL = "UPDATE "+Return_Table_name[i]+" SET complete_flag = 'N'  where doc_id = '"+Doc_id+"' and complete_flag = 'Y' and delete_flag = 'N'";
                p = con.prepareStatement(SQL);
                p.executeUpdate();
                p.clearParameters();
            }
            for(int i = 0;i<Delete_Table_name.length;i++){
                SQL = "DELETE FROM "+Delete_Table_name[i]+" WHERE doc_id = '"+Doc_id+"'";
                p = con.prepareStatement(SQL);
                p.executeUpdate();
                p.clearParameters();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(p != null){
                p.close();
            }
            if(con != null){
                con.close();
            }
        }

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ไว้นับจำนวนการส่งข้อมูลที่อนุมัติที่ถูกต้อง">
    public String Count_sendcomplete(String Table_name, String Doc_id, String Complete_flag, Connection con) throws Exception {
        ResultSet rs = null;
        String SQL = "", Return = "0";
        //UtiDatabase objuti = new UtiDatabase();
        try {
            if (Complete_flag.equalsIgnoreCase("Y")) {
                SQL = "select count(doc_id) as num from " + Table_name + " where doc_id = '" + Doc_id + "' and delete_flag = 'N' and complete_flag = 'N'";
                if (numRowdatabase(SQL) != 0) {
                    SQL = "select count_send_complete from " + Table_name + " where doc_id = '" + Doc_id + "' and delete_flag = 'N' and complete_flag = 'N'";
                    rs = con.createStatement().executeQuery(SQL);
                    while (rs.next()) {
                        Return = Integer.toString(Integer.parseInt(rs.getString("count_send_complete")) + 1);
                    }

                }else{
                    Return = "1";
                }
            }else{
               SQL = "select count(doc_id) as num from " + Table_name + " where doc_id = '" + Doc_id + "' and delete_flag = 'N' and complete_flag = 'N'";
                if (numRowdatabase(SQL) != 0) {
                    SQL = "select count_send_complete from " + Table_name + " where doc_id = '" + Doc_id + "' and delete_flag = 'N' and complete_flag = 'N'";
                    rs = con.createStatement().executeQuery(SQL);
                    while (rs.next()) {
                        Return = rs.getString("count_send_complete");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                rs.close();
            }
            return Return;
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ทำการตวจสอบข้อมูลเลขรหัสของเลขที่เอกสารว่ามีการใช้งานหรือไม่">
    public int Return_Check_Doc_id(String Table,String Column_name,String Value)throws Exception{
        //****************//
        /*
         * Return_status ค่าต่างๆ
         * 0 เป็นค่าเริ่มต้นของระบบ
         * 1 สามารถใช้งานรหัสนี้ได้
         * 2 มีข้อมูลแล้วอาจทำให้ทับข้อมูลเดิมได้
         * 3 มีข้อมูลที่เป็นเอกสารสมบูรณ์แล้ว
         */
        //****************//
        String SQL = "Select count("+Column_name+") as num from "+Table+" Where "+Column_name+" = '"+Value+"' and delete_flag = 'N' and complete_flag = 'N'";
        int Return_status = 0;
        try{
            if(numRowdatabase(SQL) == 0){
                SQL = "Select count("+Column_name+") as num from "+Table+" Where "+Column_name+" = '"+Value+"' and delete_flag = 'Y' and complete_flag = 'N'";
                Return_status = (numRowdatabase(SQL) == 0) ? 1:3;
            }else{
                Return_status = 2;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return Return_status;
        }
    }
    //</editor-fold>
    
}
