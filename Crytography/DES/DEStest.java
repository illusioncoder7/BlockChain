package com.bcClass.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import com.bcClass.DES;

class DEStest {

	@Test
	void test() throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
		DES desObj=new DES();
		System.out.println("enter the secret msg");
		Scanner scanner=new Scanner(System.in);
		String plainText=scanner.nextLine();
		byte[] encrptedObj=desObj.encryption(plainText.getBytes());
		byte[] decrptedObj=desObj.decrption(encrptedObj);
		assertEquals(plainText, new String(decrptedObj));
		
		
	}

}
