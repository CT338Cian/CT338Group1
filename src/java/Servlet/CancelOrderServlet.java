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
import Entities.RentalOrder;
import Entities.Transaction;
import Entities.Vehicle;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Cian
 */
@WebServlet(name = "CancelOrderServlet", urlPatterns = {"/CancelOrderServlet"})
public class CancelOrderServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            String custEmail = (String)session.getAttribute("email");
            
            if (custEmail == null){
                session.setAttribute("error","You need to be logged in to do that");
                response.sendRedirect("Login.jsp");
                return;
            }
            
            int orderNo = Integer.parseInt(request.getParameter("OrderNo"));
            
            utx.begin();
            em = emf.createEntityManager();
            
            // get customer object
            Customer customer = em.find(Customer.class, custEmail);
            
            // get order object
            RentalOrder order = em.find(RentalOrder.class, orderNo);
            
            if (!order.getCustomerEmail().equals(customer)){
                session.setAttribute("error","You can't delete orders that are not yours!");
                response.sendRedirect("home.jsp");
                return;
            }
            
            // get transaction object
            Transaction trans = (Transaction)em.createNamedQuery("Transaction.findByOrderNo")
                    .setParameter("orderNo", order)
                    .getSingleResult();
            
            // get vehicle object and set available to true
            Vehicle vehicle = em.find(Vehicle.class, order.getVehicleReg().getReg());
            vehicle.setIsAvailable(Boolean.TRUE);
            
            // remove the order and transaction from the database, and update the vehicle
            System.out.println("Removing order: " + order.toString() + " with transaction: " + trans.toString());
            em.remove(trans);
            em.remove(order);
            em.persist(vehicle);
            
            utx.commit();
            
            request.getSession().setAttribute("info","Order cancelled successfully");
            response.sendRedirect("GetOrdersServlet");
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
