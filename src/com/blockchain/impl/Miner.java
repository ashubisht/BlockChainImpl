package com.blockchain.impl;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Miner{
	
	private double reward;
	private long timeToHash;
	private boolean doneHash = false;
	private int nonce;
	
	public void mine(Block block, BlockChain blockChain) {
		
		this.doneHash=false;
		int logicalCores = Runtime.getRuntime().availableProcessors(); //Trying optimum multithreading for nonce finding
		//System.out.println("Logical core: " + logicalCores);
		
		long startTime = new Date().getTime();
		
		//Start of mining
		
		ExecutorService executorService = Executors.newFixedThreadPool(logicalCores);
		
		for(int i = 0; i< logicalCores; i++) {
			
			final Block tempBlock = new Block(block);
			tempBlock.setNonce(i);
			//final int threadIndex = i;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					//System.out.println("Inside run method");
					tempBlock.generateHash();
					while(notGoldenHash(tempBlock) && !doneHash) {
						//if(tempBlock.getNonce()>50) return; //For testing only
						tempBlock.incrementNonceBy(logicalCores);
						//System.out.println("Trying to generate hash by thread: "+ threadIndex + " nonceValue: "+ tempBlock.getNonce());
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

	public double getReward() {
		return reward;
	}

}
