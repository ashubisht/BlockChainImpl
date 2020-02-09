package com.ashubisht.blockchainImpl.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
public class Block{
	
	private final int blockId;
	private final String transaction, prevHash;
	private int nonce;
	private long timeStamp;
	private String hash;
	
	public Block(Block block) {
		this.blockId = block.blockId;
		this.transaction = block.transaction;
		this.prevHash = block.prevHash;
		this.timeStamp = block.timeStamp;
		this.hash = block.hash;
		this.nonce = block.nonce;
	}

	public Block(int blockId, String transaction, String prevHash){
		this.blockId = blockId;
		this.transaction = transaction;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		this.hash = this.generateHash();
		this.nonce = 0;
	}

	//Separated method to be recalled after populating fields, if block not created via constructor
	public String generateHash() {
		String hash = SHA256.computeHash(""+ this.blockId+ this.nonce + this.timeStamp+ this.prevHash +this.transaction);
		this.hash = hash;
		return hash;
	}

	public void incrementNonce() {
		this.nonce++;		
	}

	public void incrementNonceBy(int incrementer) {
		this.nonce += incrementer;
	}
	
}
