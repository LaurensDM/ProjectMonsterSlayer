package resources;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashClass {

    public static  String SHA_512_SecurePassword(String originalPassword, String salt){
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

   /* public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
      *//*  String[] parts = storedPassword.split(":");

        for (String element: parts
             ) {
            System.out.println(element);
        }

        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(),salt, iterations, hash.length*8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("SHA-512");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff==0;*//*
    }*/

   /* private static byte[] fromHex(String hex) throws NoSuchAlgorithmException{
        byte[] bytes = new byte[hex.length()/2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2*i,2*i+2),16);

        }
        return  bytes;
    }*/
}
