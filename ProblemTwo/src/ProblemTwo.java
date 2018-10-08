import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProblemTwo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		decrypt();
		long end = System.currentTimeMillis();
		System.out.println("\nFinished in " + (end - start) + " ms");
	}

	private static List<Byte> readFile() {
		List<Byte> bytes = new ArrayList<Byte>();
		File file = new File("resources/cipher.txt");

		try (Scanner scanner = new Scanner(file).useDelimiter(",")) {
			while (scanner.hasNext()) {
				int value = Integer.parseInt(scanner.next());
				bytes.add((byte) value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return bytes;
	}

	private static void decrypt() {
		List<Byte> encryptedText = readFile();
		Map<Integer, char[]> scoredTexts = new HashMap<Integer, char[]>();
		StringBuilder plainText = new StringBuilder();

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
					scoredTexts.put(score, testKey);
				}
			}
		}

		String rightKey = findKey(scoredTexts);
		printCorrectText(rightKey);
	}
	
	private static int frequencyAnalysis(String text) {
		final String MOSTCOMMON = "etaoinshrdlu";
		int score = 0;

		for (char letter : text.toLowerCase().toCharArray()) {
			for (char common : MOSTCOMMON.toCharArray()) {
				if (letter == common)
					score++;
			}
		}

		return score;
	}

	private static String findKey(Map<Integer, char[]> scores) {
		Map.Entry<Integer, char[]> firstEntry = scores.entrySet().iterator().next();
		int largestKey = firstEntry.getKey();

		for (Map.Entry<Integer, char[]> map : scores.entrySet()) {
			int key = map.getKey();
			if (key > largestKey)
				largestKey = key;
		}

		return String.valueOf(scores.get(largestKey));
	}
	
	private static void printCorrectText(String key) {
		List<Byte> encryptedText = readFile();
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
