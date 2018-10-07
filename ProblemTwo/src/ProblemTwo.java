import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemTwo {

	public static void main(String[] args) {
		readFile();
	}
	
	private static void readFile() {
		List<Byte> bytes = new ArrayList<Byte>();
		File file = new File("resources/cipher.txt");
		
		try (Scanner scanner = new Scanner(file).useDelimiter(",");){
			while(scanner.hasNext()) {
				int value = Integer.parseInt(scanner.next());
				bytes.add((byte)value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
