import java.util.Random;

public class Person
{
  private long e;
  private long m;
  private long d;
  private long p;
  private long q;
  private long n;
  private long z;


  /**
   * Generate a public key for this person, consisting of exponent,e, and modulus, m.
   * Generate a private key, consisting of an exponent, d.
   * Provide access to the public key only.
   * 
   * @author Matthew DeGenaro
   */
  public Person()
  {
	//initializing all necessary variables
    p = RSA.randPrime(Integer.MAX_VALUE/16,Integer.MAX_VALUE/8, new Random()); 
    
    do{ q = RSA.randPrime(Integer.MAX_VALUE/16,Integer.MAX_VALUE/8, new Random());}
    while(q == p); //ensures p!=q, likely will never loop
    
    m = p*q; //does not cause overflow conflict even when both p and q are MAX_VALUE
    n = (p-1)*(q-1);
    e = RSA.relPrime(n, new Random());
    d = RSA.inverse(e,n);
    
  }

  /**
   * Access the public modulus
   * @return the public modulus for this Person
   * @author Matthew DeGenaro
   */
  public long getM()
  {
    return m;
  }

  /**
   * Access the public encryption exponent
   * @return the public encryption exponent for this Person
   * @author Matthew DeGenaro
   */
  public long getE()
  {
    return e;
  }
    
/**
 * Encrypt a plaintext message to she.
 * @param msg	String, message to encrypt
 * @param she   Person, to whom message will be sent
 * @author Matthew DeGenaro
 * @return  An array of longs, which is the cipher text
 */
  public long[] encryptTo(java.lang.String msg, Person she)
  {
	  

	    if(msg.length() % 2 != 0)
	    {
	    	msg = msg + "_";								//ensure loop doesn't break on odd numbers
	    }
	  	long[] returnLong = new long[msg.length()/2];
	    
	    for(int i = 0, a = 0; i <= msg.length()-1; i=i +2, a++)
	    {
	    	returnLong[a] = RSA.modPower(RSA.toLong(msg, i),she.e,she.m);		//encrypt and add to array of blocks to be returned
	    }
	  
	  
    return returnLong;
  }

  /**
   * Decrypt the cipher text
   * @param cipher	long[], cipher to decrypt
   * @author Matthew DeGenaro
   * @return a string of plaintext
   */
  public java.lang.String decrypt(long[] cipher)
  {
	  String returnString = "";
	  for(int i = 0; i < cipher.length; i++)
	  {
		  long cLong = RSA.modPower(Long.valueOf(cipher[i]),d,m); 	//decrypt into long
		  returnString+=RSA.longTo2Chars(cLong); 					//convert to string and append to return
	  }
	  
	  returnString = returnString.replaceAll("_"," ");
	  return returnString;
	  
  }


}
