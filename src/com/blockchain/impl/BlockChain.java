package com.blockchain.impl;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
	
	private List<Block> blockchain;
	
	public BlockChain() {
		this.blockchain = new ArrayList<>();
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}
	
	public void addBlock(Block block) {
		this.blockchain.add(block); //Should validate block to have correct hash and prev hash before adding
	}
	
	public int size() {
		return this.blockchain.size();
	}
	
	public Block getLastBlock() {
		return this.blockchain.get(size()-1);
	}

	@Override
	public String toString() {
		return "BlockChain [blockchain=" + blockchain + "]";
	}
	
}
