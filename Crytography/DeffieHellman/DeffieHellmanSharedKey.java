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

import javax.crypto.KeyAgreement;

import org.bouncycastle.util.encoders.Base64;

public class DeffieHellmanSharedKey {
	private static final String provider="BC";
	public KeyPair keyGen() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException
	{
		KeyPairGenerator kpg=KeyPairGenerator.getInstance("ECDH",provider);
		ECGenParameterSpec ecParamS=new ECGenParameterSpec("secp256k1");
		kpg.initialize(ecParamS);
		KeyPair keyPair=kpg.generateKeyPair();
		return keyPair;
	}
		
	public String SharedSecret(PrivateKey privKey,PublicKey pubKey) throws NoSuchAlgorithmException, InvalidKeyException
	 {
	  KeyAgreement ecdh=KeyAgreement.getInstance("ECDH");
	  ecdh.init(privKey);
	  ecdh.doPhase(pubKey, true);
	  String SharedSecret=Base64.toBase64String(ecdh.generateSecret());
	  return SharedSecret;
	   }
	 public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException {
		 DeffieHellmanSharedKey Ram=new DeffieHellmanSharedKey();
		 DeffieHellmanSharedKey Sita=new DeffieHellmanSharedKey();
		 KeyPair RamKeys=Ram.keyGen();
		 KeyPair SitaKeys=Sita.keyGen();
		 String SSRam=Ram.SharedSecret(RamKeys.getPrivate(), SitaKeys.getPublic());
		 String SSSita=Sita.SharedSecret(SitaKeys.getPrivate(),RamKeys.getPublic());
		 System.out.println("Ram's shared secret is---->  "+ SSRam);
		 System.out.print("Sita's shared secret is------."+SSSita);
		
	}

}
