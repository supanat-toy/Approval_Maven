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
    
    public static Form getForms(int form_id){
        Form form = new Form();
        try{
            ResultSet result = DBUtils.getPreparedStatement("").executeQuery();
            while(result.next()){            
                form.setEvent_name(result.getString("event_name"));
                form.setActivity(result.getString("activity"));
                form.setDepartment(result.getString("department"));
                form.setFacility(result.getString("facility"));
                form.setPreparing_date(result.getDate("preparing_date"));
                form.setStarting_date(result.getDate("starting_date"));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return form;
    }
}
