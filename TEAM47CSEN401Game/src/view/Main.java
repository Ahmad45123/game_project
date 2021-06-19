package view;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import engine.Game;

public class Main {
	static JTextField player_name, city_name;
	static Game game;

	public static void main(String args[]) {

		player_name = new JTextField("player name");
		city_name = new JTextField("city name");
		int w = 1000, h = 1000;

		JFrame myWindow = new JFrame();

		JLabel myLabel = new JLabel("Start Menu");
		myLabel.setBounds(w / 2-w/4/2 + 70, h / 10, w / 4, h / 25);
		myLabel.setFont(myLabel.getFont().deriveFont(23.0f));

		JButton start_button = new JButton("Start Game");
		start_button.setBounds(w / 2 - w / 20, h - h / 4 - h / 36, w / 10, h / 18);

		start_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					game = new Game(player_name.getText(), city_name.getText());
					myLabel.setText("loading");
				} catch (IOException dontCare) {
					alert("io exception but somehow not invalid city ?!?");
				}

			}
		});

		player_name.setBounds(w / 2 - w / 4 / 2, h / 5, w / 4, h / 20);
		city_name.setBounds(w / 2 - w / 4 / 2, h / 5 + 100, w / 4, h / 20);

		myWindow.add(myLabel);
		myWindow.add(start_button);
		myWindow.add(player_name);
		myWindow.add(city_name);

		myWindow.setSize(w, h);
		myWindow.setLayout(null);
		myWindow.setVisible(true);
	}

	static void alert(String al) {
		JFrame myWindow = new JFrame();

		myWindow.setSize(400, 100);
		JLabel myLabel = new JLabel(al);
		myWindow.getContentPane().add(myLabel);
		myWindow.setVisible(true);
	}

}
