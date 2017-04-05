
import Models.mFormType;
import Providers.ListProvider;
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
    public static void main(String[] args){
        ListProvider lp = new ListProvider();
        List<mFormType> formTypes = lp.getFormTypes();
        for(mFormType formType : formTypes){
            String s = String.format("%1$d : " + formType.getName(), formType.getForm_type_id());
            System.out.println(s);
        }
    }
}
