package com.bcClass;

//package com.blockChain;

import java.security.InvalidKeyException;

//import org.apache.commons.codec.binary.Base64;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

public class DES {
	SecretKey key;
	public DES(byte[] password) {
		  key=new SecretKeySpec(password,"DES");
	
		}
	public DES() throws NoSuchAlgorithmException
	{
		KeyGenerator generator=KeyGenerator.getInstance("DES");
		key=generator.generateKey();
		
	}
	
	public byte[] encryption(byte[] plaintext) throws NoSuchAlgorithmException,BadPaddingException,IllegalBlockSizeException,NoSuchPaddingException, InvalidKeyException
	{
		Cipher cipher =Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE,key);
		byte[] encrptedMsg=cipher.doFinal(plaintext);
		String encodedResult=Base64.toBase64String(encrptedMsg);
		System.out.println("Encrpted message  "+new String(encrptedMsg));
		System.out.println("encoded  "+ encodedResult);
		
		return encrptedMsg;
		
	}
	public byte[] decrption(byte[]encrptedText)throws NoSuchAlgorithmException,BadPaddingException,IllegalBlockSizeException,NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
	{
		Cipher desCipher =Cipher.getInstance("DES/ECB/PKCS5Padding");
		desCipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrytedMsg=desCipher.doFinal(encrptedText);
		System.out.println("decrypted is "+ new String(decrytedMsg));
		return decrytedMsg;
	}
	

	

}
