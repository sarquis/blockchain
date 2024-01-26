package com.sqs.cryptocurrency;

import com.sqs.blockchain.Block;
import com.sqs.blockchain.BlockChain;
import com.sqs.constants.Constants;

public class Miner {

    // Every miner gets 6.25 BTC after mining.
    private double reward;

    public void mine(Block block, BlockChain blockChain) {
	// Proof of work (PoW)
	// PoW is not energy efficient, PoS is a better approach.
	while (!isGoldenHash(block)) {
	    block.incrementNonce();
	    block.generateHash();
	}

	System.out.println(block + " has just mined...");
	System.out.println("Hash is: " + block.getHash());

	blockChain.addBlock(block);
	reward += Constants.REWARD;
    }

    private boolean isGoldenHash(Block block) {
	String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace("\0", "0");
	return block.getHash().substring(0, Constants.DIFFICULTY).equals(leadingZeros);
    }

    public double getReward() {
	return reward;
    }

}
