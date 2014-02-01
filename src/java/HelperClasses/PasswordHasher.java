/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package HelperClasses;

import java.nio.charset.StandardCharsets;
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
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes()); //update digest to include salt
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
