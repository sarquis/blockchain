package com.sqs.cryptocurrency;

import com.sqs.blockchain.Block;
import com.sqs.blockchain.BlockChain;
import com.sqs.constants.Constants;

public class Miner {

    private double reward;

    public void mine(Block block, BlockChain blockChain) {
	// Proof of work (PoW)
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