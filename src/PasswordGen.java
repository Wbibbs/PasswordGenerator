import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class PasswordGen {

	public static void main(String[] args) throws IOException {

		short length = 0; //Length of password
		short numOfPasswords = 0;
		boolean goodInput = false; //Set to true when inputs are both greater than 0
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		DateFormat date = new SimpleDateFormat("MM-dd HHmmss");
		Date currentDate = new Date();
		File passwordFile = new File("Passwords " + date.format(currentDate) +".txt");
		passwordFile.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(passwordFile));

		do {
			try {

				System.out.println("How many passwords do you want to generate? Must be a number greater than 0 and less than 32,768");
				String in = sc.nextLine();
				System.out.println("How long do you want each password? Must be a number greater than 0 and less than 32,768");
				String in2 = sc.nextLine();

				numOfPasswords = Short.parseShort(in);
				length = Short.parseShort(in2);

				if (length < 1 || numOfPasswords < 1)
					throw new IllegalArgumentException("Less than 1");

			} catch (Exception e) {

				if (e instanceof NumberFormatException)
					System.out.println("You must put in a numeric value only\n");
				else if (e instanceof IllegalArgumentException)
						System.out.println("Your input must be greater than 0 and less than 32,768\n");
			}

			if (length >= 1 && numOfPasswords >= 1) {
				goodInput = true;
				sc.close();
			}
			
		} while(!goodInput);

		System.out.println("Printing to Passwords " + date.format(currentDate));

		for (int currentPassword = 0; currentPassword < numOfPasswords; currentPassword++) {
			for (int i = 0; i < length; i++)
				bw.write(Character.toString((char) (rand.nextInt(93) + 33)));

			bw.newLine();
		}

		bw.close();

	}
}