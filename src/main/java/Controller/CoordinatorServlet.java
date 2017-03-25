

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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        mUser userProfile = userProfileFunctions.GetUserProfile(request);
       
        if (userPath.equals("/Coordinator")) {
            userPath += "/List";
            List<mFormDisplay> formDisplayList = listProvider.getForms(userProfile.getUser_id());
            request.setAttribute("formDisplayList", formDisplayList);
          
        } else if (userPath.equals("/Coordinator/Create")) {
            
            
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
