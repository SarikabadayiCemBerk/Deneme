import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main1234 {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a command:");
		System.out.println("(Please write help to read our business problem definition)");
		String name = scanner.nextLine();
		
		JFrame frame = new JFrame("Business Problem Definition");
		JLabel label = new JLabel("Our problem is the charging scheduling problem which is caused by the fact that a company working with a limited number of equipment completes its daily tasks with electric vehicles.");
				
		label.setBounds(0, -350, 900,900);
		label.setFont(new Font(null,Font.PLAIN,8));
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,200);
		frame.setLayout(null);
		frame.setVisible(true); 		
}
}

