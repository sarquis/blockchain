package com.sqs.blockchain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.sqs.cryptocurrency.TransactionOutput;

public class BlockChain {

    /*
     * immutable ledger
     * 
     * not able to remove blocks
     */
    private LinkedList<Block> blockChain;

    public static Map<String, TransactionOutput> UTXOs;

    public BlockChain() {
	blockChain = new LinkedList<>();
	UTXOs = new HashMap<>();
    }

    public void addBlock(Block block) {
	blockChain.add(block);
    }

    public int getSize() {
	return blockChain.size();
    }

    @Override
    public String toString() {
	StringBuilder s = new StringBuilder();
	blockChain.forEach(b -> s.append(b.toString() + "\n"));
	return s.toString();
    }

}
