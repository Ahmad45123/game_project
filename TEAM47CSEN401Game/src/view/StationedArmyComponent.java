package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import engine.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import engine.*;
import javax.swing.border.CompoundBorder;

public class StationedArmyComponent extends JPanel {
	
	public StationedArmyComponent(int armyNo) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Army Number " + armyNo));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
	}
	
	
}
