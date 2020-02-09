package com.ashubisht.blockchainImpl.core;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class MerkleTree {
	
	private List<String> transactions;
	private List<String> merkleRoot;
	
	public MerkleTree(List<String> transactions) {
		this.transactions = transactions;
		this.merkleRoot = this.construct(this.transactions);
	}

	private String concatenatedHash(@NonNull String hash1, @NonNull String hash2) {
		return SHA256.computeHash(hash1+hash2);
	}

	private List<String> construct(@NonNull List<String> txn){
		
		int txnSize = txn.size();
		
		if(txnSize==1) {
			return Collections.singletonList(SHA256.computeHash(txn.get(0)));
		} else if(txn.size()%2==1) {
			txn.add(txn.get(txnSize-1));
		}
		
		List<String> updatedList = new ArrayList<>();
		for(int i =0; i<txn.size()-1; i+=2) {
			updatedList.add(concatenatedHash(txn.get(i), txn.get(i+1)));
		}
		
		return construct(updatedList);
	}
	
}
