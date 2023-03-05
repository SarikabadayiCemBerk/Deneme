import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main1234 {
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a command:");
		System.out.println("(Please write 'help' to read our business problem definition)");
		String name = scanner.nextLine();
			
    			JFrame frame = new JFrame("Business Problem Definition");
    			Font font = new Font(null,Font.PLAIN,8);
    			
    			JLabel label1 = new JLabel("In this project, we are faced with the task scheduling problem between forklifts, jets and RT machines. Each type of equipment is capable of performing certain types of tasks and they all need to be recharged throughout the day.");
    			JLabel label2 = new JLabel("However, charging stations are limited, so we have to think carefully about when each piece of equipment needs to be charged to ensure they stay powered throughout the day. Our goal is to maximize the number of completed tasks.");
    			
    			label1.setFont(font);
    			label2.setFont(font);	
    			label1.setBounds(0, -430, 1500,900);
    			label2.setBounds(0, -415, 1500,900);
    			label1.setFont(new Font(null,Font.PLAIN,12));
    			label2.setFont(new Font(null,Font.PLAIN,12));
    			
    			frame.add(label1);
    			frame.add(label2);
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			frame.setSize(1400,200);
    			frame.setLayout(null);
    			frame.setVisible(true); 
}
}

