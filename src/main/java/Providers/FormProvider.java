/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Providers;

import Models.*;
import DBConnection.DBUtils;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author SONY
 */
public class FormProvider {
    
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

    public static List<mFormDisplay> getForms(int created_by) {
        List<mFormDisplay> forms = new ArrayList<mFormDisplay>();
        try {
            ResultSet result = DBUtils.getPreparedStatement("select form_id from form where form.created_by = " + created_by).executeQuery();
            while (result.next()) {
                mFormDisplay form = new mFormDisplay();
                int form_id = result.getInt("form_id");
                form.setForm_id(form_id);
                form.setEvent_name(result.getString("event_name"));
                form.setActivity(result.getString("activity"));
                form.setDepartment(result.getString("department"));
                form.setStarting_date(result.getDate("starting_date"));
                form.setCoordinator_name(result.getString("coordinator_name"));
                form.setCoordinator_phone_number(result.getString("coordinator_phone_number"));
                boolean is_approved_supervisor = result.getBoolean("is_approved_supervisor");
                boolean is_approved_admin = result.getBoolean("is_approved_admin");
                form.setAdmin_progress(is_approved_admin);
                form.setSupervisor_progress(is_approved_supervisor);
                form.setDepartment_progress(getDepartmentProgress(form_id));
                int supervisor_id = result.getInt("approved_by_supervisor");
                int admin_id = result.getInt("approved_by_admin");
                if(is_approved_admin){
                    form.setLatest_response(getUserName(admin_id));
                }else if(is_approved_supervisor){
                    form.setLatest_response(getUserName(supervisor_id));
                }
                forms.add(form);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forms;
    }

    public static mResponse getLatestResponse(int form_id) {
        mResponse latest_response = new mResponse();
        List<mResponse> responses = new ArrayList<mResponse>();
        try {
            ResultSet result = DBUtils.getPreparedStatement("select * from form_department where form_department.form_id = " + form_id).executeQuery();
            while (result.next()) {
                mResponse response = new mResponse();
                response.setUser_id(result.getInt("approved_by"));
                response.setUpdated_date(result.getDate("approved_date"));
                responses.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        latest_response = responses.get(0);
        for (int i = 1; i < responses.size(); ++i) {
            mResponse compared_response = responses.get(i);
            if(latest_response.getUpdated_date().compareTo(compared_response.getUpdated_date()) < 0){
                latest_response = compared_response;
            }
        }
        return latest_response;
    }

    public static boolean getDepartmentProgress(int form_id) {
        boolean department_progress = true;
        try {
            ResultSet result = DBUtils.getPreparedStatement("select is_approved from form_department where form_department.form_id = " + form_id).executeQuery();
            while (result.next()) {
                boolean is_approved = result.getBoolean("is_approved");
                department_progress = department_progress && is_approved;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department_progress;
    }
    
    public static String getUserName(int user_id){
        String name = "";
        try{
            ResultSet result = DBUtils.getPreparedStatement("select name from user where user.user_id = " + user_id).executeQuery();
            while (result.next()) {
                name = result.getString("name");
                break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;
    }
}
