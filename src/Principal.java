import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class Principal {
	

	public static void main(String[] args) throws IOException {
		boolean checked = false;
		String usuario;
		String contraseña;
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
		Usuarios u1 = new Usuarios("Carlos", getSHA512("123"));
		Usuarios u2 = new Usuarios("Iñigo", getSHA512("1234"));
		Usuarios u3 = new Usuarios("Alfredo", getSHA512("12345"));
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		listaUsuarios.add(u3);
		
		int intentos = 3;
		while(intentos  !=0 && !checked) {
			System.out.println("Escribe el usuario: ");
    		usuario = sc.next();
    		System.out.println("Escribe la contraseña: ");
    		contraseña = sc.next();
			String entradaContraseñaHash = getSHA512(contraseña);
        	for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuarios usuarios = listaUsuarios.get(i);
                
        		if (usuario.equals(usuarios.getUsuario()) && entradaContraseñaHash.equals(usuarios.getContraseña())) {
        			checked = true;
    				System.out.println("El usuario existe y contraseña correcta");
    			}
        	}
        	if(!checked) {
        	intentos--;
			System.out.println("Usuario o contraseña incorrectos, quedan " + intentos + " intentos");
        	}
	    }
			
		if(intentos == 0) {
			System.out.println("Cerrando aplicacion...");
            sc.close();
        	}
		
        if (checked) {
        	try {
    			int opcion = 0;
    			String mensaje;
    			String mensajeCifrado;
    			byte[] bytesMensaje;
    			byte[] bytesMensajeCifrado = null;
    			byte[] bytesMensajeDescifrado;
    			KeyGenerator generador = KeyGenerator.getInstance("DES");
    			SecretKey edix = generador.generateKey();
    			Cipher cifrador = Cipher.getInstance("DES");
    			
    			do{
    			  System.out.println("#####Iniciando Programa#####");
    			  System.out.println("0- Salir del programa");     
    			  System.out.println("1- Encriptar frase");   
    			  System.out.println("2- Desencriptar frase");   
    			  opcion = sc.nextInt();
    			  switch (opcion) { 
      			  	case 0: 
    			  		opcion = 0;
    			  		System.out.println("Cerrando aplicacion...");
    		            sc.close();
    			  		break;
    			    case 1:
    			    	System.out.println("Escribe la frase para encriptar");
    			    	mensaje = sc.nextLine();
    			    	mensaje = sc.nextLine();
    			    	System.out.println("Encriptando frase...");
    			    	cifrador.init(Cipher.ENCRYPT_MODE, edix);
    			    	bytesMensaje = mensaje.getBytes();
    			    	bytesMensajeCifrado = cifrador.doFinal(bytesMensaje);
    			    	mensajeCifrado = new String(bytesMensajeCifrado);
    			    	System.out.println("Mensaje original: " + mensaje);
    			    	System.out.println("El mensaje ha sido cifrado: " + mensajeCifrado);
    			     break;
    			    case 2:
    			    	System.out.println("Desencriptando frase...");
    			    	cifrador.init(Cipher.DECRYPT_MODE, edix);
    			    	bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
    			    	System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
    			     break;
    			    default:
    			    	System.out.println("Las opciones validas son 0,1 y 2");
    			  }
    			}
    			while(opcion != 0);
    			
    			
    		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
    			System.out.println("Error al crear y configurar el descifrador");
    			System.out.println(e.getMessage());
    		} catch (IllegalBlockSizeException | BadPaddingException e) {
    			System.out.println("Error al cifrar el mensaje");
    			System.out.println(e.getMessage());
    		}
    	}
    }
	
    private static String getSHA512(String datos) {
        String resultado = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[]  datosHash = digest.digest(datos.getBytes());
            String datosHashString = Base64.getEncoder().encodeToString(datosHash);
            return datosHashString;
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }	
	
}
	

