/*
 * Get info for a particular car
 */

package Servlet;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darrentighe
 */
@WebServlet(name = "GetCarInfoServlet", urlPatterns = {"/GetCarInfoServlet"})
public class GetCarInfoServlet extends HttpServlet {

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            // get registration passed in as URL parameter
            String Reg  = (String) request.getParameter("Reg");
            // search for vehicle
            List results = em.createQuery("SELECT v FROM Vehicle v WHERE v.reg = :reg")
                    .setParameter("reg", Reg)
                    .getResultList();
            
            if(!results.isEmpty()){//send to JSP for rendering
                        request.setAttribute("VehicleInfo",results);
                        request.getRequestDispatcher("GetCarInfo.jsp").forward(request, response);
            }
            else{
                request.setAttribute("errorMessage","No car with that REG!");
                request.getRequestDispatcher("GetCarInfo.jsp").forward(request, response);//shud print error msg
            }
        }
        finally {
            //close the em to release any resources held up by the persistebce provider
            if(em != null) {
                em.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
