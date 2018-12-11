// Project 3
// Driver and class methods for RSA Public Key cryptography
// As Provided by Prof. Bergmann

import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.util.*;	// Random number generator

public class RSA
{
	public static void main (String args[])
	{
		Person Alice = new Person();


		long x = 345; //17
		long y = 76408; //75

		long tester = RSA.inverse(x, y);
		System.out.println(tester);


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

	/**
	 * Finds the multiplicative inverse of a long e modulo m, using the extended euclidian algorithm
	 * @author Matt Moore
	 * @param e - digit (long) being inversed
	 * @param m - modulo - long
	 * @return long - The inverse of e (mod m)
	 */
	public static long inverse(long e, long m)
	{
		//Remainder and quotient variable declarations (longs)
		long r, q;

		//Arrays of longs to hold r, u, and v values from formula
		long[] rLongs = new long[]{e, m}; //e=X, m=Y for x^(-1) (mod y)
		long[] uLongs = new long[]{1, 0}; //U1, U2
		long[] vLongs = new long[]{0, 1}; //V1, U2

		/*Storing the initial mod (long y) for multiplicative inverse operation at the end
		long y = rLongs[1];*/

		do { //Formula R(n) = U(n)*X + V(n)*Y
			r = rLongs[1] % rLongs[0]; //setting new remainder (r) from y/x [essentially r(n-2)/r(n-1)]
			q = rLongs[1] / rLongs[0]; //setting new quotient (q) from r(n-2)/r(n-1)

			//Each step (n) => u(n) = u(n-2) - q(n)*u(n-1) && v(n) = v(n-2) - q(n)*v(n-1)
			long u = uLongs[1] - avoidOverflow(uLongs[0], q, m); //Perform [q * uLongs[0] (mod m)], avoiding overflow
			u = u % m;
			if (u < 0)
				u = u + m;	//That final u value + the mod value, m (like multiplicative inverse, for if negative)
			long v = vLongs[1] - avoidOverflow(vLongs[0], q, m); //Perform [q * vLongs[0] (mod m)], avoiding overflow
			v = v % m;
			if (v < 0)
				v = v + m;	//That final v value + the mod value, m (like multiplicative inverse, for if negative)

			//Update arrays of longs (r, u, and v) each step, n
			rLongs[1] = rLongs[0];
			rLongs[0] = r;
			uLongs[1] = uLongs[0];
			uLongs[0] = u;
			vLongs[1] = vLongs[0];
			vLongs[0] = v;

		} while (r != 1); //"until" remainder = 1
		//Since the final u value * the final e value returns 1, the final u value is the inverse.
		return uLongs[0]; //MULTIPLICATIVE INVERSE => vLongs[0] cancels out at end
	}



	/**
	 * Raises a number (long), b, to the power p, modulo m.
	 * @author Matt Moore
	 * @param b - long (number being raised to power)
	 * @param p - long (exponent)
	 * @param m - long (mod)
	 * @return result (long) - b^p mod m
	 */
	public static long modPower(long b, long p, long m)
	{
		long result = 1, power = p;
		int nPower = 0;
		while (Math.pow(2, nPower) <= p)
			nPower++;							//Provides a max nPower (2^n), given a power, p
		long[] nPowers = nPowers(b, nPower, m); //Saving computed powers of 2 to maxPower in array via call to helper method
		nPower--;
		long toNPower = (long) Math.pow(2, nPower); //Saving 2 to the nPower (2^(nPower)) as long => for use in decrementing iteration


		for (int n = nPower; n>=0; n--) { //Decrementing iteration from max nPower to 0
			if (power >= toNPower) { //Checking current input power, p, to the saved 2^(nPower), if greater subtract n by 1
				power -= toNPower;
				result = avoidOverflow(result, nPowers[n], m); //Multiplying power into result => Use this to avoid overflow in the modPower method.
			}
			toNPower /= 2;
		}
	  return result;
	}

	/**
	 * Computes b^n for all powers of 2 to maxPower
	 * @author Matt Moore
	 * @param b - long (number being raised to power)
	 * @param maxPower - int (the max n in 2^n)
	 * @param m - long (mod)
	 * @return nPowers - array of longs (b^(2^n) for each n)
	 */
	public static long[] nPowers(long b, int maxPower, long m)
	{
		//Saving computed powers of b^(2^n) for each n, and sending back its array
		long[] nPowers = new long[maxPower];
		long base = b;
		nPowers[0] = b; //base case

		for (int i = 1; i <= maxPower; i++) { //Squares each base and stores it in return array
			base = avoidOverflow(base, base, m); //Use this to avoid overflow in the modPower method.
			nPowers[i] = base;
		}
		return nPowers;
	}

	/**
	 * Helper function performing multiplication on two n-bit values and avoid overflow
	 * @author Matt Moore
	 * @param x - long, multiplied (mod m)
	 * @param y - long, multiplied (mod m)
	 * @param m - long (mod)
	 * @return result - long (mod m)
	 */
	public static long avoidOverflow(long x, long y, long m)
	{ //Watch out for overflow. When multiplying two n-bit values, the result requires 2*n bits.
		long result = 0;
		while (y > 0) { //Halfing max value of (longs) second num until 0
			//If the second number is odd, add x (mod m) to result
			if (y % 2 == 1)
				result = (result + x) % m;

			x = (x * 2) % m; //Multiply first number by 2 (mod m)
			y /= 2;
		}
		return result; //new quotient as result
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
		/**
	 * @author Quentin Terry
	 * @param int a
	 * @param int b
	 * @return gcd of two params
	 */
	private static int gcd(int a, int b) {
	    int t;
	    while(b != 0){
	        t = a;
	        a = b;
	        b = t%b;
	    }
	    return a;
	}
	/**
	 * @author Quentin Terry
	 * @param int a
	 * @param int b
	 * @return gcd of two params
	 */
	private static int gcd(int a, int b) {
	    int t;
	    while(b != 0){
	        t = a;
	        a = b;
	        b = t%b;
	    }
	    return a;
	}
	/**
	 * @author Quentin Terry
	 * @param int n
	 * @param Random rand
	 * @return random number relative to n
	 */
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
	/**
	 * @author Quentin Terry
	 * @param msg
	 * @param p
	 * @return return two digit number beginning at position p of msg as a long int
	 */
	public static long toLong(java.lang.String msg, int p)
	{
		
		char c = msg.charAt(p);
		long l1 = c;

		
	  return l1;
	}
	/**
	 * @author Quentin Terry
	 * @param msg
	 * @param p
	 * @return The string made up of two numeric digits representing x
	 */
	public static java.lang.String longTo2Chars(long x)
	{
		char c = (char) x;

	  return Character.toString(c);
	  
	}
	/**
	 * @author Quentin Terry
	 * @param cipher
	 * Display an array of longs in stdout
	 */
	public static void show(long[] cipher)
	{
		System.out.println(Arrays.toString(cipher));
	}


}
