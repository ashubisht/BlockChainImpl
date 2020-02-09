package com.ashubisht.blockchainImpl.core;

import lombok.Getter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Miner{
	
	@Getter
	private double reward;
	private long timeToHash;
	private boolean doneHash = false;
	private int nonce;
	
	public void mine(Block block, BlockChain blockChain) {
		
		this.doneHash=false;
		int logicalCores = Runtime.getRuntime().availableProcessors(); //Trying optimum multithreading for nonce finding
		long startTime = new Date().getTime();
		
		//Start of mining
		ExecutorService executorService = Executors.newFixedThreadPool(logicalCores);
		
		for(int i = 0; i< logicalCores; i++) {
			final Block tempBlock = new Block(block);
			tempBlock.setNonce(i);
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					tempBlock.generateHash();
					while(notGoldenHash(tempBlock) && !doneHash) {
						tempBlock.incrementNonceBy(logicalCores);
						tempBlock.generateHash();
					}
					
					if(!doneHash) {//Found nonce by this thread else found nonce by some other thread
						nonce = tempBlock.getNonce();
						System.out.println("Found nonce: "+ nonce + " Net hash: " + tempBlock.generateHash());
						doneHash = true;
						executorService.shutdown();					
					}
				}
			});
		}
		
		try{
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		}catch(Exception e) {
			System.out.println("Unable to mine nonce value");
			throw new RuntimeException(e);
		}
		
		//End of mining
		timeToHash = new Date().getTime() - startTime;
		timeToHash /= 1000; //Conversion to second
		
		block.setNonce(nonce);
		block.generateHash();
		System.out.println("Block mined. Hash: " + block.getHash() +
				System.lineSeparator() + "Time to hash: " + timeToHash + " sec");
		blockChain.addBlock(block);
		reward += Constants.REWARD;
		
	}

	public boolean notGoldenHash(Block block) {
		String leadingZeroes = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
		return !block.getHash().substring(0, Constants.DIFFICULTY).equals(leadingZeroes);
	}

}
