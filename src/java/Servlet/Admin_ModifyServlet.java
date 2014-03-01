/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Entities.Vehicle;
import java.io.IOException;
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
 * @author Cian
 */


@WebServlet(name = "Admin_ModifyServlet", urlPatterns = {"/Admin_ModifyServlet"})
public class Admin_ModifyServlet extends HttpServlet {

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
    
    @Resource
    private UserTransaction utx;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        
        try{
            String reg = (String)request.getParameter("reg");
            String make = (String)request.getParameter("make");
            String model = (String)request.getParameter("model");
            String year = (String)request.getParameter("year");
            String colour = (String)request.getParameter("colour");
            String enginecc = (String)request.getParameter("enginecc");
            String price = (String)request.getParameter("price");
            String fueltype = (String)request.getParameter("fueltype");
            String transmission = (String)request.getParameter("transmission");

            utx.begin();

            em = emf.createEntityManager();
            
            // find vehicle
            Vehicle v = em.find(Vehicle.class, reg);
            
            // change all properties to those from form
            v.setMake(make);
            v.setModel(model);
            v.setYear(Integer.parseInt(year));
            v.setColour(colour);
            v.setEngineCC(enginecc);
            v.setPrice(Integer.parseInt(price));
            v.setFuelType(fueltype);
            v.setTransmission(transmission);

            em.persist(v);
            
            utx.commit();
            
            request.setAttribute("info", "Vehicle: " + reg + " updated successfully");
            request.getRequestDispatcher("Admin_GetAllVehiclesServlet").forward(request, response);
            
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
