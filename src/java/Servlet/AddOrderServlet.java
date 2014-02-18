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
            String SDate   = (String) request.getParameter("startDate");
            String EDate   = (String) request.getParameter("endDate");
            String Email   = (String) session.getAttribute("email");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date StartDate = formatter.parse(SDate);
            Date EndDate = formatter.parse(EDate);
            
            String reg  = (String) request.getParameter("reg");
            Integer insNo   = Integer.parseInt(request.getParameter("insuranceNo"));
            String provider   = (String) request.getParameter("provider");
            String covertype      = (String) request.getParameter("coverType");
            String Type   = (String) request.getParameter("type");
            Integer CardNo = Integer.parseInt(request.getParameter("cardno"));
            Integer Price = Integer.parseInt(request.getParameter("price"));
       
            RentalOrder Order = new RentalOrder(StartDate,EndDate,Email,reg);
           Insurance Insurance = new Insurance(insNo, provider, covertype, Email);
                  List results = em2.createNamedQuery("Insurance.findByInsuranceNo").setParameter("insuranceNo", insNo).getResultList();
            
            if(!results.isEmpty()){//Check if insurance number already exists
                request.setAttribute("error","Order with that Insurance number already exists!");
                request.getRequestDispatcher("Rent.jsp?reg="+reg+"&price="+Price).forward(request, response);//shud print error msg
            } 
           
           
                //begin a transaction
                utx.begin();
                em = emf.createEntityManager();
                em.persist(Order);
                em.persist(Insurance);
                Customer Customer2 = new Customer();
                Customer2.setEmail(Email);
                List OrderNo = em.createQuery("SELECT v.orderNo from RentalOrder v WHERE v.customerEmail = :email").setParameter("email", Customer2).getResultList();
                Transaction Transaction = new Transaction(Price, Type, CardNo, (Integer)OrderNo.get(0));          
               
                //set vehicle that was rented to not available
                em.createQuery("UPDATE Vehicle v SET v.isAvailable = false WHERE v.reg = :reg").setParameter("reg", reg).executeUpdate();
                em.persist(Transaction);
                utx.commit();
                

                request.getRequestDispatcher("GetOrdersServlet").forward(request, response);
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
