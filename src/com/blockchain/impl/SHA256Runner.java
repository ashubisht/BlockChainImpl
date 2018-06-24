package com.blockchain.impl;

import java.security.MessageDigest;

public class SHA256Runner {
	
	public static String hash(String data){
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(data.getBytes());
			byte[] hashArray = md.digest();
			StringBuffer buf = new StringBuffer();
			for(byte hash : hashArray) {
				String hex = Integer.toHexString(0xff & hash); //Convert first 24 bits to 0 in 32 bit int system
				if(hex.length() ==1) {
					buf.append("0");
				}
				buf.append(hex); //2 digit hex number
			}
			return buf.toString();
		}catch(Exception e) {
			System.out.println("Exception occurred: "+ e.getMessage() + System.lineSeparator() + "Stacktrace: " + e);
			throw new RuntimeException(e); // Runtime exception thus, doesnt need to be specify at function prototype level
		}
		
	}

}
