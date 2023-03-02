import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
File dosya = new File("help.txt");

Scanner s = new Scanner(dosya);
while(s.hasNextLine()) {
	System.out.println(s.nextLine());
	
}
s.close();
}

	}
