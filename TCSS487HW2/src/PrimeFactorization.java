/*
 * Colin Casey
 * TCSS 487A
 */

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

/**
 * Used to find prime factorization of
 * random numbers.
 * @author caseycao
 */
public class PrimeFactorization {
	
	/** The first power getting tested.*/
	private static final int MY_POWER = 30;
	
	/** The second power getting tested.*/
	private static final int MY_POWER_2 = 40;
	
	/** Used to keep track of number in helper methods.*/
	private static BigInteger myNumber;
	
	/**
	 * Used to kick off program and call all
	 * helper methods.
	 * @param theArgs String
	 */
	public static void main(String theArgs[]) {
		BigInteger a = Kalai_range(MY_POWER);
		BigInteger b = Kalai_range(MY_POWER_2);
		Formater(a, MY_POWER);
		ListOfFactors(a);
		Formater(b, MY_POWER_2);
		ListOfFactors(b);
	}
	

	/**
	 * Take in a number to be used as a
	 * power
	 * @param thePower power used to make random number
	 * @return random number made
	 */
	public static BigInteger Kalai_range(int thePower) {
		BigInteger f = new BigInteger("1");
		Random rand = new Random();
		if (thePower <= 31) {
			// if power is under limit that can be in an int.
			int a = (int) Math.pow(2, thePower+1);
			int n = rand.nextInt(a);
			n = n - 1;
			f = new BigInteger(String.valueOf(n));
		} else {
			// if power is over limit that can be in an int.
			BigInteger temp = new BigInteger("1");
			int power = thePower;
			int currPower = 0;
			while(power > 0) {
				if(power >= 30) {
					power = power -30;
					currPower = 30;
				} else {
					currPower = power + 1;
					power = 0;
				}
				int a = (int) Math.pow(2, currPower);
				int n = rand.nextInt(a);
				BigInteger w = new BigInteger(String.valueOf(n));
				temp = temp.multiply(w);
			}
			BigInteger one = new BigInteger("1");
			temp = temp.subtract(one);
			f = temp;
		}
		return f;
	}
	
	/**
	 * Used to find the prime factorization of a big integer number.
	 * @param theNumber The number sent in in order to 
	 * find the prime factorization.
	 */
	public static void ListOfFactors(final BigInteger theNumber) {
		// The value of the big integer taken in.
		myNumber = theNumber;
		// Constant is added to three in order to find all
		// odd primes.
	    BigInteger two = BigInteger.valueOf(2);
	    // Constant to check for odd primes.
	    BigInteger three = BigInteger.valueOf(3);
	    // The list of the prime factorization for this current
	    // big integer.
	    LinkedList<BigInteger> primeFactors = new LinkedList<BigInteger>();
	    //calls helper method to look for even primes.
	    primeFactors = ListOfFactorsHelpTwoCheck(theNumber);
	    // Checks for every odd prime that can be divided into 
	    // this number. Starting with 3 and working up.
	    if (myNumber.compareTo(BigInteger.ONE) > 0) {
	        three = BigInteger.valueOf(3);
	        while (three.multiply(three).compareTo(myNumber) <= 0) {
	        	BigInteger check = myNumber.mod(three);
	            if (check.equals(BigInteger.ZERO)){
	            	//add odd primes to list
	                primeFactors.add(three);
	                //reduce myNumber based on size of prime
	                myNumber = myNumber.divide(three);
	            } else {
	            	//makes more odd numbers.
	                three = three.add(two);
	            }
	        }
	        //if a prime number is left add it to list
	        primeFactors.add(myNumber);
	    }
	    //print off list
	    System.out.println(primeFactors.toString());
	    System.out.println();
	}
	
	/**
	 * Used to find all of the even primes
	 * in TheNumber.
	 * @param theNumber
	 * @return list made of number two
	 */
	public static LinkedList<BigInteger> ListOfFactorsHelpTwoCheck(final BigInteger theNumber) {
		// The value of the big integer taken in.
		myNumber = theNumber;
		// Constant to check for even primes(only two).
	    BigInteger two = BigInteger.valueOf(2);
	    // The list of the prime factorization for this current
	    // big integer.
	    LinkedList<BigInteger> primeFactors = new LinkedList<BigInteger>();
	    // Check the big Integer for the only even prime two.
	    // Every time it shows up add it to the list and reduce the
	    // number by half.
	    while (myNumber.mod(two).equals(BigInteger.ZERO)) {
	        primeFactors.add(two);
	        myNumber = myNumber.divide(two);
	    }
		return primeFactors;	
	}	
	
	/**
	 * Used to format the data to look nice.
	 * @param theNumber random number
	 * @param thePower power used to make random number
	 */
	private static void Formater(BigInteger theNumber, int thePower) {
		System.out.println("The power used = " + thePower);
		System.out.println("Random number = "+ theNumber.toString());
		System.out.println("Prime Factorization = ");
	}
}
