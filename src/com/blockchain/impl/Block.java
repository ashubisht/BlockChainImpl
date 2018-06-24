package com.blockchain.impl;

import java.util.Date;

public class Block{
	
	private int blockId;
	private int nonce;
	private long timeStamp;
	private String hash;
	private String prevHash;
	private String transaction;
	
	public Block(Block block) {
		this.blockId = block.getBlockId();
		this.transaction = block.getTransaction();
		this.prevHash = block.getPrevHash();
		this.timeStamp = block.getTimeStamp();
		this.hash = block.getHash();
		this.nonce = block.getNonce();
	}
		
	public Block(int blockId, String transaction, String prevHash) {
		this.blockId = blockId;
		this.transaction = transaction;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		this.nonce = 0; //Should be default value of int
		this.hash = generateHash();
	}

	public String generateHash() { //Separated method to be recalled after populating fields, if block not created via constructor
		String hash = SHA256Runner.hash(""+ this.blockId+ this.nonce + this.timeStamp+ this.prevHash +this.transaction);
		//System.out.println("Temp hashing block: "+ this.blockId + " Hash: " + hash);
		this.hash = hash;
		return hash;
	}

	@Override
	public String toString() {
		return  System.lineSeparator() + "Block [blockId=" + blockId + ", nonce=" + nonce + ", timeStamp=" + timeStamp + ", hash=" + hash
				+ ", prevHash=" + prevHash + ", transaction=" + transaction + "]";
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public void incrementNonce() {
		this.nonce++;		
	}
	
	public void incrementNonceBy(int incrementor) {
		this.nonce += incrementor;
	}
	
}
