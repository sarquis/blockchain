package com.sqs.helper;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class CryptographyHelper {

    /**
     * SHA-256 hash (256 bits = 64 hexadecimal characters)
     */
    public static String generateHash(String data) {
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hash = digest.digest(data.getBytes("UTF-8"));

	    StringBuilder hexadecimalString = new StringBuilder();
	    for (byte b : hash) {

		/*
		 * 0xff & b: Isso é uma operação de máscara bitwise que garante que o byte b
		 * seja interpretado como um valor positivo. Ao realizar 0xff & b, você está
		 * essencialmente zerando os bits superiores e preservando apenas os últimos 8
		 * bits do byte. Isso é feito para garantir que o byte seja tratado como um
		 * número sem sinal.
		 */

		/*
		 * String.format("%02x", ...): Aqui, você está usando String.format para
		 * formatar o valor resultante como uma string hexadecimal. %02x especifica que
		 * a string resultante deve ter pelo menos dois caracteres, preenchendo com
		 * zeros à esquerda se necessário.
		 */

		String hexadecimal = String.format("%02x", 0xff & b); // Garantindo o 64 lenght.
		hexadecimalString.append(hexadecimal);
	    }

	    return hexadecimalString.toString();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Elliptic-curve cryptography (ECC) to sign the given transaction.
     */
    public static byte[] sign(PrivateKey privateKey, String input) {
	try {
	    // 1. (ECDSA) Elliptic Curve Digital Signature Algorithm.
	    // 2. (BC) Bouncy Castle
	    Signature signature = Signature.getInstance("ECDSA", "BC");

	    signature.initSign(privateKey);
	    signature.update(input.getBytes());
	    byte[] output = signature.sign();

	    return output;
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Checks whether the given transaction belongs to the sender based on the
     * signature.
     */
    public static boolean verify(PublicKey publicKey, String data, byte[] signature) {
	try {
	    // 1. (ECDSA) Elliptic Curve Digital Signature Algorithm.
	    // 2. (BC) Bouncy Castle
	    Signature signatureCheck = Signature.getInstance("ECDSA", "BC");
	    signatureCheck.initVerify(publicKey);
	    signatureCheck.update(data.getBytes());
	    return signatureCheck.verify(signature);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    /**
     * Generate public key and private key.
     * 
     * Private Key: 256 bits long random integer.
     * 
     * Public Key: point on the elliptic curve.
     * 
     * Point: (x,y) - both of these values are 256 bits long.
     */
    public static KeyPair ellipticCurveCrypto() {
	try {
	    KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
	    ECGenParameterSpec params = new ECGenParameterSpec("prime256v1");
	    kpg.initialize(params);
	    return kpg.generateKeyPair();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
