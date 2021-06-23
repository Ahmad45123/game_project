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

public class StationedArmyUnitComponent extends JPanel {
	
	public StationedArmyUnitComponent(String unitName, int level){
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Lv " + level),BorderLayout.NORTH);
		this.add(new JLabel(unitName),BorderLayout.CENTER);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	}
	

}
