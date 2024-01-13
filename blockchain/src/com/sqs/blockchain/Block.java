package com.sqs.blockchain;

import java.util.Date;

public class Block {

    private int id;
    private int nonce;
    private long timeStamp;
    private String hash;
    private String previousHash;
    private String transaction;

    public Block(int id, String previousHash, String transaction) {
	this.id = id;
	this.previousHash = previousHash;
	this.transaction = transaction;
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
	hash = SHA256Helper.generateHash(dataToHash);
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