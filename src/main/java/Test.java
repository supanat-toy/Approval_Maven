
import Functions.TimeConverter;
import Models.*;
import Providers.CreateProvider;
import Providers.ListProvider;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SONY
 */
public class Test {

    public static void main(String[] args) {
        TimeConverter tc = new TimeConverter();
////        System.out.println(tc.dateToString(new Date()));
        mForm new_form = new mForm();
        new_form.setForm_id(1);
        new_form.setEvent_name("Event01_V2");
        new_form.setActivity("Education");
        new_form.setDepartment("Science and Technology");
        new_form.setCampus("Bangna");
        new_form.setFacility("VMS0101");
        
        //Preparing date
        new_form.setPreparing_date(tc.dateToString(new Date()));
        //Starting date
        new_form.setStarting_date("2020-04-01 09:54");
        
        new_form.setCoordinator_name("Tester01");
        new_form.setCoordinator_phone_number("0123456789");
        new_form.setDescription("N/A");

        new_form.setCreated_by(1);
        new_form.setUpdated_by(1);
        
        CreateProvider cp = new CreateProvider();
        mResult result = cp.updateForm(new_form);
        System.out.println(result.getIsSuccess() + ": " + result.getMessage());
//        List<mFormDisplay> fds = new ListProvider().getForms(1);
//        int i = 0;
    }
}
