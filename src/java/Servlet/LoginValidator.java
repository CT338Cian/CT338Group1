/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;
import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Cian
 */


public class LoginValidator {
   
    private static EntityManagerFactory emf  = Persistence.createEntityManagerFactory("CarRentalProjectPU");

    public static boolean validateUser(String email, String pswd){
        boolean status =  false;
        try{          
            EntityManager em;
            em = emf.createEntityManager();
            Query q = em.createQuery("select c from Customer c where c.email=:email and c.password=:password")
                .setParameter("email", email)
                .setParameter("password", pswd);
            List l = q.getResultList();
            if (!l.isEmpty())
                status = true;

            }catch(Exception e){System.out.println(e);}
            return status;
    }
}