import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
	
	private static void decrypt() {
		// Get encrypted file & all key combinations
		List<Byte> encryptedText = readFile();
		List<String> keys = generateKeys();
		
		// Put every key underneath the text
		for (String key : keys) {
			for (int i = 0; i < encryptedText.size(); i++) {
				System.out.print(encryptedText.get(i));
			}
			System.out.println();
			
			char[] testKey = key.toCharArray();
			for (int i = 0, j = 0; i < encryptedText.size(); i++) {
				if (j == 3) j = 0;
				System.out.print(testKey[j]);
				j++;
			}
			System.out.println("\n");
		}
	}
}
