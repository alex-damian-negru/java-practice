import java.util.ArrayList;
import java.util.Arrays;

public class ProblemOne {

	/*
	 * Generate all prime numbers up to n using the Sieve of Erastothenes algorithm.
	 */
	private ArrayList<Integer> generatePrimes(int n) {

		ArrayList<Integer> primeList = new ArrayList<Integer>();
		boolean prime[] = new boolean[n + 1];
		Arrays.fill(prime, true);

		for (int p = 2; p * p <= n; p++) {
			if (prime[p] == true) {
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}
		
		for (int i = 2; i < prime.length; i++) {
			if (prime[i] == true) { 
				primeList.add(i);
			System.out.println(i);
			}
		}

		return primeList;
	}

	private void consecutivePrimeSum(int limit) {
	}

	public static void main(String args[]) {
		ProblemOne problemOne = new ProblemOne();

		long start = System.currentTimeMillis();
		ArrayList<Integer> test = problemOne.generatePrimes(100);
		long end = System.currentTimeMillis();
		System.out.println("Executed in " + (end - start) + "ms");
	}

}
