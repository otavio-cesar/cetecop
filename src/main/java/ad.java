import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ad {
public static void main(String[] args) {
	try {
		Scanner c = new Scanner(new File("f://t.txt"));
		while (c.hasNext()) {
			System.out.println(c.nextLine());
			
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
