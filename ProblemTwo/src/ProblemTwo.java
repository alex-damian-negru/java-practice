import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemTwo {

	public static void main(String[] args) {
		readFile();
	}
	
	private static void readFile() {
		File file = new File("resources/cipher.txt");
		try (Scanner scanner = new Scanner(file).useDelimiter(",");){
			while(scanner.hasNext()) {
				System.out.print(scanner.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
