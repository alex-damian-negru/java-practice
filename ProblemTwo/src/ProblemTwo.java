import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemTwo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		decrypt();
		long end = System.currentTimeMillis();
		System.out.println("\nFinished in " + (end - start) + " ms");
	}

	private static byte[] readFile() {
		File file = new File("resources/cipher.txt");
		byte[] bytes = new byte[(int) file.length()];
		int i = 0;

		try (Scanner scanner = new Scanner(file).useDelimiter(",")) {
			while (scanner.hasNext()) {
				bytes[i++] = (byte) Integer.parseInt(scanner.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return Arrays.copyOf(bytes, i);
	}

	private static void decrypt() {
		byte[] encryptedText = readFile();
		Map<Integer, char[]> scoredTexts = new HashMap<Integer, char[]>();
		StringBuilder plainText = new StringBuilder();
		int maxScore = 0;
		char[] bestKey = null;
		
		for (char a = 'a'; a <= 'z'; a++) {
			for (char b = 'a'; b <= 'z'; b++) {
				for (char c = 'a'; c <= 'z'; c++) {
					plainText.setLength(0);
					char[] testKey = new char[] { a, b, c };
					int i = 0;

					for (Byte character : encryptedText) {
						char currChar = (char) (character ^ testKey[i % 3]);
						plainText.append(currChar);
						i++;
					}
					int score = frequencyAnalysis(plainText.toString());
					if (score > maxScore) {
						maxScore = score;
						bestKey = Arrays.copyOf(testKey, 3);
					}
					scoredTexts.put(score, testKey);
				}
			}
		}
		printCorrectText(String.valueOf(bestKey));
	}
	
	private static int frequencyAnalysis(String text) {
		final String MOSTCOMMON = "etaoinshrdlu";
		int score = 0;

		for (char letter : text.toLowerCase().toCharArray()) {
			if (MOSTCOMMON.indexOf(letter) != -1)
				score++;
		}

		return score;
	}
	
	private static void printCorrectText(String key) {
		byte[] encryptedText = readFile();
		char[] keyBits = key.toCharArray();
		int asciiSum = 0;
		int i = 0;
		
		for (Byte character : encryptedText) {
			if (i == 3) i = 0;
			char currChar = (char) (character ^ keyBits[i]);
			asciiSum += currChar;
			System.out.print(currChar);
			i++;
		}
		System.out.println("\nASCII Sum: " + asciiSum);
	}
}
