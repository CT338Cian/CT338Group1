/*
 * Get orders for a current customer
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
import javax.servlet.http.HttpSession;
import Entities.Customer;

/**
 *
 * @author Cian
 */
@WebServlet(name = "GetOrdersServlet", urlPatterns = {"/GetOrdersServlet"})
public class GetOrdersServlet extends HttpServlet {

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
        
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        
        // check that user if logged in
        HttpSession session = request.getSession();
        if (session.getAttribute("name") == null){
            session.setAttribute("error", "You need to be logged in to do that.");
            response.sendRedirect("Login.jsp");
            return;
        }
        
        try {
            em = emf.createEntityManager();
            // get user email from session
            String email = (String)session.getAttribute("email");
            
            // create customer object
            Customer c = (Customer)em.createNamedQuery("Customer.findByEmail")
                    .setParameter("email", email)
                    .getSingleResult();
            
            // find all orders made by customer
            List orders = em.createQuery("SELECT t FROM Transaction t WHERE t.orderOrderNo = (SELECT r.orderNo FROM RentalOrder r WHERE r.customerEmail = :customer)")
                    .setParameter("customer", c)
                    .getResultList();
            // add orders to request and send to jsp for rendering
            request.setAttribute("orderList", orders);
            request.getRequestDispatcher("MyOrders.jsp").forward(request, response);
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
