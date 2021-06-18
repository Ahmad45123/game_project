package view;
import javax.swing.*;

public class Main {
	public static void main(String args[]) {
		JFrame myWindow = new JFrame();
		myWindow.setSize(400,100);
		JLabel myLabel = new JLabel("My first window");
		myWindow.getContentPane().add(myLabel);
		myWindow.setVisible(true);
	}

}
