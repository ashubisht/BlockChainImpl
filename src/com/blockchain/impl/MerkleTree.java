package com.blockchain.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MerkleTree {
	
	private List<String> transactions;
	private List<String> merkleRoot;
	
	public MerkleTree(List<String> transactions) {
		this.transactions = transactions;
		this.merkleRoot = this.construct(this.transactions);
	}

	public List<String> getTransactions() {
		return transactions;
	}
	
	public List<String> getMerkleRoot() {
		return merkleRoot;
	}

	private List<String> construct(List<String> txn){
		
		int txnSize = txn.size();
		
		if(txnSize==1) {
			return Arrays.asList(new String[] {SHA256Runner.hash(txn.get(0))});
		}else if(txn.size()%2==1) {
			txn.add(txn.get(txnSize-1));
		}
		
		List<String> updatedList = new ArrayList<>();
		for(int i =0; i<txn.size()-1; i+=2) {
			updatedList.add(concatenatedHash(txn.get(i), txn.get(i+1)));
		}
		
		return construct(updatedList);
	}
	
	private String concatenatedHash(String hash1, String hash2) {
		return SHA256Runner.hash(hash1+hash2);
	}
	
	
	public static void main(String[] args) {
		List<String> txns = new ArrayList<>();
		txns.add("aa");
		txns.add("bb");
		
		MerkleTree merkleTree = new MerkleTree(txns);
		System.out.println(merkleTree.getMerkleRoot());
	}
	
}
