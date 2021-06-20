package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;



public class StartScreen extends JPanel  {
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes/2;
	
	public StartScreen() {
		//JScrollPane scrollableMainPanel = new JScrollPane(mainPanel);
		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		
//		Get string in a certain font's pixel width example
		
//		String s = "Hello World";
//		Font z = new Font("Ariel", Font.BOLD, 28);
//		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
//		int textwidth = (int)(z.getStringBounds(s, frc).getWidth());
//		int textheight = (int)(font.getStringBounds(text, frc).getHeight());

		JButton startButton = new JButton("Start");
		int buttonWidth = 200;
		int buttonHeight = 50;
		int buttonXOffset = midRes - (buttonWidth/2);
		int buttonYOffset = 480;
		int buttonWidthOffset = 0;
		int buttonHeightOffset = 0;
		Dimension buttonSize = new Dimension(buttonWidth,buttonHeight);
		startButton.setBounds(buttonXOffset + insets.left, buttonYOffset + insets.top, buttonWidthOffset + buttonSize.width, buttonHeightOffset + buttonSize.height);
		this.add(startButton);
		
		
		//Smooth transition between start screen and world map
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.initialiseWorldMap();
				
			}
		});
		
		JLabel startMenu = new JLabel();
		startMenu.setText("Start Menu");
		startMenu.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelWidth = 150;
		int labelHeight = 50;
		int labelXOffset = midRes - ((int)(startMenu.getFont().getStringBounds(startMenu.getText(), frc).getWidth()))/2;
		int labelYOffset = 30;
		int labelWidthOffset = 0;
		int labelHeightOffset = 0;
		Dimension labelSize = new Dimension(labelWidth,labelHeight);
		startMenu.setBounds(labelXOffset + insets.left, labelYOffset + insets.top, labelWidthOffset + labelSize.width, labelHeightOffset + labelSize.height);
		this.add(startMenu);
		
		JLabel nameEntry = new JLabel();
		nameEntry.setText("Enter your name:");
		nameEntry.setFont(new Font("Ariel", Font.BOLD, 20));
		int nameEntryWidth = 200;
		int nameEntryHeight = 230;
		int nameEntryXOffset = midRes- ((int)(nameEntry.getFont().getStringBounds(nameEntry.getText(), frc).getWidth()))/2;
		int nameEntryYOffset = 30;
		int nameEntryWidthOffset = 0;
		int nameEntryHeightOffset = 0;
		Dimension nameEntrySize = new Dimension(nameEntryWidth,nameEntryHeight);
		nameEntry.setBounds(nameEntryXOffset + insets.left, nameEntryYOffset + insets.top, nameEntryWidthOffset + nameEntrySize.width, nameEntryHeightOffset + nameEntrySize.height);
		this.add(nameEntry);
		
		JLabel cityEntry = new JLabel();
		cityEntry.setText("Enter your city:");
		cityEntry.setFont(new Font("Ariel", Font.BOLD, 20));
		int cityEntryWidth = 200;
		int cityEntryHeight = 600;
		int cityEntryXOffset = midRes- ((int)(cityEntry.getFont().getStringBounds(cityEntry.getText(), frc).getWidth()))/2;;
		int cityEntryYOffset = 30;
		int cityEntryWidthOffset = 0;
		int cityEntryHeightOffset = 0;
		Dimension cityEntrySize = new Dimension(cityEntryWidth,cityEntryHeight);
		cityEntry.setBounds(cityEntryXOffset + insets.left, cityEntryYOffset + insets.top, cityEntryWidthOffset + cityEntrySize.width, cityEntryHeightOffset + cityEntrySize.height);
		this.add(cityEntry);
		
		JTextField nameInput = new JTextField();
		int nameInputWidth = 200;
		int nameInputHeight = 30;
		int nameInputXOffset = midRes- nameInputWidth/2;
		int nameInputYOffset = 180;
		int nameInputWidthOffset = 0;
		int nameInputHeightOffset = 0;
		Dimension nameInputSize = new Dimension(nameInputWidth,nameInputHeight);
		nameInput.setBounds(nameInputXOffset + insets.left, nameInputYOffset + insets.top, nameInputWidthOffset + nameInputSize.width, nameInputHeightOffset + nameInputSize.height);
		this.add(nameInput);
		
		JTextField cityInput = new JTextField();
		int cityInputWidth = 200;
		int cityInputHeight = 30;
		int cityInputXOffset = midRes- cityInputWidth/2;
		int cityInputYOffset = 360;
		int cityInputWidthOffset = 0;
		int cityInputHeightOffset = 0;
		Dimension cityInputSize = new Dimension(cityInputWidth,cityInputHeight);
		cityInput.setBounds(cityInputXOffset + insets.left, cityInputYOffset + insets.top, cityInputWidthOffset + cityInputSize.width, cityInputHeightOffset + cityInputSize.height);
		this.add(cityInput);
		
		JLabel karimName = new JLabel();
		karimName.setText("Karim ElMosalamy 49-4884");
		karimName.setFont(new Font("Ariel", Font.BOLD, 17));
		int karimNameWidth = 300;
		int karimNameHeight = 880;
		int karimNameXOffset = 0;
		int karimNameYOffset = 30;
		int karimNameWidthOffset = 0;
		int karimNameHeightOffset = 0;
		Dimension karimNameSize = new Dimension(karimNameWidth,karimNameHeight);
		karimName.setBounds(karimNameXOffset + insets.left, karimNameYOffset + insets.top, karimNameWidthOffset + karimNameSize.width, karimNameHeightOffset + karimNameSize.height);
		this.add(karimName);
		
		JLabel ahmedName = new JLabel();
		ahmedName.setText("Ahmed Mamdooh 49-4934");
		ahmedName.setFont(new Font("Ariel", Font.BOLD, 17));
		int ahmedNameWidth = 300;
		int ahmedNameHeight = 930;
		int ahmedNameXOffset = 0;
		int ahmedNameYOffset = 30;
		int ahmedNameWidthOffset = 0;
		int ahmedNameHeightOffset = 0;
		Dimension ahmedNameSize = new Dimension(ahmedNameWidth,ahmedNameHeight);
		ahmedName.setBounds(ahmedNameXOffset + insets.left, ahmedNameYOffset + insets.top, ahmedNameWidthOffset + ahmedNameSize.width, ahmedNameHeightOffset + ahmedNameSize.height);
		this.add(ahmedName);
		
		JLabel noureldinName = new JLabel();
		noureldinName.setText("Noureldin Shaker 49-7911");
		noureldinName.setFont(new Font("Ariel", Font.BOLD, 17));
		int noureldinNameWidth = 300;
		int noureldinNameHeight = 980;
		int noureldinNameXOffset = 0;
		int noureldinNameYOffset = 30;
		int noureldinNameWidthOffset = 0;
		int noureldinNameHeightOffset = 0;
		Dimension noureldinNameSize = new Dimension(noureldinNameWidth,noureldinNameHeight);
		noureldinName.setBounds(noureldinNameXOffset + insets.left, noureldinNameYOffset + insets.top, noureldinNameWidthOffset + noureldinNameSize.width, noureldinNameHeightOffset + noureldinNameSize.height);
		this.add(noureldinName);
		
		//testing a panel inside a panel, works as intended, will be useful in city view
		
//		JPanel testPanel = new JPanel();
//		testPanel.setBackground(Color.RED);
//		int testPanelWidth = 50;
//		int testPanelHeight = 50;
//		int testPanelXOffset = 0;
//		int testPanelYOffset = 30;
//		int testPanelWidthOffset = 0;
//		int testPanelHeightOffset = 0;
//		Dimension testPanelSize = new Dimension(testPanelWidth,testPanelHeight);
//		testPanel.setBounds(testPanelXOffset + insets.left, testPanelYOffset + insets.top, testPanelWidthOffset + testPanelSize.width, testPanelHeightOffset + testPanelSize.height);
//		mainPanel.add(testPanel);
		
	}
	
	
	
	
	
}
