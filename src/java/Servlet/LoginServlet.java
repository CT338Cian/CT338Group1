/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Entities.Customer;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cian
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        EntityManager em = null;
        em = emf.createEntityManager();
        
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pswd");
            
        if(LoginValidator.validateUser(user, pass)){
            System.out.println("Yes");
            Customer c = (Customer)em.createNamedQuery("Customer.findByEmail")
                    .setParameter("email", user)
                    .getSingleResult();
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(15*60); // timeout after 15 minutes
            session.setAttribute("name", c.getFName());
            session.setAttribute("email", c.getEmail());
            session.setAttribute("isAdmin", c.getIsAdmin());
            if (c.getIsAdmin()){
                response.sendRedirect("AdminPage.jsp");
            }
            else{
                response.sendRedirect("LoginSuccess.jsp");
            }
            
                
        }
        else{
            request.setAttribute("error","Username or password incorrect");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
