package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;



public class WorldMap extends JPanel {
	private int xRes = 800;
	private int yRes = 600;
	private int midRes = xRes/2;
	
	public WorldMap() {
		this.setLayout(null);
		Insets insets = this.getInsets();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		
		JLabel worldMap = new JLabel();
		worldMap.setText("World Map");
		worldMap.setFont(new Font("Ariel", Font.BOLD, 28));
		int labelWidth = 150;
		int labelHeight = 50;
		int labelXOffset = midRes - ((int)(worldMap.getFont().getStringBounds(worldMap.getText(), frc).getWidth()))/2;
		int labelYOffset = 30;
		int labelWidthOffset = 0;
		int labelHeightOffset = 0;
		Dimension labelSize = new Dimension(labelWidth,labelHeight);
		worldMap.setBounds(labelXOffset + insets.left, labelYOffset + insets.top, labelWidthOffset + labelSize.width, labelHeightOffset + labelSize.height);
		this.add(worldMap);
		
		
		
		
		
	}
	
}
