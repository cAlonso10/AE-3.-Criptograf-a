import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class main {
	
	
	
	public static void main(String[] args) throws IOException {
		try {
			int opcion = 0;
			Scanner sc = new Scanner(System.in);
			String mensaje;
			String mensajeCifrado;
			byte[] bytesMensaje;
			byte[] bytesMensajeCifrado = null;
			byte[] bytesMensajeDescifrado;
			KeyGenerator generador = KeyGenerator.getInstance("DES");
			SecretKey edix = generador.generateKey();
			Cipher cifrador = Cipher.getInstance("DES");
			
			do{
			  System.out.println("#####################################");
			  System.out.println("0- Salir del programa");     
			  System.out.println("1- Encriptar frase");   
			  System.out.println("2- Desencriptar frase");   
			  opcion = sc.nextInt();
			  switch (opcion) { 
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
					System.out.println("Saliendo del programa...");
			
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			System.out.println("Error al crear y configurar el descifrador");
			System.out.println(e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error al cifrar el mensaje");
			System.out.println(e.getMessage());
		}
	}
}