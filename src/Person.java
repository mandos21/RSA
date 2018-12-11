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
	//initializing all necessary variables
    p = RSA.randPrime(Integer.MAX_VALUE/16,Integer.MAX_VALUE/8, new Random()); 
    
    do{ q = RSA.randPrime(Integer.MAX_VALUE/16,Integer.MAX_VALUE/8, new Random());}
    while(q == p); //ensures p!=q 
    
    m = p*q; //does not cause overflow conflict even when both p and q are MAX_VALUE
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
	    msg = msg.replaceAll("\\s","_");
	    
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
	  String returnString = "";
	  
	  for(int i = 0; i < cipher.length; i++)
	  {
		  String cString = String.valueOf(RSA.modPower(Long.valueOf(cipher[i]),d,m));
		  
		  if(cString.length() != 6)
		  {
			  cString = "0" + cString;
		  }
		  
		  String.valueOf((Character.toString((char)(Integer.valueOf(cString.substring(0,3))).intValue()) + 
				  		  Character.toString((char)(Integer.valueOf(cString.substring(3,6))).intValue())));
		  
		  returnString+=cString;
	  }
	  
	  return returnString;
	  
  }


}
