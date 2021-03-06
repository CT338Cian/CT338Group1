/*
 * Searches the database according to user search terms
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

/**
 *
 * @author Cian
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        try {
            em = emf.createEntityManager();
            
            // get what type of search the user performed (dropdown search, or searchbox)
            String searchType = request.getParameter("searchType");
            List searchResults = null;
            
            // dropdown search performed
            if (searchType.equals("dropdown")){
                System.out.println("Performing dropdown search");
                String make = request.getParameter("make");
                System.out.println("make is: " + make);
                String transmission = request.getParameter("transmission");
                System.out.println("transmission is: " + transmission);
                int price;
                
                
                // handle possible dropdown search combinations
                if(make.equals("Any") && transmission.equals("Any")){ 
                    if(request.getParameter("price").equals("Any")){//search on all vehicles(select all)
                        searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.isAvailable=1").getResultList();
                    }
                    else {//search on price
                        price = Integer.parseInt(request.getParameter("price"));
                        searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.price < :price AND v.isAvailable=1")
                            .setParameter("price", price)
                            .getResultList();
                    }
                }
                else if(make.equals("Any") && !transmission.equals("Any")){ 
                    if(request.getParameter("price").equals("Any")){//search on trans
                            searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.transmission = :transmission AND v.isAvailable=1")
                                .setParameter("transmission", transmission)
                                .getResultList();
                    }
                    else {//search on trans&price
                            price = Integer.parseInt(request.getParameter("price"));
                            searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.transmission = :transmission AND v.price < :price AND v.isAvailable=1")
                                .setParameter("transmission", transmission)
                                .setParameter("price", price)
                                .getResultList();
                    }
                }
                else if(!make.equals("Any") && transmission.equals("Any")){ 
                    if(request.getParameter("price").equals("Any")){//search on make
                            searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.make = :make AND v.isAvailable=1")
                                .setParameter("make", make)
                                .getResultList();
                    }
                    else {//search on make&price
                            price = Integer.parseInt(request.getParameter("price"));
                            searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.make = :make AND v.price < :price AND v.isAvailable=1")
                                .setParameter("make", make)
                                .setParameter("price", price)
                                .getResultList();
                    }
                }
                else if(!make.equals("Any") && !transmission.equals("Any")){ 
                    if(request.getParameter("price").equals("Any")){//search on make&trans
                            searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.make = :make AND v.transmission = :transmission AND v.isAvailable=1")
                                .setParameter("make", make)
                                .setParameter("transmission", transmission)
                                .getResultList();
                    }
                    else {//search on make&trans&price
                            price = Integer.parseInt(request.getParameter("price"));
                            searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.make = :make AND v.transmission = :transmission AND v.price < :price AND v.isAvailable=1")
                                .setParameter("make", make)
                                .setParameter("transmission", transmission)
                                .setParameter("price", price)
                                .getResultList();
                    }
                }
            }
            
            // searchbar search
            else if (searchType.equals("searchbar")){
                System.out.println("Performing searchbar search");
                
                // get search term
                String searchTerm = request.getParameter("searchterm");
                System.out.println("Performing search for: " + searchTerm);
                // perform search
                searchResults = em.createQuery("SELECT v FROM Vehicle v WHERE v.make = :searchterm AND v.isAvailable=1 OR v.model = :searchterm AND v.isAvailable=1")
                    .setParameter("searchterm", searchTerm)
                    .getResultList();
            }
            
            // attach search results to request and forward to results page
            request.setAttribute("searchResultsList",searchResults);
            request.getRequestDispatcher("SearchResults.jsp").forward(request, response);
        }catch (Exception ex) {
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
