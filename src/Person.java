import java.util.Random;

public class Person
{
  private long e;
  private long m;
  private long d;


  public Person()
  {

    Random rand = new Random();
    e = RSA.randPrime(Integer.MAX_VALUE/4,Integer.MAX_VALUE, rand);
    m = RSA.randPrime();
  }

  public long getM()
  {
    return null;
  }

  public long getE()
  {
    return null;
  }

  public long[] encryptTo(java.lang.String msg, Person she)
  {
    return null;
  }

  public java.lang.String decrypt(long[] cipher)
  {
    return null;
  }


}
