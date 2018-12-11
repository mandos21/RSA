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


  public Person()
  {
	//initializing all necessary variables
    p = RSA.randPrime(Integer.MAX_VALUE/16,Integer.MAX_VALUE/8, new Random()); 
    
    do{ q = RSA.randPrime(Integer.MAX_VALUE/16,Integer.MAX_VALUE/8, new Random());}
    while(q == p); //ensures p!=q 
    
    m = p*q; //does not cause overflow conflict even when both p and q are MAX_VALUE
    n = (p-1)*(q-1);
    e = RSA.relPrime(n, new Random());
    d = RSA.inverse(e,n);
    
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
	  
	  	msg = msg.replaceAll("\\s","_");
	    if(msg.length() % 2 != 0)
	    {
	    	msg = msg + "_";
	    }
	  	long[] returnLong = new long[msg.length()/2];
	    
	    for(int i = 0, a = 0; i <= msg.length()-1; i=i +2, a++)
	    {
	    	String s1 = zeroPad(Integer.toString((int)msg.charAt(i)));
	    	String s2 = zeroPad(Integer.toString((int)msg.charAt(i+1)));
	    	
	    	returnLong[a] = RSA.modPower(Long.valueOf(s1+s2),she.e,she.m);
	    	
	    	//debug purposes
	    	if( a == 0)
	    	{
	    		z = Long.valueOf(s1+s2);
	    	}
	    }
	  
	  
    return returnLong;
  }

  public java.lang.String decrypt(long[] cipher)
  {
	  String returnString = "";
	  String addString = "";
	  
	  for(int i = 0; i < cipher.length; i++)
	  {
		  String cString = String.valueOf(RSA.modPower(Long.valueOf(cipher[i]),d,m));
		  
		  if(cString.length() != 6)
		  {
			  cString = "0" + cString;
		  }
		  
		  addString = (Character.toString((char)(Integer.valueOf(cString.substring(0,3))).intValue()) + 
				  		  Character.toString((char)(Integer.valueOf(cString.substring(3,6))).intValue()));
		  
		  
		  
		  returnString+=addString;
	  }
	  
	  returnString = returnString.replaceAll("_"," ");
	  return returnString;
	  
  }


}
