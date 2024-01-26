package com.sqs.app;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sqs.blockchain.Block;
import com.sqs.blockchain.BlockChain;
import com.sqs.constants.Constants;
import com.sqs.cryptocurrency.Miner;
import com.sqs.cryptocurrency.Transaction;
import com.sqs.cryptocurrency.TransactionOutput;
import com.sqs.cryptocurrency.Wallet;

public class BlockchainApp {

    public static void main(String[] args) {

	Security.addProvider(new BouncyCastleProvider());

	Wallet userA = new Wallet();
	Wallet userB = new Wallet();
	Wallet lender = new Wallet();

	BlockChain blockChain = new BlockChain();
	Miner miner = new Miner();

	Transaction genesisTransaction = new Transaction(lender.getPublicKey(), userA.getPublicKey(), 500, null);
	genesisTransaction.generateSignature(lender.getPrivateKey());
	genesisTransaction.setTransactionId("0");
	genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.getReceiver(),
		genesisTransaction.getAmount(), genesisTransaction.getTransactionId()));

	BlockChain.UTXOs.put(genesisTransaction.outputs.get(0).getId(), genesisTransaction.outputs.get(0));

	System.out.println("Constructing the genesis block...");
	Block genesis = new Block(Constants.GENESIS_PREV_HASH);
	genesis.addTransaction(genesisTransaction);
	miner.mine(genesis, blockChain);

	Block block1 = new Block(genesis.getHash());
	System.out.println("\n userA's balance is: " + userA.calculateBalance());
	System.out.println("\n userA's tries to send money (120 coins) to userB...");
	block1.addTransaction(userA.transferMoney(userB.getPublicKey(), 120));
	miner.mine(block1, blockChain);
	System.out.println("\n userA's balance is: " + userA.calculateBalance());
	System.out.println("\n userB's balance is: " + userB.calculateBalance());

	Block block2 = new Block(block1.getHash());
	System.out.println("\n userA tries to send money (600 coins) to userB... (more than it has)");
	block2.addTransaction(userA.transferMoney(userB.getPublicKey(), 600));
	miner.mine(block2, blockChain);
	System.out.println("\n userA's balance is: " + userA.calculateBalance());
	System.out.println("\n userB's balance is: " + userB.calculateBalance());

	Block block3 = new Block(block1.getHash());
	System.out.println("\n userB tries to send money (110 coins) to userA...");
	block3.addTransaction(userB.transferMoney(userA.getPublicKey(), 110));
	System.out.println("\n userA's balance is: " + userA.calculateBalance());
	System.out.println("\n userB's balance is: " + userB.calculateBalance());
	miner.mine(block3, blockChain);

	System.out.println("Miner's reward: " + miner.getReward());
    }

}
