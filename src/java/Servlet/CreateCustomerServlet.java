/*
 * Registers a new customer
 */
package Servlet;

import HelperClasses.PasswordHasher;
import Entities.Customer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.persistence.PersistenceUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;


/**
 * The servlet class to insert Person into database
 */
@WebServlet(name = "CreateCustomerServlet", urlPatterns = {"/CreateCustomer"})
public class CreateCustomerServlet extends HttpServlet {
    
    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;  
    
    @Resource
    private UserTransaction utx;

    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
        assert emf != null;  //Make sure injection went through correctly.
        EntityManager em = null;
        EntityManager em2 = emf.createEntityManager();
        try {
            
            //Get the data from user's form            
            String firstName  = (String) request.getParameter("firstName");
            String lastName   = (String) request.getParameter("surname");
            String address   = (String) request.getParameter("address");
            String dobString   = (String) request.getParameter("dob");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = formatter.parse(dobString);
            String email      = (String) request.getParameter("email");
            String phone      = (String) request.getParameter("phone");
            String password   = (String) request.getParameter("password");
            boolean isAdmin = false;
            
            // check that email is not already registered to another customer
            List results = em2.createNamedQuery("Customer.findByEmail")
                    .setParameter("email", email)
                    .getResultList();
            
            if(!results.isEmpty()){//email already exists
                request.setAttribute("errorMessage","Email Taken! Choose Another");
                request.getRequestDispatcher("CreateCustomer.jsp").forward(request, response);
            }
            else{
                // send the password to be hashed, which returns hash and salt
                String[] passAndHash = PasswordHasher.registerHashAndSalt(password);
                String hashedPassword = passAndHash[0];
                String salt = passAndHash[1];
                
                //Create a person instance
                Customer person = new Customer(firstName, lastName, address, dob, email, phone, hashedPassword, isAdmin, salt);
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(15*60); // timeout after 15 minutes
                session.setAttribute("name", firstName);
                session.setAttribute("email", email);
                session.setAttribute("isAdmin", isAdmin);
                //begin a transaction
                utx.begin();
                //create an em. 
                //Since the em is created inside a transaction, it is associsated with 
                //the transaction
                em = emf.createEntityManager();
                //persist the person entity
                em.persist(person);
                //commit transaction which will trigger the em to 
                //commit newly created entity into database
                utx.commit();
                
                // redirect back to page before login prompt, if applicable.
                if (session.getAttribute("referer") != null){
                        // get referal string (ignoring leading slash)
                        String referer = ((String)session.getAttribute("referer")).substring(1);
                        System.out.println("Redirecting back to referer: " + referer);
                        response.sendRedirect(referer);
                        session.removeAttribute("referer");
               }
               else{
                        response.sendRedirect("home.jsp");
               }
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
        return "Short description";
    }
    // </editor-fold>
}
