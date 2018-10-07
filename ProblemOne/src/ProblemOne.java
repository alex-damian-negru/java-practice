import java.util.ArrayList;
import java.util.Arrays;

public class ProblemOne {

	// Generate all prime numbers up to n using the Sieve of Erastothenes algorithm.
	private ArrayList<Integer> generatePrimes(int limit) {
		ArrayList<Integer> primesList = new ArrayList<Integer>();
		boolean primes[] = new boolean[limit + 1];
		Arrays.fill(primes, true);

		for (int p = 2; p * p <= limit; p++) {
			if (primes[p] == true) {
				for (int i = p * 2; i <= limit; i += p)
					primes[i] = false;
			}
		}

		for (int i = 2; i < primes.length; i++) {
			if (primes[i] == true) {
				primesList.add(i);
			}
		}

		return primesList;
	}

	private int consecutivePrimeSum(int limit) {
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

		System.out.println(result + " " + numberOfPrimes);
		return result;
	}
	// private int consecutivePrimeSum(int limit) {
	// boolean[] isPrime = initPrimes(limit);
	// ArrayList<Integer> primes = generatePrimes(isPrime);
	//
	// int maxSum = 0;
	// int maxRun = -1;
	// for (int i = 0; i < primes.size(); i++) {
	// int sum = 0;
	// for (int j = i; j < primes.size(); j++) {
	// sum += primes.get(j);
	// if (sum > limit)
	// break;
	// else if (j - i > maxRun && sum > maxSum && isPrime[sum]) {
	// maxSum = sum;
	// maxRun = j - i;
	// }
	// }
	// }
	// System.out.println(maxSum);
	// return maxSum;
	// }

	public static void main(String args[]) {
		ProblemOne problemOne = new ProblemOne();

		long start = System.currentTimeMillis();
		problemOne.consecutivePrimeSum(1000000);
		long end = System.currentTimeMillis();
		System.out.println("Executed in " + (end - start) + "ms");
	}

}
