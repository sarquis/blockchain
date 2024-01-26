package com.sqs.cryptocurrency;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sqs.blockchain.BlockChain;
import com.sqs.helper.CryptographyHelper;

public class Wallet {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Wallet() {
	KeyPair keyPair = CryptographyHelper.ellipticCurveCrypto();
	privateKey = keyPair.getPrivate();
	publicKey = keyPair.getPublic();
    }

    public Transaction transferMoney(PublicKey receiver, double amount) {
	if (calculateBalance() < amount) {
	    System.out.println("Not enough money.");
	    return null;
	}

	List<TransactionInput> inputs = new ArrayList<>();

	for (Map.Entry<String, TransactionOutput> item : BlockChain.UTXOs.entrySet()) {
	    TransactionOutput UTXO = item.getValue();

	    if (UTXO.isMine(publicKey))
		inputs.add(new TransactionInput(UTXO.getId()));
	}

	Transaction newTrasaction = new Transaction(publicKey, receiver, amount, inputs);
	newTrasaction.generateSignature(privateKey);

	return newTrasaction;
    }

    public double calculateBalance() {
	double balance = 0;

	for (Map.Entry<String, TransactionOutput> item : BlockChain.UTXOs.entrySet()) {
	    TransactionOutput txOutput = item.getValue();
	    if (txOutput.isMine(publicKey))
		balance += txOutput.getAmount();
	}

	return balance;
    }

    public PrivateKey getPrivateKey() {
	return privateKey;
    }

    public PublicKey getPublicKey() {
	return publicKey;
    }

}
