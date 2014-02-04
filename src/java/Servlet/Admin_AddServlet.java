/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Entities.Vehicle;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author darrentighe
 */
@WebServlet(name = "Admin_AddServlet", urlPatterns = {"/Admin_AddServlet"})
public class Admin_AddServlet extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;  
    
    @Resource
    private UserTransaction utx;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        EntityManager em2 = emf.createEntityManager();
        try {
            String reg  = (String) request.getParameter("reg");
            String make   = (String) request.getParameter("make");
            String model   = (String) request.getParameter("model");
            Integer year   = Integer.parseInt(request.getParameter("year"));
            String colour      = (String) request.getParameter("colour");
            String engineCC      = (String) request.getParameter("engineCC");
            Integer price   = Integer.parseInt(request.getParameter("price"));
            String fuelType   = (String) request.getParameter("fueltype");
            String transmission   = (String) request.getParameter("transmission");
            String imagePath   = (String) request.getParameter("imagePath");
            Boolean isAvailable   = true;

            
            List results = em2.createNamedQuery("Vehicle.findByReg").setParameter("reg", reg).getResultList();
            
            if(!results.isEmpty()){//Reg already exists
                request.setAttribute("errorMessage","Car with that Reg Already Exists! Please Choose another.");
                request.getRequestDispatcher("AdminAdd.jsp").forward(request, response);//shud print error msg
            }
            else{

                Vehicle Car = new Vehicle(reg, make, model, year, colour, engineCC, price, fuelType, transmission, imagePath, isAvailable);

                //begin a transaction
                utx.begin();
                //create an em. 
                //Since the em is created inside a transaction, it is associsated with 
                //the transaction
                em = emf.createEntityManager();
                em.persist(Car);
                utx.commit();
                request.getRequestDispatcher("Admin_GetAllVehiclesServlet").forward(request, response);
            }
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
