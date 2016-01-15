/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.Util;

/**
 *
 * @author B
 */
public class StringQuery {

    /*
     *obj_name_column = ชื่อของ column ที่จะทำการลงข้อมูล
     * Table_name = ชื่อของ Table ที่จะลงขอ้มูล
     * Command = คำสั่ง sql ในที่นี้ใช้ where
     * index_obj_name_column = ทำการเลือกข้อมูล ชื่อcolumn ของตัวแปร obj_name_column
     */
    public String Insert_String(String[] obj_name_column, String Table_name) {
        String String_return = "INSERT INTO ";
        String_return += Table_name + "(";
        for (int i = 0; i < obj_name_column.length; i++) {
            if(i == (obj_name_column.length - 1)){
                String_return += obj_name_column[i] + ")";
            }else{
                String_return += obj_name_column[i] + ",";
            }
        }
        String_return += " VALUES(";
        for(int i = 0;i<obj_name_column.length;i++){
            if(i == (obj_name_column.length - 1)){
                String_return += "?)";
            }else{
                String_return += "?,";
            }
        }
        return String_return;
    }
    public String Update_String(String[] obj_name_column,String Table_name,String Command){
        String String_return = "UPDATE ";
        String_return += Table_name + " SET ";
        for (int i = 0; i < obj_name_column.length; i++) {
            if(i == (obj_name_column.length - 1)){
                String_return += obj_name_column[i] + "=? ";
            }else{
                String_return += obj_name_column[i] + "=?,";
            }
        }
        String_return += Command;
        return String_return;

    }
    public String Update_String(String[] obj_name_column,String Table_name,String Command,int[] index_obj_name_column){
        String String_return = "UPDATE ";
        String_return += Table_name+" SET ";
        for(int i =0 ; i< index_obj_name_column.length;i++){
            if(i == (index_obj_name_column.length - 1)){
                String_return += obj_name_column[index_obj_name_column[i]] + "=? ";
            }else{
                String_return += obj_name_column[index_obj_name_column[i]] + "=?,";
            }
        }
        String_return += Command;
        return String_return;
    }
    /*public String Flag_Status_String(String Coloum_flag_name,String Value_Status,String Table_Name,String Command){
        String String_return = "UPDATE ";
        String_return += Table_Name+" SET "+Coloum_flag_name+"="+Value_Status+" "+Command;
        return String_return;
    }*/
    public String Flag_Status_String(String SET_Command,String Table_name,String Command){
        String String_return = "UPDATE ";
        String_return += Table_name+" SET "+SET_Command+" "+Command;
        return String_return;
    }
    
}
