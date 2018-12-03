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
  
  private String zeroPad(String msg)
  {
  	if(msg.length() == 2)
  	{
  		msg = "0" + msg;
  	}
  	if(msg.length() == 1)
  	{
  		msg = "00" + msg;
  	}
  	
  	return msg;
  	
  }
  

  public long[] encryptTo(java.lang.String msg, Person she)
  {
	  	long[] returnLong = new long[msg.length()/2];
	  
      
	    if(msg.length() % 2 != 0)
	    {
	    	msg = msg + "_";
	    }
	    
	    
	    for(int i = 0, a = 0; i <= msg.length()-1; i=i +2, a++)
	    {
	    	String s1 = zeroPad(Integer.toString((int)msg.charAt(i)));
	    	String s2 = zeroPad(Integer.toString((int)msg.charAt(i+1)));
	    	
	    	returnLong[a] = RSA.modPower(Long.valueOf(s1+s2),she.e,she.m);
	    }
	  
	  
	  
    return returnLong;
  }

  public java.lang.String decrypt(long[] cipher)
  {
    return null;
  }


}
