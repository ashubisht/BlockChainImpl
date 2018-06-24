package com.blockchain.impl;

import java.util.Date;

public class TestClazz {
	public static void main(String[] args) throws InterruptedException {
		//Sample to check println (Stream to console) takes lot of time. Thereby increasing mining time
		int max = 50000;
		
		long start1 = new Date().getTime();
		long sum = 0;
		for(int i = 0; i< max; i++) {
			sum+=i;
			//Do nothing
		}
		System.out.println("Time1: " + (new Date().getTime() - start1) + " Sum: " + sum);
		
		Thread.sleep(4000);
		
		sum=0;
		long start2 = new Date().getTime();
		for(int i = 0; i< max; i++) {
			System.out.println(i);
			sum+=i;
		}
		System.out.println("Time2: " + (new Date().getTime() - start2) + " Sum: " + sum);
	}
}
