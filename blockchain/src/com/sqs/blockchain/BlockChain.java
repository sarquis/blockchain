package com.sqs.blockchain;

import java.util.LinkedList;

public class BlockChain {

    /*
     * immutable ledger
     * 
     * not able to remove blocks
     */
    private LinkedList<Block> blockChain;

    public BlockChain() {
	blockChain = new LinkedList<>();
    }

    public void addBlock(Block block) {
	blockChain.add(block);
    }

    // Ensuring the immutability of the blockchain and its constituent blocks.
    public LinkedList<Block> getBlockChain() {
	LinkedList<Block> copy = new LinkedList<>();
	blockChain.forEach(b -> copy.add(new Block(b)));
	return copy;
    }

    public int getSize() {
	return this.blockChain.size();
    }

    @Override
    public String toString() {
	StringBuilder s = new StringBuilder();
	blockChain.forEach(b -> s.append(b.toString() + "\n"));
	return s.toString();
    }

}
