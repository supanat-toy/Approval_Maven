/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Providers;

import DBConnection.DBUtils;
import Models.*;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author SONY
 */
public class FormProvider {
    
    public static Form getForm(int form_id){
        Form form = new Form();
        try{
            ResultSet result = DBUtils.getPreparedStatement("select * from form where form.form_id = " + form_id).executeQuery();
            while(result.next()){            
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
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return form;
    }
    
    public static List<Form> getForms(int created_by){
        List<Form> forms = new ArrayList<Form>();
        try {
            List<Integer> form_ids = new ArrayList<Integer>();
            ResultSet result = DBUtils.getPreparedStatement("select form_id from form where form.created_by = " + created_by).executeQuery();
            while (result.next()){
                int form_id = result.getInt("form_id");
                form_ids.add(form_id);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forms;
    }
}
