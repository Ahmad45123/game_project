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

public class DefendingArmyComponent extends JPanel {
	public DefendingArmyComponent(String unitName, int level) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(unitName + "  Lv " + level),BorderLayout.NORTH);
		this.add(new JButton("Send"),BorderLayout.CENTER);
		this.add(new JButton("Initiate"),BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
	}

}
