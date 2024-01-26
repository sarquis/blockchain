package com.sqs.cryptocurrency;

import java.security.PublicKey;
import java.util.Arrays;

import com.sqs.helper.CryptographyHelper;

public class TransactionOutput {

    // transaction's identifier (SHA-256).
    private String id;

    private String parentTransactionId;

    private PublicKey receiver;

    private double amount;

    public TransactionOutput(PublicKey receiver, double amount, String parentTransactionId) {
	this.receiver = receiver;
	this.amount = amount;
	this.parentTransactionId = parentTransactionId;
	generateId();
    }

    private void generateId() {
	id = CryptographyHelper.generateHash(
		receiver.toString() + String.valueOf(amount) + parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey) {
	return Arrays.equals(publicKey.getEncoded(), receiver.getEncoded());
    }

    public String getId() {
	return id;
    }

    public String getParentTransactionId() {
	return parentTransactionId;
    }

    public PublicKey getReceiver() {
	return receiver;
    }

    public double getAmount() {
	return amount;
    }

}
