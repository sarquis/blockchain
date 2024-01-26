package com.sqs.cryptocurrency;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import com.sqs.blockchain.BlockChain;
import com.sqs.helper.CryptographyHelper;

public class Transaction {

    // (SHA-256)
    private String transactionId;

    private PublicKey sender;
    private PublicKey receiver;

    private double amount;

    private byte[] signature;

    public List<TransactionInput> inputs;
    public List<TransactionOutput> outputs;

    public Transaction(PublicKey sender, PublicKey receiver,
	    double amount, List<TransactionInput> inputs) {
	this.inputs = inputs;
	this.outputs = new ArrayList<TransactionOutput>();
	this.sender = sender;
	this.receiver = receiver;
	this.amount = amount;
	calculateHash();
    }

    public boolean verifyTransaction() {
	if (!verifySignature()) {
	    System.out.println("Invalid transaction because of invalid signature.");
	    return false;
	}

	for (TransactionInput txInput : inputs)
	    txInput.setUTXO(BlockChain.UTXOs.get(txInput.getTransactionOutputId()));

	// Send value to recipient.
	outputs.add(new TransactionOutput(receiver, amount, transactionId));
	// Send the left over 'change' back to sender.
	outputs.add(new TransactionOutput(sender, getInputSum() - amount, transactionId));

	// Update UTXOs.
	for (TransactionOutput txOutput : outputs)
	    BlockChain.UTXOs.put(txOutput.getId(), txOutput);

	// Remove from UTXOs list because they've been spent.
	for (TransactionInput txInput : inputs)
	    if (txInput.getUTXO() != null)
		BlockChain.UTXOs.remove(txInput.getUTXO().getId());

	return true;
    }

    // Calculate how much money the sender has.
    private double getInputSum() {
	double sum = 0;

	for (TransactionInput txInput : inputs)
	    if (txInput.getUTXO() != null)
		sum += txInput.getUTXO().getAmount();

	return sum;
    }

    public void generateSignature(PrivateKey privateKey) {
	signature = CryptographyHelper.sign(privateKey, getPrimaryData());
    }

    public boolean verifySignature() {
	return CryptographyHelper.verify(sender, getPrimaryData(), signature);
    }

    private void calculateHash() {
	transactionId = CryptographyHelper.generateHash(getPrimaryData());
    }

    private String getPrimaryData() {
	return sender.toString() + receiver.toString() + String.valueOf(amount);
    }

    public byte[] getSignature() {
	return signature;
    }

    public List<TransactionOutput> getOutputs() {
	return outputs;
    }

    public void setOutputs(List<TransactionOutput> outputs) {
	this.outputs = outputs;
    }

    public String getTransactionId() {
	return transactionId;
    }

    public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
    }

    public PublicKey getSender() {
	return sender;
    }

    public PublicKey getReceiver() {
	return receiver;
    }

    public double getAmount() {
	return amount;
    }

    public List<TransactionInput> getInputs() {
	return inputs;
    }

}
