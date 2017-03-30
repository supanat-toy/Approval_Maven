/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Providers;

import DBConnection.DBUtils;
import Models.*;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author supanattechasothon
 */
public class CreateProvider {

    public static final int COORDINATOR = 1, SUPERVISOR = 2, ADMIN = 3, PROPERTIES = 4, TECHNICAL = 5, SOUNDANDLIGHT = 6, ARTSANDCULTURE = 7, SECURITY = 8, IT = 9;

    public mResult submitRequest(mForm form) {
        mResult result = new mResult();
        try {
            PreparedStatement ps = DBUtils.getPreparedStatement("insert into form "
                    + "(event_name,"
                    + "activity,"
                    + "department,"
                    + "campus,"
                    + "facility,"
                    + "preparing_date,"
                    + "starting_date,"
                    + "coordinator_name,"
                    + "coordinator_phone_number,"
                    + "description,"
                    + "created_date,"
                    + "created_by,"
                    + "updated_date,"
                    + "updated_by,"
                    + "approved_date_supervisor,"
                    + "approved_by_supervisor,"
                    + "is_approved_supervisor,"
                    + "approved_date_admin,"
                    + "approved_by_admin,"
                    + "is_approved_admin,"
                    + "is_delete) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, form.getEvent_name());
            ps.setString(2, form.getActivity());
            ps.setString(3, form.getDepartment());
            ps.setString(4, form.getCampus());
            ps.setString(5, form.getFacility());
            ps.setDate(6, (Date) form.getPreparing_date());
            ps.setDate(7, (Date) form.getStarting_date());
            ps.setString(8, form.getCoordinator_name());
            ps.setString(9, form.getCoordinator_phone_number());
            ps.setString(10, form.getDescription());
            ps.setDate(11, (Date) form.getCreated_date());
            ps.setInt(12, form.getCreated_by());
            ps.setDate(13, (Date) form.getUpdated_date());
            ps.setInt(14, form.getUpdated_by());
            ps.setDate(15, (Date) form.getApproved_date_supervisor());
            ps.setInt(16, form.getApproved_by_supervisor());
            ps.setBoolean(17, form.isIs_approved_supervisor());
            ps.setDate(18, (Date) form.getApproved_date_admin());
            ps.setInt(19, form.getApproved_by_admin());
            ps.setBoolean(20, form.isIs_approved_admin());
            ps.setBoolean(21, form.isIs_delete());
            ps.execute();
            result.setIsSuccess(true);
            result.setMessage("Submit Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            result.setIsSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
