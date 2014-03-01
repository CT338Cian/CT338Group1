/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Entities.RentalOrder;
import Entities.Insurance;
import Entities.Transaction;
import Entities.Customer;
import Entities.Vehicle;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author darrentighe
 */
@WebServlet(name = "AddOrderServlet", urlPatterns = {"/AddOrderServlet"})
public class AddOrderServlet extends HttpServlet {

   @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;  
    
    @Resource
    private UserTransaction utx;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        EntityManager em2 = emf.createEntityManager();
        HttpSession session = request.getSession();
        if (session.getAttribute("email") == null){
            session.setAttribute("error", "You need to be logged in to do that.");
            response.sendRedirect("Login.jsp");
            return;
        }
        
        try {
            //Get the data from user's form     
            String sDate   = (String) request.getParameter("startDate");
            String eDate   = (String) request.getParameter("endDate");
            String email   = (String) session.getAttribute("email");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(sDate);
            Date endDate = formatter.parse(eDate);
            
            String reg  = (String) request.getParameter("reg");
            Integer insNo   = Integer.parseInt(request.getParameter("insuranceNo"));
            String provider   = (String) request.getParameter("provider");
            String covertype      = (String) request.getParameter("coverType");
            String type   = (String) request.getParameter("type");
            Integer cardNo = Integer.parseInt(request.getParameter("cardno"));
            Integer price = Integer.parseInt(request.getParameter("price"));
            
            //get customer object
            Customer c = (Customer)em2.createNamedQuery("Customer.findByEmail")
                    .setParameter("email", email)
                    .getSingleResult();
            
            //get vehicle object
            Vehicle v = (Vehicle)em2.createNamedQuery("Vehicle.findByReg")
                    .setParameter("reg", reg)
                    .getSingleResult();
            
            // check if insurance number entered already exists
            List results = em2.createNamedQuery("Insurance.findByInsuranceNo")
                    .setParameter("insuranceNo", insNo)
                    .getResultList();
            
            Insurance insurance;
            if(!results.isEmpty()){//Check if insurance number already exists
                request.setAttribute("error","Order with that Insurance number already exists!");
                request.getRequestDispatcher("Payment.jsp").forward(request, response);//should print error message
                return;
            }
            else{
                insurance = new Insurance(insNo, provider, covertype, c);
            }
            
            RentalOrder order = new RentalOrder(startDate, endDate, c, v);
            Transaction transaction = new Transaction(price, type, cardNo, order);    
            
            //set vehicle that was rented to not available
            v.setIsAvailable(Boolean.FALSE);
            
            //begin a transaction
            utx.begin();
            em = emf.createEntityManager();
            em.persist(order);
            em.persist(insurance);
            em.persist(transaction);
            em.merge(v);
            utx.commit();
            
            
            request.setAttribute("vehicle", v);
            request.setAttribute("order", order);
            request.setAttribute("orderCompleted", true);
            request.getRequestDispatcher("OrderConfirmed.jsp").forward(request, response);
            }
         catch (Exception ex) {
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
