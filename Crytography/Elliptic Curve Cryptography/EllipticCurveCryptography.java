package com.bcClass;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Scanner;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.util.encoders.Base64;

public class EllipticCurveCryptography {
	 

	
   	PublicKey pubkey;
	PrivateKey privkey;
	//constructor for the key generation 
	 public EllipticCurveCryptography() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException 
	 {
		KeyPairGenerator generator=KeyPairGenerator.getInstance("EC","BC");
		generator.initialize(new ECGenParameterSpec("secp256r1"));
	    KeyPair keyPair=generator.generateKeyPair();
	    pubkey=keyPair.getPublic();
	    privkey=keyPair.getPrivate();
	     }
	 //function for the encryption
	 public byte[] encryption(String plainText) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
		 {
		 String algorithm="ECIES";
		 Cipher cipher=Cipher.getInstance(algorithm,"BC");
		 cipher.init(Cipher.ENCRYPT_MODE, pubkey);
		 byte[] cypherText=cipher.doFinal(plainText.getBytes());
		 String encoded=Base64.toBase64String(cypherText);
		 System.out.println("Encrpted ---->   "+ new String(cypherText));
		 System.out.println("Encoded is     "+ encoded);
		 System.out.println("public key is   "+pubkey);
		 System.out.println("Private key is  "+privkey); 
		 
		 return cypherText;
		 }
	 
		 
		 public byte [] decryption(byte[] cypherText) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
		 {
			 String algorithm="ECIES";
			 Cipher cipher=Cipher.getInstance(algorithm,"BC");
			 cipher.init(Cipher.DECRYPT_MODE, privkey);
			 byte[] message=cipher.doFinal(cypherText);
			 System.out.println("Decrpted is:   "+ new String(message));
			 return message;
			 
		 }
		 public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
			EllipticCurveCryptography eccObj=new EllipticCurveCryptography();
			Scanner scanner=new Scanner(System.in);
			String message=scanner.nextLine();
			
			//String message="A quick brown fox jumos over the lazy dog";
			byte[] cypher=eccObj.encryption(message);
			byte[] orgi=eccObj.decryption(cypher);
			
			
		}
	 
	

}
