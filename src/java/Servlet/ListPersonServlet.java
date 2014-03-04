/*
 * Allows an admin to list all registered customers
 */
package Servlet;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

/**
 * The servlet class to list Persons from database
 */
@WebServlet(name="ListPersonServlet", urlPatterns={"/ListPerson"})
public class ListPersonServlet extends HttpServlet {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        
        // check user is authorised
        HttpSession session = request.getSession();
        if (session.getAttribute("isAdmin") != Boolean.TRUE){
            session.setAttribute("error", "You do not have admin access!");
            response.sendRedirect("home.jsp");
            return;
        }
        
        try {
            em = emf.createEntityManager();
            
            //query for all the persons in database
            List persons = em.createQuery("select p from Customer p").getResultList();
            request.setAttribute("personList",persons);
            
            //Forward to the jsp page for rendering
            request.getRequestDispatcher("ListPerson.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            //close the em to release any resources held up by the persistebce provider
            if(em != null) {
                em.close();
            }
        }
      
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "ListPerson servlet";
    }
    // </editor-fold>
}
