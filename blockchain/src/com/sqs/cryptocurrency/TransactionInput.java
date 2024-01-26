package com.sqs.cryptocurrency;

public class TransactionInput {

    /**
     * Every input has a output.
     */
    private String transactionOutputId;

    /**
     * Unspent transaction output.
     */
    private TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId) {
	this.transactionOutputId = transactionOutputId;
    }

    public String getTransactionOutputId() {
	return transactionOutputId;
    }

    public TransactionOutput getUTXO() {
	return UTXO;
    }

    public void setUTXO(TransactionOutput uTXO) {
	UTXO = uTXO;
    }

}
