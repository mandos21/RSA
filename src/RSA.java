// Project 3
// Driver and class methods for RSA Public Key cryptography
// As Provided by Prof. Bergmann

import java.io.*;
import java.math.*;
import java.util.*;	// Random number generator

public class RSA
{
	public static void main (String args[])
	{

		Person Alice = new Person();
		Person Bob = new Person();
	
		String msg = new String ("Bob, let's have lunch."); 	// message to be sent to Bob
		long []  cipher;
		cipher =  Alice.encryptTo(msg, Bob);			// encrypted, with Bob's public key
	
		System.out.println ("Message is: " + msg);
		System.out.println ("Alice sends:");
		show (cipher);
	
		System.out.println ("Bob decodes and reads: " + Bob.decrypt (cipher));	// decrypted,
									// with Bob's private key.
		System.out.println ();
	
		msg = new String ("No thanks, I'm busy");
		cipher = Bob.encryptTo (msg, Alice);
	
		System.out.println ("Message is: " + msg);
		System.out.println ("Bob sends:");
		show (cipher);
	
		System.out.println ("Alice decodes and reads: " + Alice.decrypt (cipher));

	}

	public RSA()
	{
	
	}

	
	public static long inverse(long e, long m)
	{
	  return null;
	}
	
	public static void show(long[] cipher)
	{
		System.out.println(Arrays.toString(cipher));
	}
	
	public static long modPower(long b, long p, long m)
	{
	  return null;
	}
	// Quentin Terry
	public static long randPrime(int m, int n, java.util.Random rand)
	{
		rand = new Random();
		int random = rand.nextInt(n - m) + m;

		while(!isPrime(random))
		{
			random = rand.nextInt(n - m) + m;
		}
		return random;
	}
	// Quentin Terry
	public static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	//Quentin Terry
		private static int gcd(int a, int b) {
	    int t;
	    while(b != 0){
	        t = a;
	        a = b;
	        b = t%b;
	    }
	    return a;
	}
	// Quentin Terry
	public static long relPrime(long n, java.util.Random rand)
	{
		rand = new Random();
		
		int i = rand.nextInt(1000);
		if(gcd((int)n,i) == 1 && i < n)
		{
			return i;
		}

		return relPrime(n, new Random());
		

	}
	//Quentin Terry
	public static long toLong(java.lang.String msg, int p)
	{
		
		char c = msg.charAt(p);
		long l1 = c;

		
	  return l1;
	}
	//Quentin Terry
	public static java.lang.String longTo2Chars(long x)
	{
		char c = (char) x;

	  return Character.toString(c);
	  
	}


// ..........  place class methods here

}
