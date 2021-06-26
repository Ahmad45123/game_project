package view;

import javax.swing.*;
import units.*;
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

	public StationedArmyUnitComponent(String unitName, int level, Unit zu, City c) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(
				"Lv " + level + " Soldier Count: " + zu.getCurrentSoldierCount() + "/" + zu.getMaxSoldierCount()),
				BorderLayout.NORTH);
		this.add(new JLabel(unitName), BorderLayout.CENTER);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	}

}
