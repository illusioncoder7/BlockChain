package com.bcClass.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import com.bcClass.AES;

class AEStest {

	@Test
	void test() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		AES aesObj=new AES();
		System.out.println("enter the key");
		Scanner scanner=new Scanner(System.in);
		String key=scanner.nextLine();
		System.out.println("enter the secret msg");
		String plainText=scanner.nextLine();
		aesObj.KeyGeneration(key);
		byte[] cyppher=aesObj.encrpypt(plainText);
		byte[]text=aesObj.decrypt(cyppher);
		assertEquals(plainText, new String(text));
		
	}

}
