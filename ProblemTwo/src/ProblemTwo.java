import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
		StringBuilder plainText = new StringBuilder();
		int maxScore = 0;
		char[] bestKey = null;
		
		for (char a = 'a'; a <= 'z'; a++) {
			for (char b = 'a'; b <= 'z'; b++) {
				for (char c = 'a'; c <= 'z'; c++) {
					plainText.setLength(0);
					char[] testKey = new char[] { a, b, c };
					int i = 0;

					boolean isValid = true;
					for (Byte character : encryptedText) {
						char currChar = (char) (character ^ testKey[i % 3]);
						if (currChar < 32 || currChar > 127) {
							isValid = false;
							break;
						}
						
						plainText.append(currChar);
						i++;
					}
					if (!isValid)
						continue;
					int score = frequencyAnalysis(plainText.toString());
					if (score > maxScore) {
						maxScore = score;
						bestKey = Arrays.copyOf(testKey, 3);
					}
				}
			}
		}
		printCorrectText(encryptedText, String.valueOf(bestKey));
	}
	
	private static int frequencyAnalysis(String text) {
		final String MOSTCOMMON = "etaoinshrdlu";
		int score = 0;

		for (int i=0; i<text.length(); i++) {
			char letter = Character.toLowerCase(text.charAt(i));
			if (MOSTCOMMON.indexOf(letter) != -1)
				score++;
		}

		return score;
	}
	
	private static void printCorrectText(byte[] encryptedText, String key) {
		StringBuilder stringBuilder = new StringBuilder(encryptedText.length);
		char[] keyBits = key.toCharArray();
		int asciiSum = 0;
		int i = 0;
		
		for (Byte character : encryptedText) {
			char currChar = (char) (character ^ keyBits[i % 3]);
			asciiSum += currChar;
			stringBuilder.append(currChar);
			i++;
		}
		System.out.print(stringBuilder.toString());
		System.out.println("\nASCII Sum: " + asciiSum);
	}
}
