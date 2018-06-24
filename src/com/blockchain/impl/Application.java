package com.blockchain.impl;

public class Application {
	
	public static void main(String[] args) {
		BlockChain blockChain = new BlockChain();
		Miner miner = new Miner();
		
		Block genesis = new Block(0, "blankTxn", Constants.GENESIS_PREV_HASH);
		miner.mine(genesis, blockChain);
		
		Block blk1 = new Block(1, "txn1", blockChain.getLastBlock().getHash());
		miner.mine(blk1, blockChain);
		
		Block blk2 = new Block(2, "txn2", blockChain.getLastBlock().getHash());
		miner.mine(blk2, blockChain);
		
		System.out.println(blockChain);
	}

}
