package com.ashubisht.blockchainImpl.core;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @ToString
public class BlockChain {
	
	private final List<Block> blockchain = new ArrayList<>();

	public void addBlock(Block block) {
		this.blockchain.add(block); //Should validate block to have correct hash and prev hash before adding
	}
	
	public int size() {
		return this.blockchain.size();
	}
	
	public Block getLastBlock() {
		return this.blockchain.get(size()-1);
	}
}
