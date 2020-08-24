package com.example;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.swing.JOptionPane;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;




@WebServlet("/firstController")
public class firstController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//CASE:1  AES
		    private static final String ALGO = "AES";
		    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
		
		    private static Key aesgenerateKey() throws Exception {
		        Key key1 = new SecretKeySpec(keyValue, ALGO);
		 	   	try 
		 	   	{
		 	   	} 
		 	   	catch (Exception e) 
		 	   	{   e.printStackTrace();
		 	   	}
		        
		        return key1;
		     }
		    // AES Encryption
		    public static String aesencrypt(String Data) throws Exception {
		       Key key1 = aesgenerateKey();
		       Cipher c = Cipher.getInstance(ALGO);
		       c.init(Cipher.ENCRYPT_MODE, key1);
		       byte[] encVal = c.doFinal(Data.getBytes());
		       String encryptedValue = new BASE64Encoder().encode(encVal);
			   	try 
			   	{
			   	} 
			   	catch (Exception e) 
			   	{   e.printStackTrace();
			   	}
		
		       return encryptedValue;
		    }
		    // AES Decryption
		    public static String aesdecrypt(String encryptedData) throws Exception {
		    	try{
		       Key key1 = aesgenerateKey();
		       Cipher c = Cipher.getInstance(ALGO);
		       c.init(Cipher.DECRYPT_MODE, key1);
		      
		       byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		       byte[] decValue = c.doFinal(decordedValue);
		       String decryptedValue = new String(decValue);
		    	
		       
		       return decryptedValue;
		    	}
		    	
		    	catch(Exception e){
		    	    JOptionPane.showMessageDialog(null,"Invalid String.","Alert",JOptionPane.WARNING_MESSAGE); 		    		
		    		return "";
		    	}
		    }
 // CASE 2 : Triple DES

		    private static final String key = "abcd";
		    
		    // Triple DES Encryption
		    public static String tDesencrypt(String unencryptedString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, java.io.UnsupportedEncodingException {
		    	MessageDigest md = MessageDigest.getInstance("md5");
		        byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
		        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		        for (int j = 0, k = 16; j < 8;) {
		            keyBytes[k++] = keyBytes[j++];
		        }
		        SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
		        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		        byte[] plainTextBytes = unencryptedString.getBytes("utf-8");
		        byte[] buf = cipher.doFinal(plainTextBytes);
		        byte[] base64Bytes = org.apache.tomcat.util.codec.binary.Base64.encodeBase64(buf);
		        String base64EncryptedString = new String(base64Bytes);
		        return base64EncryptedString;
		    }
		    	// Triple DES Decryption
			    public static String tDesdecrypt(String encryptedString) throws  NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, java.io.UnsupportedEncodingException {
		    	if(encryptedString == null)
		        {
		            return "There is null value";
		        }
		    	try{
		        byte[] message = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(encryptedString.getBytes("utf-8"));
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
		        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		        
		        for (int j = 0, k = 16; j < 8;) {
		            keyBytes[k++] = keyBytes[j++];
		        }		        
		        SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
		        Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		        decipher.init(Cipher.DECRYPT_MODE, secretKey);
		        byte[] plainText = decipher.doFinal(message);
		        
		        return new String(plainText, "UTF-8");
		    	}
		    	catch(Exception e){
		    		
		    	    JOptionPane.showMessageDialog(null,"Invalid String.","Alert",JOptionPane.WARNING_MESSAGE); 		    		

		    		return "" ;
		    	}
		    }		    
		    
		    
// CASE 3  : RC4 
			    	private static final String algorithm = "RC4";
			    	private static final String key4="key";
			    	// RC4 Encryption
		    	  public static byte[] rc4encrypt(String toEncrypt) throws Exception {
		    	      SecureRandom sr = new SecureRandom(key4.getBytes());
		    	      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		    	      kg.init(sr);
		    	      SecretKey sk = kg.generateKey();
		    	      Cipher cipher = Cipher.getInstance(algorithm);
		    	      cipher.init(Cipher.ENCRYPT_MODE, sk);
		    	      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
		    	      return encrypted;
		    	   }
		    	  // RC4 Decryption
		    	   public static String rc4decrypt(byte[] toDecrypt) throws Exception {
		    	      SecureRandom sr = new SecureRandom(key4.getBytes());
		    	      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		    	      kg.init(sr);
		    	      SecretKey sk = kg.generateKey();
		    	      Cipher cipher = Cipher.getInstance(algorithm);
		    	      cipher.init(Cipher.DECRYPT_MODE, sk);
		    	      byte[] decrypted = cipher.doFinal(toDecrypt);
		    	      return new String(decrypted);
		    	   }
		    	   
// CASE 4 : MD5  

    		  private static String md5(final String input) throws NoSuchAlgorithmException {
    			  final MessageDigest md = MessageDigest.getInstance("MD5");
    			  final byte[] messageDigest = md.digest(input.getBytes());
    			  final BigInteger number = new BigInteger(1, messageDigest);
    			  return String.format("%032x", number);
    		  }
    		  private static Cipher initCipher(final int mode, final String initialVectorString, final String secretKey)
    				  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
    			  		final SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
    			  		final IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
    			  		final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
    			  		cipher.init(mode, skeySpec, initialVector);
    			  		return cipher;
    		  }
    		  // MD5 Encryption
    		  public static String md5encrypt(final String dataToEncrypt, final String initialVector, final String secretKey) {
    			  String encryptedData = null;
    			  try {
    				  final Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, initialVector, secretKey);
    				  final byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
    				  encryptedData = (new BASE64Encoder()).encode(encryptedByteArray);
    			  	}
    			  catch (Exception e) {   	
    	            e.printStackTrace();
    			  	}
    			  return encryptedData;
    		  }
    		  // MD5 Decryption
    		  public static String md5decrypt(final String encryptedData, final String initialVector, final String secretKey) {
    			  String decryptedData = null;
    			  try {
    				  final Cipher cipher = initCipher(Cipher.DECRYPT_MODE, initialVector, secretKey);
    				  final byte[] encryptedByteArray = (new BASE64Decoder()).decodeBuffer(encryptedData);
    				  final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
    				  decryptedData = new String(decryptedByteArray, "UTF8");
    			  	}
    			  catch (Exception e) {
    			  		e.printStackTrace();
    			  	}
    			  return decryptedData;
    	    }
 }
