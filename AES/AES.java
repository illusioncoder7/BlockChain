package com.bcClass;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

public class AES {
	IvParameterSpec ivParaSpec;
	SecretKeySpec secretKey;
	
	public void KeyGeneration(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
	int ivSize=16;
	byte [] iv=new byte[ivSize];
	SecureRandom random=new SecureRandom();
	random.nextBytes(iv);
	 ivParaSpec=new IvParameterSpec(iv);
	 MessageDigest digest=MessageDigest.getInstance("SHA-256");
	 digest.update(key.getBytes("UTF-8"));
	 byte[] keyBytes= new byte[16];
	 System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
	 secretKey=new SecretKeySpec(keyBytes, "AES");
	 }
	public byte[] encrpypt(String message) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] mess=message.getBytes();
		Cipher cipher=Cipher.getInstance("AES/OFB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivParaSpec);
		byte[] encrypted= cipher.doFinal(mess);
		String encode=Base64.toBase64String(encrypted);
		System.out.println("encrpted  "+ new String(encrypted));
		System.out.println("encodec   "+ encode);
		return encrypted;
	}
	public byte[] decrypt(byte[] cyphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException 
	
	{
		Cipher cipher=Cipher.getInstance("AES/OFB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey,ivParaSpec);
		byte [] decrypted =cipher.doFinal(cyphertext);
		System.out.println("decrpted  "+ new String(decrypted));
		//System.out.println("encodec   "+ encode);
		return decrypted;
		
		
	}
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		AES newAes=new AES();
		newAes.KeyGeneration("thenewworld");
		byte[] encrpted=newAes.encrpypt("encrpt me");
		byte[] decrpted=newAes.decrypt(encrpted);
		
		
		
	}
	}
	


