import java.util.ArrayList;
import java.util.Arrays;

/**
 * Finds the largest prime number under given limit written as a sum of the most consecutive primes.
 * 
 * @author Alex Damian Negru
 */
public class ProblemOne {

	private static ArrayList<Integer> generatePrimes(int limit) {
		ArrayList<Integer> primesList = new ArrayList<Integer>();
		boolean primes[] = new boolean[limit + 1];
		Arrays.fill(primes, true);

		for (int p = 2; p * p <= limit; p++) {
			if (primes[p] == true) 
				for (int i = p * 2; i <= limit; i += p)
					primes[i] = false;
		}

		for (int i = 2; i < primes.length; i++) {
			if (primes[i] == true) 
				primesList.add(i);
		}

		return primesList;
	}

	private static void consecutivePrimeSum(int limit) {
		int result = 0;
		int numberOfPrimes = 0;
		Integer[] primes = generatePrimes(limit).toArray(new Integer[0]);
		int[] primeSum = new int[primes.length + 1];

		primeSum[0] = 0;
		for (int i = 0; i < primes.length; i++)
			primeSum[i + 1] = primeSum[i] + primes[i];

		for (int i = numberOfPrimes; i < primeSum.length; i++) {
			for (int j = i - (numberOfPrimes + 1); j >= 0; j--) {
				if (primeSum[i] - primeSum[j] > limit)
					break;
				
				if (Arrays.binarySearch(primes, primeSum[i] - primeSum[j]) >= 0) {
					numberOfPrimes = i - j;
					result = primeSum[i] - primeSum[j];
				}
			}
		}

		System.out.println("Largest prime below " + limit + " written as a sum of consecutive primes is " + result);
		System.out.println("It consists of " + numberOfPrimes + " prime numbers.");
	}

	public static void main(String args[]) {
		consecutivePrimeSum(1000000);
	}

}