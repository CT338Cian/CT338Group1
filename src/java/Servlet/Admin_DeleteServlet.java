package Servlet;

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
 * @author Niall
 */
@WebServlet(name = "Admin_DeleteServlet", urlPatterns = {"/Admin_DeleteServlet"})
public class Admin_DeleteServlet extends HttpServlet {

    @PersistenceUnit
    //The emf corresponding to 
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction utx;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       EntityManager em = null;
       
       try {
            utx.begin();
            //Create an em. Since the em is created inside a transaction, it is associsated with the transaction
            em = emf.createEntityManager();
            
            //Get the data from user's form            
            String reg  = (String) request.getParameter("reg");            
            
            int deleted = em.createQuery ("DELETE FROM Vehicle v WHERE v.reg = :reg").setParameter("reg", reg).executeUpdate ();
            //commit transaction which will trigger the em to 
            //commit newly created entity into database
            utx.commit();
            
            if(deleted!=1){//unsuccessful delete
                System.out.println("Car Deletion Failed!");
                request.setAttribute("errorMessage","Vehicle Not Removed. May no longer exist.");
                request.getRequestDispatcher("DeleteCar.jsp").forward(request, response);
            }
   
            else{
                System.out.println("Car with reg #"+reg+" Deleted");
                response.sendRedirect("Admin_GetAllVehiclesServlet");
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
