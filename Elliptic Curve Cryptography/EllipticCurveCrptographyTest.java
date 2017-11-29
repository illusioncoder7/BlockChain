package com.bcClass.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import com.bcClass.EllipticCurveCryptography;

class EllipticCurveCrptographyTest {

	@Test
	void test() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		EllipticCurveCryptography ECCtest=new EllipticCurveCryptography();
		Scanner scanner=new Scanner(System.in);
		String message=scanner.nextLine();
        byte[] encrpted=ECCtest.encryption(message);
        byte[] decrpted=ECCtest.decryption(encrpted);
        assertEquals(message,new String (decrpted));
        
        
	}

}
