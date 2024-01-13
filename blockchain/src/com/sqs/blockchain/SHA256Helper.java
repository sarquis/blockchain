package com.sqs.blockchain;

import java.security.MessageDigest;

public class SHA256Helper {

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
}
