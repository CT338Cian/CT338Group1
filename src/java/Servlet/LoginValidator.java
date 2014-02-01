/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;
import HelperClasses.PasswordHasher;
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
            
            // get salt
            List saltList = em.createNativeQuery("select Salt from Customer where email=?")
                .setParameter(1, email)
                .getResultList();
            
            if (saltList.isEmpty()) // no salt, therefore no customer
                return status;
            
            // use the salt to hash the entered password
            String salt = saltList.get(0).toString();
            String hashedPass = PasswordHasher.getHash(pswd, salt);
            
            // compare the hashed password to the one in the database
            Query q = em.createQuery("select c from Customer c where c.email=:email and c.password=:password")
                .setParameter("email", email)
                .setParameter("password", hashedPass);
            List l = q.getResultList();
            if (!l.isEmpty())
                status = true;

            }catch(Exception e){
                System.out.println(e);
            }
            return status;
    }
}