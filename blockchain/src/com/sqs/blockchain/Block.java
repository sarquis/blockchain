package com.sqs.blockchain;

import java.util.Date;

import com.sqs.helper.CryptographyHelper;

public class Block {

    private int id;
    private int nonce;
    private long timeStamp;
    private String hash;
    private String previousHash;
    private String transaction;

    public Block(int id, String transaction, String previousHash) {
	this.id = id;
	this.transaction = transaction;
	this.previousHash = previousHash;
	this.timeStamp = new Date().getTime();
	generateHash();
    }

    public Block(Block block) {
	id = block.id;
	nonce = block.nonce;
	timeStamp = block.timeStamp;
	hash = block.hash;
	previousHash = block.previousHash;
	transaction = block.transaction;
    }

    public void generateHash() {
	String dataToHash = String.valueOf(id) +
		String.valueOf(timeStamp) +
		String.valueOf(nonce) +
		transaction;
	hash = CryptographyHelper.generateHash(dataToHash);
    }

    public void incrementNonce() {
	nonce++;
    }

    public String getHash() {
	return hash;
    }

    public void setHash(String hash) {
	this.hash = hash;
    }

    public String getPreviousHash() {
	return previousHash;
    }

    @Override
    public String toString() {
	return "Block [id=" + id
	       + ", hash=" + hash
	       + ", previousHash=" + previousHash
	       + ", transaction=" + transaction + "]";
    }

}