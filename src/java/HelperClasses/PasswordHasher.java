/*
 * Generates a salt, a hash, or both in the case of user registration
 */

package HelperClasses;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Cian
 */
public class PasswordHasher {
    
    // calculates a hash, given a password and a salt
    public static String getHash(String pass, String salt) {
        String hashedPassword = null;
        try{
            // get sha-1 digest
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // update digest to include salt
            md.update(salt.getBytes()); 
            // digest bytes of password into a byte array
            byte[] hashedBytes = md.digest(pass.getBytes());
            
            // convert byte array to hex
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<hashedBytes.length;i++) {
	    	sb.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
            hashedPassword = sb.toString();
            
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hashedPassword;
    }
    
    // calculates a hash, then returns both hash and salt to store in DB
    public static String[] registerHashAndSalt(String pass){
        String salt = getSalt();
        String hashedPassword = getHash(pass, salt);
        
        String[] hashAndSalt = {hashedPassword, salt};
        return hashAndSalt;
    }
    
    // creates a random salt
    private static String getSalt(){
	SecureRandom sr = new SecureRandom();
	byte[] salt = new byte[16];
	sr.nextBytes(salt);
        
        //convert salt to hex
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<salt.length;i++) {
            sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
	}
        String hexSalt = sb.toString();
	return hexSalt;
    }
}
