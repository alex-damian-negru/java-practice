import java.util.Arrays;

public class ProblemOne {

	void consecutivePrimeSum() {
		// final int limit = 1000000;
		// int result = 0;
	}

	void sieveOfEratosthenes(int n) {
		boolean prime[] = new boolean[n + 1];
		Arrays.fill(prime, true);

		for (int p = 2; p * p <= n; p++) {
			if (prime[p] == true) {
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}

		for (int i = 2; i <= n; i++) {
			if (prime[i] == true)
				System.out.print(i + " ");
		}
	}

	public static void main(String args[]) {
		ProblemOne test = new ProblemOne();
		
		long start = System.currentTimeMillis();
		test.sieveOfEratosthenes(1000000);
		long end = System.currentTimeMillis();
		System.out.println("Executed in " + (end - start) + "ms");
	}

}
