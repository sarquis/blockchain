package com.sqs.blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sqs.constants.Constants;
import com.sqs.cryptocurrency.Transaction;
import com.sqs.helper.CryptographyHelper;

public class Block {

    private int id;
    private int nonce;
    private long timeStamp;
    private String hash;
    private String previousHash;

    // Ethereum every block stores 1500-2000 transactions.
    private List<Transaction> transactions;

    public Block(String previousHash) {
	transactions = new ArrayList<>();
	this.previousHash = previousHash;
	this.timeStamp = new Date().getTime();
	generateHash();
    }

    public void generateHash() {
	String dataToHash = String.valueOf(id) +
		String.valueOf(timeStamp) +
		transactions.toString() +
		String.valueOf(nonce);
	hash = CryptographyHelper.generateHash(dataToHash);
    }

    public boolean addTransaction(Transaction transaction) {
	if (transaction == null)
	    return false;

	if (!previousHash.equals(Constants.GENESIS_PREV_HASH)
		&& !transaction.verifyTransaction()) {
	    System.out.println("Transation is not valid...");
	    return false;
	}

	transactions.add(transaction);
	System.out.println("Transaction is valid and it's added to the block " + this);
	return true;
    }

    public void incrementNonce() {
	nonce++;
    }

    // SHA-256 identify the block.
    public String getHash() {
	return hash;
    }

    public String getPreviousHash() {
	return previousHash;
    }

    @Override
    public String toString() {
	return "Block [id=" + id
	       + ", hash=" + hash
	       + ", previousHash=" + previousHash
	       + ", transactions=" + transactions.toString() + "]";
    }

}