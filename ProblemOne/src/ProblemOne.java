import java.util.ArrayList;
import java.util.Arrays;

public class ProblemOne {

	/*
	 * Generate all prime numbers up to n using the Sieve of Erastothenes algorithm.
	 */
	private boolean[] initPrimes(int n) {
		boolean primes[] = new boolean[n + 1];
		Arrays.fill(primes, true);

		for (int p = 2; p * p <= n; p++) {
			if (primes[p] == true) {
				for (int i = p * 2; i <= n; i += p)
					primes[i] = false;
			}
		}
		return primes;
	}

	private ArrayList<Integer> generatePrimes(int n) {

		ArrayList<Integer> primesList = new ArrayList<Integer>();
		boolean[] primes = initPrimes(n);

		for (int i = 2; i < primes.length; i++) {
			if (primes[i] == true) {
				primesList.add(i);
			}
		}

		return primesList;
	}

	private int consecutivePrimeSum(int limit) {
		boolean[] isPrime = initPrimes(limit);
		ArrayList<Integer> primes = generatePrimes(limit);

		int maxSum = 0;
		int maxRun = -1;
		for (int i = 0; i < primes.size(); i++) {
			int sum = 0;
			for (int j = i; j < primes.size(); j++) {
				sum += primes.get(j);
				if (sum > limit)
					break;
				else if (j - i > maxRun && sum > maxSum && isPrime[sum]) {
					maxSum = sum;
					maxRun = j - i;
				}
			}
		}
		System.out.println(maxSum);
		return maxSum;
	}

	public static void main(String args[]) {
		ProblemOne problemOne = new ProblemOne();

		long start = System.currentTimeMillis();
		problemOne.consecutivePrimeSum(1000);
		long end = System.currentTimeMillis();
		System.out.println("Executed in " + (end - start) + "ms");
	}

}
