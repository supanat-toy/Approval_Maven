/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Providers;

import Models.*;
import DBConnection.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author supanattechasothon
 */
public class ToyProvider {
    
    public static mForm GetResponseMessageList(int form_id, int form_type_id){
        mForm form = new mForm();
        
        String queryStatement = "select form_department_id from form_department where form_id=? and form_type_id=?";
        int form_department_id = 0;
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ps.setInt(2, form_type_id);
            ResultSet result = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(result.next()){            
               form_department_id = result.getInt("form_department_id");
                           
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //---------------
        queryStatement = "select * from response_message where form_id=? and form_department_id=?";
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ps.setInt(2, form_department_id);
            ResultSet result = ps.executeQuery();

            ArrayList<mResponseMessage> responseMessageList = new ArrayList<mResponseMessage>();
            
            
            
            while(result.next()){            
               mResponseMessage responseMessage = new mResponseMessage();
               responseMessage.setForm_id(result.getInt("form_id"));
                           
               responseMessageList.add(responseMessage);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        return form;
    }
    
    
    public static mForm AddResponseMessage(int user_id,int form_id, int form_department_id, String message){
        mForm form = new mForm();
        
        String queryStatement = "INSERT INTO response_message(form_id,form_department_id,message,created_date,created_by,updated_date,updated_by) VALUES (?, ?, ?, ?, ?,?,?)";
     
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ps.setInt(2, form_department_id);
            ps.setString(3, message);
            ps.setDate(4, (java.sql.Date) new Date());
            ps.setInt(5, 0);
            ps.setDate(6, (java.sql.Date) new Date());
            ps.setInt(7, 0);
            
            ResultSet result = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(result.next()){            
                  
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return form;
    }
    
<<<<<<< HEAD
    public static mResult ApprovedForm(int user_id,int responsible_form_type_id, int form_id) throws SQLException{
        mResult result = new mResult();
=======
    public static mForm ApprovedForm(int user_id,int responsible_form_type_id, int form_id) throws SQLException{
        mForm form = new mForm();
>>>>>>> db0deeee8193b4cb7b0ea42f8ed42b8885316877
        
        String queryStatement = "select * from form where form_id=?";
     
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
               
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
        PreparedStatement ps = null;
        if (responsible_form_type_id == 3){ // approve by supervisor
            queryStatement = "update form set   is_approved_supervisor = ? and "
                    + "                         approved_date_supervisor = ? and  "
                    + "                         approved_by_supervisor = ?"
                    + "                         where form_id= ? ";
            
            ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setBoolean(1, true);
            ps.setInt(2, user_id);
            ps.setDate(form_id, (java.sql.Date) new Date());
        }
        else if(responsible_form_type_id == 4){ // approve by admin
            queryStatement = "update form set   is_approved_admin = ? and "
                    + "                         approved_date_admin = ? and  "
                    + "                         approved_by_admin = ?"
                    + "                         where form_id= ? ";
            
            ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setBoolean(1, true);
            ps.setInt(2, user_id);
            ps.setDate(form_id, (java.sql.Date) new Date());
        }   
            
            ResultSet queryResult = ps.executeQuery();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        result.setIsSuccess(true);
        result.setMessage("Approve successfully");
        return result;
    }
    
    public static mForm RejectedForm(int user_id,int responsible_form_type_id, int form_id) throws SQLException{
        mForm form = new mForm();
        
        String queryStatement = "select * from form where form_id=?";
     
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            
            ResultSet result = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(result.next()){            
               
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
        PreparedStatement ps = null;
        if (responsible_form_type_id == 3){ // approve by supervisor
            queryStatement = "update form set   is_approved_supervisor = ? and "
                    + "                         approved_date_supervisor = ? and  "
                    + "                         approved_by_supervisor = ?"
                    + "                         where form_id= ? ";
            
            ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setBoolean(1, false);
            ps.setInt(2, user_id);
            ps.setDate(form_id, (java.sql.Date) new Date());
        }
        else if(responsible_form_type_id == 4){ // approve by admin
            queryStatement = "update form set   is_approved_admin = ? and "
                    + "                         approved_date_admin = ? and  "
                    + "                         approved_by_admin = ?"
                    + "                         where form_id= ? ";
            
            ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setBoolean(1, false);
            ps.setInt(2, user_id);
            ps.setDate(form_id, (java.sql.Date) new Date());
        }
   
            ResultSet result = ps.executeQuery();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return form;
    }
    
    public static mForm DeleteFormByID(int form_id) throws SQLException{
        mForm form = new mForm();
        
        String queryStatement = "delete form where form_id=?";
     
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            
            ResultSet result = ps.executeQuery();

            while(result.next()){            
               
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return form;
    }
    
    
    
}
