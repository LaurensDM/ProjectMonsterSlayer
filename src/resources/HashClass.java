package resources;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashClass {

    public static  String securePassword(String originalPassword, String salt){
        String generatedPassword=null;
        try {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(salt.getBytes());
    byte[] bytes = md.digest(originalPassword.getBytes());
    StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] + 0xff) + 0x100,16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException ne){
        ne.printStackTrace();
        }
        return generatedPassword;
    }

    public static String getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        return saltString.substring(0,saltString.length()-2);
    }

}
