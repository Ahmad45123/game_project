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


public class MilitaryBuildingComponent extends JPanel {
	public MilitaryBuildingComponent(String buildingName, int level) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Lv " + level),BorderLayout.NORTH);
		this.add(new JLabel(buildingName),BorderLayout.CENTER);
		this.add(new JButton("Recruit"),BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	}
	

}
