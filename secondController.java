package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.example.firstController;

@WebServlet("/secondController")
public class secondController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	System.out.println("Inside second Controller do Get");	
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside second Controller do Post");
		
		//Session
		HttpSession session = request.getSession(true); 
		System.out.println("working upto session establishment");
			
	    //Requests
		int al=Integer.parseInt(request.getParameter("algo"));
		System.out.println("You Have Chosen :" +al);		
		String encrypt=request.getParameter("encrypt");
	    String decrypt = request.getParameter("decrypt");
	    String password = request.getParameter("plaintext");
		System.out.println("plaintext :" +password);
		//SWITCH 
		switch(al)
		{
		case 1:
					System.out.println("AES Encryption");
					String passwordEnc = null;
				    String passwordDec = null;

					if(encrypt!=null){      
					       
				    	try {
				    		Integer alInt=al;
				    		//Function call encrypt()
				    		passwordEnc = firstController.aesencrypt(password);
					        System.out.println("Encrypted Text : " + passwordEnc);
					        System.out.println("alInt : " + alInt);
					        session.setAttribute("alInt",alInt);
					        session.setAttribute("plaintext", password);
							session.setAttribute("result",passwordEnc);
							response.sendRedirect("first.jsp");
						} catch (Exception e1) {
							e1.printStackTrace();
						}   
					}
					else if(decrypt!=null){
						try {
				    		Integer alInt=al;

							password = request.getParameter("plaintext");
						    passwordEnc = request.getParameter("result");
						    //Function call decrypt()
							passwordDec = firstController.aesdecrypt(passwordEnc);
					        System.out.println("Decrypted Text : " + passwordDec);
					        System.out.println("alInt : " + alInt);
					        session.setAttribute("alInt",alInt);
					        session.setAttribute("plaintext", password);
					        session.setAttribute("result", passwordDec);
							response.sendRedirect("first.jsp");			 
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else{
				        System.out.println("Error");
					}
					break;

		
		case 2:
	        System.out.println("TripleDes Encryption");
			String pdEn = null;
		    String pdDe = null;

			if(encrypt!=null){      
			       
		    	try {
		    		Integer alInt=al;

		    		//Function call encrypt()
		    		pdEn = firstController.tDesencrypt(password);
			        System.out.println("Encrypted Text : " + pdEn);
			        System.out.println("alInt : " + alInt);
			        session.setAttribute("alInt",alInt);
			        session.setAttribute("plaintext", password);
					session.setAttribute("result",pdEn);
					response.sendRedirect("first.jsp");
				} catch (Exception e1) {
					e1.printStackTrace();
				}   
			}
			else if(decrypt!=null){
				try {
		    		Integer alInt=al;

					password = request.getParameter("plaintext");
				    pdEn = request.getParameter("result");
				    //Function call decrypt()
					pdDe = firstController.tDesdecrypt(pdEn);
			        System.out.println("Decrypted Text : " + pdDe);
			        System.out.println("alInt : " + alInt);
			        session.setAttribute("alInt",alInt);
			        session.setAttribute("plaintext", password);
			        session.setAttribute("result", pdDe);
					response.sendRedirect("first.jsp");			 
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
			else{
		        System.out.println("Error");
			}
	        
	        break;

	        
	        
	        
		case 3:
	        System.out.println("RC4 Encryption");
			String passwordEn = null;
		    String passwordDe = null;


			if(encrypt!=null){      
			       
		    	try {
		    		Integer alInt=al;

		    		//Function call encrypt()
		    		byte[] passEn = firstController.rc4encrypt(password);
		    	    passwordEn = new String(passEn);
			        System.out.println("Encrypted Text : " + passwordEn);
			        System.out.println("alInt : " + alInt);
			        session.setAttribute("alInt",alInt);
			        session.setAttribute("plaintext", password);
					session.setAttribute("result",passwordEn);
					response.sendRedirect("first.jsp");
				} catch (Exception e1) {
					e1.printStackTrace();
				}   
			}
			else if(decrypt!=null){
				try {
		    		Integer alInt=al;

					password = request.getParameter("plaintext");
				    passwordEn = request.getParameter("result");
				    //checking last two digits of encrypted string 
				    String lastTwoDigits =  passwordEn.substring(passwordEn.length() - 2);
				    String lastOneDigits =  passwordEn.substring(passwordEn.length() - 1);

				    System.out.println(lastTwoDigits);
				    
				    if(lastTwoDigits.equals("==") || lastOneDigits.equals("=")){
			    	    JOptionPane.showMessageDialog(null,"Invalid String.","Alert",JOptionPane.WARNING_MESSAGE); 	
			    	    String ased="";
				        session.setAttribute("result", ased);
						response.sendRedirect("first.jsp");			 

				    }
				    else{
					    //Function call decrypt()
						byte[] pasEn = passwordEn.getBytes();
						passwordDe = firstController.rc4decrypt(pasEn);
				        System.out.println("Decrypted Text : " + passwordDe);
				        System.out.println("alInt : " + alInt);
				        session.setAttribute("alInt",alInt);
				        session.setAttribute("plaintext", password);
				        session.setAttribute("result", passwordDe);
						response.sendRedirect("first.jsp");			 

				    }
				    
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else{
		        System.out.println("Error");
			}
	        
	        break;
 
		case 4:
	        System.out.println("MD5 Encryption");
	        final String iv = "0123456789123456"; // This has to be 16 characters
	        final String secretKey = "The secret  a private text here";
	        									

			if(encrypt!=null){      
			       
		    	try {
		    		Integer alInt=al;

		    		//Function call encrypt()
		    		passwordEnc = firstController.md5encrypt(password, iv, secretKey);
			        System.out.println("Encrypted Text : " + passwordEnc);
			        System.out.println("alInt : " + alInt);
			        session.setAttribute("alInt",alInt);
			        session.setAttribute("plaintext", password);
					session.setAttribute("result",passwordEnc);
					response.sendRedirect("first.jsp");
				} catch (Exception e1) {
					e1.printStackTrace();
				}   
			}
			else if(decrypt!=null){
				try {
					
		    		Integer alInt=al;

					password = request.getParameter("plaintext");
				    passwordEnc = request.getParameter("result");
				    String lastTwoDigits =  passwordEnc.substring(passwordEnc.length() - 2);
				    System.out.println(lastTwoDigits);
				    
				    if(lastTwoDigits.equals("==")){
					    System.out.print(lastTwoDigits);
						passwordDec = firstController.md5decrypt(passwordEnc,iv, secretKey);
				        System.out.println("Decrypted Text : " + passwordDec);
				        session.setAttribute("plaintext", password);
				        System.out.println("alInt : " + alInt);
				        session.setAttribute("alInt",alInt);
				        session.setAttribute("result", passwordDec);
						response.sendRedirect("first.jsp");			 

				    }
				    else{
			    	    JOptionPane.showMessageDialog(null,"Invalid String.","Alert",JOptionPane.WARNING_MESSAGE); 	
			    	    String ased="";
				        session.setAttribute("result", ased);
						response.sendRedirect("first.jsp");			 

				    }
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else{
		        System.out.println("Error");
			}
	        
	        break;

		default:
	        System.out.println("Error");
		}
	}
}
