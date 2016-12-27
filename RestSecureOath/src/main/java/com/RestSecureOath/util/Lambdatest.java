package com.RestSecureOath.util;

public class Lambdatest {
	
/*	static void printhello(Hello h){
		h.print("Hello World");
	}*/
	
	/*public static void main(String [] args){		
		Hello h = s -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(s);
			}
			
			};
		//Lambdatest.printhello(h);
		h.print("Hello world");
	}*/

}

@FunctionalInterface
interface Hello{
	
	void print(String s);
}
