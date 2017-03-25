/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Providers;

import DBConnection.DBUtils;
import Models.mForm;
import Models.mFormDepartment;
import Models.mRequestedItem;
import Models.mResponseMessage;
import Models.mResult;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author supanattechasothon
 */
public class DetailsProvider {
    
    public static final int COORDINATOR = 1, SUPERVISOR = 2, ADMIN = 3, PROPERTIES = 4, TECHNICAL = 5, SOUNDANDLIGHT = 6, ARTSANDCULTURE = 7, SECURITY = 8, IT = 9;
    
    
    public static mForm getFormDetails(int form_id){
        mForm form = new mForm();
        try {
            ResultSet result = DBUtils.getPreparedStatement("select * from form where form.form_id = " + form_id).executeQuery();
            while (result.next()) {
                form.setEvent_name(result.getString("event_name"));
                form.setActivity(result.getString("activity"));
                form.setDepartment(result.getString("department"));
                form.setCampus(result.getString("campus"));
                form.setFacility(result.getString("facility"));
                form.setPreparing_date(result.getDate("preparing_date"));
                form.setStarting_date(result.getDate("starting_date"));
                form.setCoordinator_name(result.getString("coordinator_name"));
                form.setCoordinator_phone_number(result.getString("coordinator_phone_number"));
                form.setDescription(result.getString("description"));
                form.setCreated_date(result.getDate("created_date"));
                form.setCreated_by(result.getInt("created_by"));
                form.setUpdated_date(result.getDate("updated_date"));
                form.setUpdated_by(result.getInt("updated_by"));
                form.setApproved_date_supervisor(result.getDate("approved_date_supervisor"));
                form.setApproved_by_supervisor(result.getInt("approved_by_supervisor"));
                form.setIs_approved_supervisor(result.getBoolean("is_approved_supervisor"));
                form.setApproved_date_admin(result.getDate("approved_date_admin"));
                form.setApproved_by_admin(result.getInt("approved_by_admin"));
                form.setIs_approved_admin(result.getBoolean("is_approved_admin"));
                form.setStarting_date(result.getDate("starting_date"));
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return form;
    }

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
       
        
        try{
        PreparedStatement ps = null;
        if (responsible_form_type_id == 3){ // approve by supervisor
            String queryStatement = "update form set   is_approved_supervisor = ? and "
                    + "                         approved_date_supervisor = ? and  "
                    + "                         approved_by_supervisor = ?"
                    + "                         where form_id= ? ";
            
            ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setBoolean(1, true);
            ps.setInt(2, user_id);
            ps.setDate(form_id, (java.sql.Date) new Date());
        }
        else if(responsible_form_type_id == 4){ // approve by admin
            String queryStatement = "update form set   is_approved_admin = ? and "
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
        
        try{
            PreparedStatement ps = null;
            
        if (responsible_form_type_id == SUPERVISOR){ // approve by supervisor
            String queryStatement = "update form set   is_approved_supervisor = ? and "
                    + "                         approved_date_supervisor = ? and  "
                    + "                         approved_by_supervisor = ?"
                    + "                         where form_id= ? ";
            
            ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setBoolean(1, false);
            ps.setInt(2, user_id);
            ps.setDate(form_id, (java.sql.Date) new Date());
        }
        else if(responsible_form_type_id == ADMIN){ // approve by admin
            String queryStatement = "update form set   is_approved_admin = ? and "
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
    
    public static mFormDepartment GetFormDetails_Properties(int form_id){     
        mFormDepartment formDepartment = new mFormDepartment();
        formDepartment = GetFormDetails_department(form_id,1);
        return formDepartment;
    }
    
    public static mFormDepartment GetFormDetails_Technical(int form_id){
        mFormDepartment formDepartment = new mFormDepartment();
        formDepartment = GetFormDetails_department(form_id,2);
        return formDepartment;
    }
    
    public static mFormDepartment GetFormDetails_SoundAndLight(int form_id){     
        mFormDepartment formDepartment = new mFormDepartment();
        formDepartment = GetFormDetails_department(form_id,3);    
        return formDepartment;
    }
    
    public static mFormDepartment GetFormDetails_ArtAndCulture(int form_id){     
        mFormDepartment formDepartment = new mFormDepartment();
        formDepartment = GetFormDetails_department(form_id,4);    
        return formDepartment;
    }
    
    public static mFormDepartment GetFormDetails_Security(int form_id){     
        mFormDepartment formDepartment = new mFormDepartment();
        formDepartment = GetFormDetails_department(form_id,5);    
        return formDepartment;
    }
    
    public static mFormDepartment GetFormDetails_IT(int form_id){
        mFormDepartment formDepartment = new mFormDepartment();
        formDepartment = GetFormDetails_department(form_id,6);
        return formDepartment;
    }
    
    public static mFormDepartment GetFormDetails_department(int form_id, int form_type_id){
        
        mFormDepartment formDepartment = new mFormDepartment();
        String[] form_department_nameList = {"" ,"Properties", "Technical", "Sound & Light","Art & Culture", "Security", "IT"};
        int form_department_id = 0;
        String form_department_name = form_department_nameList[form_type_id];
        String queryStatement = "select * from form_department where form_id=? and form_type_id=?";
        
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_id);
            ps.setInt(2, form_type_id);
            
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
               form_department_id = queryResult.getInt("form_department_id");
               formDepartment.setApproved_date(queryResult.getDate("approved_date"));
               formDepartment.setApproved_by(queryResult.getInt("approved_by"));
               formDepartment.setDescription(queryResult.getString("description"));
               formDepartment.setForm_department_name(form_department_name);
               formDepartment.setForm_department_id(form_department_id);
               formDepartment.setForm_id(form_id);
               formDepartment.setForm_type_id(form_type_id);
               formDepartment.setIs_approved(queryResult.getBoolean("is_approved"));
                           
               break; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //---------------
        formDepartment.setRequestedItemList(GetFormDetails_requestedItemList(form_department_id));
        
        return formDepartment;
    }
    
    public static List<mRequestedItem> GetFormDetails_requestedItemList(int form_department_id){
        ArrayList<mRequestedItem> requestedItemList = new ArrayList<mRequestedItem>();
        
        String queryStatement = "select * from request_item inner join item on request_item.item_id = item.item_id where form_department_id=?";
        
        try{
            PreparedStatement ps = DBUtils.getPreparedStatement(queryStatement);
            ps.setInt(1, form_department_id);
            ResultSet queryResult = ps.executeQuery();
            
            //ResultSet result = DBUtils.getPreparedStatement(queryStatement).executeQuery();
            while(queryResult.next()){            
               mRequestedItem requestedItem = new mRequestedItem();
               requestedItem.setForm_department_id(queryResult.getInt("form_department_id"));
               requestedItem.setForm_type_id(queryResult.getInt("form_type_id"));
               requestedItem.setImage_url(queryResult.getString("image"));
               requestedItem.setItem_id(queryResult.getInt("item_id"));
               requestedItem.setName(queryResult.getString("name"));
               requestedItem.setQuantity(queryResult.getInt("quantity"));
               
               requestedItemList.add(requestedItem);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //---------------
        return requestedItemList;
    }
    
}
