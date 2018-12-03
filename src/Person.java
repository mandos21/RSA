import java.util.Random;

public class Person
{
  private long e;
  private long m;
  private long d;
  private long p;
  private long q;
  private long n;


  public Person()
  {

    p = RSA.randPrime(Integer.MAX_VALUE/4,Integer.MAX_VALUE, new Random());
    q = RSA.randPrime(Integer.MAX_VALUE/4,Integer.MAX_VALUE, new Random());  
    m = p*q;
    n = (p-1)*(q-1);
    e = RSA.relPrime(n, new Random());
    d = RSA.inverse(e,m);
    
  }

  public long getM()
  {
    return m;
  }

  public long getE()
  {
    return e;
  }

  public long[] encryptTo(java.lang.String msg, Person she)
  {
	  long[] returnLong = [msg.length()];
	  
	  for()
	  {
		  
	  }
	  
	  
    return RSA.modPower(msg., she.e, she.m);
  }

  public java.lang.String decrypt(long[] cipher)
  {
    return null;
  }


}
