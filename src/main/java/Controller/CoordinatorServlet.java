

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Functions.*;
import Models.*;
import Providers.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author supanattechasothon
 */
@WebServlet(name="Coordinator", 
            loadOnStartup = 1,
            urlPatterns = { "/Coordinator", 
                            "/Coordinator/Create", 
                            "/Coordinator/Details"
})
public class CoordinatorServlet extends HttpServlet {

    UserProfileFunctions userProfileFunctions = new UserProfileFunctions();
    DetailsProvider detailsProvider = new DetailsProvider();
    ListProvider listProvider = new ListProvider();
    CreateProvider createProvider = new CreateProvider();
    UserProvider userProvider = new UserProvider();
    TimeConverter timeConverter = new TimeConverter();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        mUser userProfile = userProfileFunctions.GetUserProfile(request);
        request.setAttribute("userProfile", userProfile);
        
        if (userPath.equals("/Coordinator")) {
           
            userPath = "/List";
            List<mFormDisplay> formDisplayList = listProvider.getForms(userProfile.getUser_id());
            request.setAttribute("formDisplayList", formDisplayList);
          
        } else if (userPath.equals("/Coordinator/Create")) {
            int id = 1;
            String event_name = request.getAttribute("form_name").toString();
            String activity = request.getAttribute("form_activity_type").toString();
            String department = request.getAttribute("form_department").toString();
            String campus = request.getAttribute("form_place").toString();
            String facility = request.getAttribute("form_room").toString();
            
            String preparing_date = request.getAttribute("pre_start_date").toString();
            String preparing_time = request.getAttribute("pre_start_time").toString();
            String starting_date = request.getAttribute("start_date").toString();
            String starting_time = request.getAttribute("start_time").toString();
            //String end_date = request.getAttribute("end_date").toString();
            //String end_time = request.getAttribute("end_time").toString();
            
            String coordinator_name = request.getAttribute("coordinator_name").toString();
            String coordinator_phone_number = request.getAttribute("coordinator_phone").toString();
            String description = request.getAttribute("0_description").toString();
            
            mForm new_form = new mForm();
            new_form.setEvent_name(event_name);
            new_form.setActivity(activity);
            new_form.setDepartment(department);
            new_form.setCampus(campus);
            new_form.setFacility(facility);
            
            //Preparing date
            new_form.setPreparing_date(preparing_date + " " + preparing_time);
            //Starting date
            new_form.setStarting_date(starting_date + " " + starting_time);
            
            new_form.setCoordinator_name(coordinator_name);
            new_form.setCoordinator_phone_number(coordinator_phone_number);
            new_form.setDescription(description);
            
            new_form.setCreated_by(id);
            new_form.setUpdated_by(id);
            
            mResult result = createProvider.submitRequest(new_form);
            
            
            if(result.getIsSuccess()){
                userPath = "/List";
            }
            
        } else if (userPath.equals("/Coordinator/Details")) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            mForm formDetails = detailsProvider.getFormDetails(id);
            mFormDepartment formDepartment_artAndCulture = detailsProvider.GetFormDetails_ArtAndCulture(id);
            mFormDepartment formDepartment_IT = detailsProvider.GetFormDetails_IT(id);
            mFormDepartment formDepartment_properties = detailsProvider.GetFormDetails_Properties(id);
            mFormDepartment formDepartment_secuity = detailsProvider.GetFormDetails_Security(id);
            mFormDepartment formDepartment_soundAndLight = detailsProvider.GetFormDetails_SoundAndLight(id);
            mFormDepartment formDepartment_technical = detailsProvider.GetFormDetails_Technical(id);
            
            request.setAttribute("formDetails", formDetails);
            request.setAttribute("formDepartment_artAndCulture", formDepartment_artAndCulture);
            request.setAttribute("formDepartment_IT", formDepartment_IT);
            request.setAttribute("formDepartment_properties", formDepartment_properties);
            request.setAttribute("formDepartment_secuity", formDepartment_secuity);
            request.setAttribute("formDepartment_soundAndLight", formDepartment_soundAndLight);
            request.setAttribute("formDepartment_technical", formDepartment_technical);
            
            
            ArrayList<mResponseMessage> responseMessageList = detailsProvider.GetResponseMessageList(id, 1);
            request.setAttribute("responseMessageList_1", responseMessageList);
            responseMessageList = detailsProvider.GetResponseMessageList(id, 2);
            request.setAttribute("responseMessageList_2", responseMessageList);
            responseMessageList = detailsProvider.GetResponseMessageList(id, 3);
            request.setAttribute("responseMessageList_3", responseMessageList);
            responseMessageList = detailsProvider.GetResponseMessageList(id, 4);
            request.setAttribute("responseMessageList_4", responseMessageList);
            responseMessageList = detailsProvider.GetResponseMessageList(id, 5);
            request.setAttribute("responseMessageList_5", responseMessageList);
            responseMessageList = detailsProvider.GetResponseMessageList(id, 6);
            request.setAttribute("responseMessageList_6", responseMessageList);
            
            userPath = "/Details";
        }


        String url = "/WEB-INF/View/Web" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        //processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        mUser userProfile = userProfileFunctions.GetUserProfile(request);
        
        // if addToCart action is called
         if (userPath.equals("/Coordinator")) {
            userPath = "/List";
            //System.out.println("count: "+FormProvider.getAllRequests(8).size());
            
        } else if (userPath.equals("/Coordinator/Create")) {
            
            
        } else if (userPath.equals("/Coordinator/Details")) {
            
            
        }


        String url = "/WEB-INF/View/Web" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
