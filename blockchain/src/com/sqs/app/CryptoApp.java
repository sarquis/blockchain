package com.sqs.app;

import java.security.KeyPair;
import java.security.Security;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sqs.helper.CryptographyHelper;

public class CryptoApp {

    public static void main(String[] args) {
	Security.addProvider(new BouncyCastleProvider());

	KeyPair keyPair = CryptographyHelper.ellipticCurveCrypto();

	System.out.println(keyPair.getPrivate().toString());
	System.out.println(keyPair.getPublic().toString());

	System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
	System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
    }
}
