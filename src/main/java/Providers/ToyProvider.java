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
    
    public static ArrayList<mResponseMessage> GetResponseMessageList(int form_id, int form_type_id){
        ArrayList<mResponseMessage> responseMessageList = new ArrayList<mResponseMessage>();
        
        String queryStatement = "select form_department_id from form_department where form_id=? and form_type_id=?";
        int form_department_id = 0;
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ps.setInt(2, form_type_id);
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
               form_department_id = queryResult.getInt("form_department_id");
                           
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //---------------
        responseMessageList = GetResponseMessageList_ByForm_department_id(form_id, form_department_id);
        
        return responseMessageList;
    }
    
    public static ArrayList<mResponseMessage> GetResponseMessageList_ByForm_department_id(int form_id, int form_department_id){
        
        ArrayList<mResponseMessage> responseMessageList = new ArrayList<mResponseMessage>();
        
        String queryStatement = "select * from response_message where form_id=? and form_department_id=?";
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ps.setInt(2, form_department_id);
            ResultSet queryResult = ps.executeQuery();
            
            while(queryResult.next()){            
               mResponseMessage responseMessage = new mResponseMessage();
               
               responseMessage.setResponse_message_id(queryResult.getInt("response_message_id"));
               responseMessage.setForm_id(queryResult.getInt("form_id"));
               responseMessage.setForm_department_id(queryResult.getInt("form_department_id"));
               responseMessage.setMessage(queryResult.getString("message"));
               responseMessage.setCreated_date(queryResult.getDate("created_date"));
               responseMessage.setCreated_by(queryResult.getInt("created_by"));
               responseMessage.setUpdated_date(queryResult.getDate("updated_date"));
               responseMessage.setUpdated_by(queryResult.getInt("updated_by"));
               
               responseMessageList.add(responseMessage);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return responseMessageList;
    }
    
    public static mResult AddResponseMessage(int user_id,int form_id, int form_department_id, String message){
        mResult result = new mResult();
        
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
            
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
                  
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        result.setIsSuccess(true);
        result.setMessage("Added successfully");
        return result;
    }
    

    public static mResult ApprovedForm(int user_id,int responsible_form_type_id, int form_id) throws SQLException{
        mResult result = new mResult();
        
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
        result.setMessage("Approved successfully");
        return result;
    }
    
    public static mResult RejectedForm(int user_id,int responsible_form_type_id, int form_id) throws SQLException{
        mResult result = new mResult();
        
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
   
           ResultSet queryResult = ps.executeQuery();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        result.setIsSuccess(true);
        result.setMessage("Rejected successfully");
        
        return result;
    }
    
    public static mResult DeleteFormByID(int form_id) throws SQLException{
        mResult result = new mResult();
        
        String queryStatement = "delete form where form_id=?";
     
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            
            ResultSet queryResult = ps.executeQuery();

            while(queryResult.next()){            
               
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    
        result.setIsSuccess(true);
        result.setMessage("Deleted successfully");
        
        return result;
    }
    
    public static mFormDepartment GetFormDetails_IT(int form_id){
        mFormDepartment formDepartment = new mFormDepartment();
        
        String queryStatement = "select form_department_id from form_department where form_id=? and form_type_id=?";
        int form_department_id = 0;
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
               form_department_id = queryResult.getInt("form_department_id");
                           
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //---------------
        formDepartment.setRequestedItemList(GetFormDetails_requestedItemList(form_id));
        return formDepartment;
    }
    
    public static List<mRequestedItem> GetFormDetails_requestedItemList(int form_id){
        ArrayList<mRequestedItem> requestedItemList = new ArrayList<mRequestedItem>();
        
        String queryStatement = "select form_department_id from form_department where form_id=? and form_type_id=?";
        int form_department_id = 0;
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
               form_department_id = queryResult.getInt("form_department_id");
                           
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //---------------
        return requestedItemList;
    }
    
}
