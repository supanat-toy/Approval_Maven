/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.*;
import java.util.*;

/**
 *
 * @author SONY
 */
public class Form {
    
    private int form_id;
    private String event_name;
    private String activity;
    private String department;
    private String campus;
    private String facility;
    private Date preparing_date;
    private Date starting_date;
    private String coordinator_name;
    private String coordinator_phone_number;
    private String description;
    private Date created_date;
    private int created_by;
    private Date updated_date;
    private int updated_by;
    private Date approved_date_supervisor;
    private int approved_by_supervisor;
    private boolean is_approved_supervisor;
    private Date approved_date_admin;
    private int approved_by_admin;
    private boolean is_approved_admin;
    
    public Form(){
        Date now = new Date();
        this.created_date = now;
        this.updated_date = now;
        this.approved_date_supervisor = now;
        this.approved_by_supervisor = 0;
        this.is_approved_supervisor = false;
        this.approved_date_admin = now;
        this.approved_by_admin = 0;
        this.is_approved_admin = false;
    }    

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public Date getPreparing_date() {
        return preparing_date;
    }

    public void setPreparing_date(Date preparing_date) {
        this.preparing_date = preparing_date;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(Date starting_date) {
        this.starting_date = starting_date;
    }

    public String getCoordinator_name() {
        return coordinator_name;
    }

    public void setCoordinator_name(String coordinator_name) {
        this.coordinator_name = coordinator_name;
    }

    public String getCoordinator_phone_number() {
        return coordinator_phone_number;
    }

    public void setCoordinator_phone_number(String coordinator_phone_number) {
        this.coordinator_phone_number = coordinator_phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public Date getApproved_date_supervisor() {
        return approved_date_supervisor;
    }

    public void setApproved_date_supervisor(Date approved_date_supervisor) {
        this.approved_date_supervisor = approved_date_supervisor;
    }

    public int getApproved_by_supervisor() {
        return approved_by_supervisor;
    }

    public void setApproved_by_supervisor(int approved_by_supervisor) {
        this.approved_by_supervisor = approved_by_supervisor;
    }

    public boolean isIs_approved_supervisor() {
        return is_approved_supervisor;
    }

    public void setIs_approved_supervisor(boolean is_approved_supervisor) {
        this.is_approved_supervisor = is_approved_supervisor;
    }

    public Date getApproved_date_admin() {
        return approved_date_admin;
    }

    public void setApproved_date_admin(Date approved_date_admin) {
        this.approved_date_admin = approved_date_admin;
    }

    public int getApproved_by_admin() {
        return approved_by_admin;
    }

    public void setApproved_by_admin(int approved_by_admin) {
        this.approved_by_admin = approved_by_admin;
    }

    public boolean isIs_approved_admin() {
        return is_approved_admin;
    }

    public void setIs_approved_admin(boolean is_approved_admin) {
        this.is_approved_admin = is_approved_admin;
    }
    
    
}
