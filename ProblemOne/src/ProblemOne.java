import java.util.Arrays;

public class ProblemOne {

	void consecutivePrimeSum() {
	}

	boolean[] generatePrimes(int n) {
		boolean prime[] = new boolean[n + 1];
		Arrays.fill(prime, true);

		for (int p = 2; p * p <= n; p++) {
			if (prime[p] == true) {
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}
		return prime;
	}

	public static void main(String args[]) {
		ProblemOne test = new ProblemOne();
		
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		System.out.println("Executed in " + (end - start) + "ms");
	}

}
