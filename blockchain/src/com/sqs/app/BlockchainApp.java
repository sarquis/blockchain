package com.sqs.app;

import com.sqs.blockchain.Block;
import com.sqs.blockchain.BlockChain;
import com.sqs.constants.Constants;
import com.sqs.cryptocurrency.Miner;

public class BlockchainApp {

    public static void main(String[] args) {

	BlockChain blockChain = new BlockChain();
	Miner miner = new Miner();

	Block block0 = new Block(blockChain.getSize(), "", Constants.GENESIS_PREV_HASH);
	miner.mine(block0, blockChain);

	Block block1 = new Block(blockChain.getSize(), "transaction1", blockChain.getBlockChain().peekLast().getHash());
	miner.mine(block1, blockChain);

	Block block2 = new Block(blockChain.getSize(), "transaction2", blockChain.getBlockChain().peekLast().getHash());
	miner.mine(block2, blockChain);

	System.out.println("\nBLOCKCHAIN:\n" + blockChain);
	System.out.println("Miner's reward:" + miner.getReward());
    }

}
