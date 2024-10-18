package com.sqs.app;

import java.util.ArrayList;
import java.util.List;

import com.sqs.helper.CryptographyHelper;
// Teste para disparar o Git Actions.
public class MerkleTreeApp {

    private List<String> transactions;

    private MerkleTreeApp(List<String> transactions) {
	this.transactions = transactions;
    }

    private String getMerkleRoot() {
	return construct(transactions).get(0);
    }

    private List<String> construct(List<String> transactions) {
	if (transactions.size() == 1)
	    return transactions;

	List<String> updatedList = new ArrayList<>();

	for (int i = 0; i < transactions.size() - 1; i += 2)
	    updatedList.add(mergeHash(transactions.get(i), transactions.get(i + 1)));

	// If the number of transactions is odd. (Hc + Hc)
	if (transactions.size() % 2 == 1)
	    updatedList.add(mergeHash(
		    transactions.get(transactions.size() - 1),
		    transactions.get(transactions.size() - 1)));

	return construct(updatedList);
    }

    private String mergeHash(String hash1, String hash2) {
	String mergedHash = hash1 + hash2;
	return CryptographyHelper.generateHash(mergedHash);
    }

    public static void main(String[] args) {
	List<String> transactions = new ArrayList<>();
	transactions.add("aa");
	transactions.add("bb");
	transactions.add("cc");

	MerkleTreeApp merkleTreeApp = new MerkleTreeApp(transactions);
	System.out.println(merkleTreeApp.getMerkleRoot());
    }

}
