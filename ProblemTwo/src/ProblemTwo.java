import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProblemTwo {

	public static void main(String[] args) {
		decrypt();
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

	private static List<String> generateKeys() {
		List<String> keyList = new ArrayList<String>();

		for (char a = 'a'; a <= 'z'; a++) {
			for (char b = 'a'; b <= 'z'; b++) {
				for (char c = 'a'; c <= 'z'; c++) {
					keyList.add("" + a + b + c);
				}
			}
		}
		return keyList;
	}

	private static void findKey() {
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

	private static void decrypt() {
		// Get encrypted file & all key combinations
		List<Byte> encryptedText = readFile();
		List<String> keys = generateKeys();
		Map<Integer, String> textScores = new HashMap<Integer, String>();
		
		// Do XOR between the key and the values
		for (String key : keys) {
			StringBuilder plainText = new StringBuilder(); // Create new plainText
			char[] testKey = key.toCharArray(); // Separate key to individual chars
			int i = 0; // index for chars
			
			for (Byte character : encryptedText) { // Create text by XOR between each char and its' corresponding key char
				if (i == 3) i = 0;
				plainText.append(character ^ testKey[i]);
				i++;
			}
			int score = frequencyAnalysis(plainText.toString());
			textScores.put(score, plainText.toString());
		}
	}
}
