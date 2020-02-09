package com.ashubisht.blockchainImpl.core;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Constants {
	
	private Constants() {}
	
	public static final Integer DIFFICULTY =5;
	public static final String GENESIS_PREV_HASH = IntStream.range(0, 64).mapToObj(i -> "0").collect(Collectors.joining(""));
	public static final Double REWARD = 10D;
	
}
