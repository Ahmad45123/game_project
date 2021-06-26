package view;

import units.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import engine.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import exceptions.*;
import javax.swing.border.CompoundBorder;

public class DefendingArmyComponent extends JPanel {
	public JButton send;
	public JButton initiate;
	public DefendingArmyComponent(String unitName, int level, Unit u, City c, JLayeredPane cv) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(unitName + "  Lv " + level + " Soldier Count: " + u.getCurrentSoldierCount() + "/" + u.getMaxSoldierCount()),BorderLayout.NORTH);
		send = new JButton("Send to army");
		initiate = new JButton("Initiate new army");
		this.add(send,BorderLayout.CENTER);
		this.add(initiate,BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
		

		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Launcher.getPlayer().getControlledArmies().size()==0) JOptionPane.showMessageDialog(null, "No stationed armies to send unit to");
				else {
						JPanel zrc = new JPanel();
						zrc.setLayout(new GridLayout(0,1,10,10));
						JButton close = new JButton("Close");
						zrc.add(close);
						JScrollPane scrollableZrc = new JScrollPane(zrc);
						Launcher.setComponent(scrollableZrc , 0, 162, 550, 230, false);
						scrollableZrc.setBackground(Color.RED);
						scrollableZrc.setOpaque(true);
						scrollableZrc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scrollableZrc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						
						
						//functionality
						close.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								cv.remove(zrc);
								Launcher.initialiseCityView(c);
							}
						});
						
						for(Army ar : Launcher.getPlayer().getControlledArmies()) {
							JButton armyButton = new JButton(ar.getName());
							zrc.add(armyButton);
							
							armyButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									try {
										ar.relocateUnit(u);
									}
									catch(MaxCapacityException urt) {
										JOptionPane.showMessageDialog(null, ar.getName() +  " is already at max capactiy");
									}
									cv.remove(zrc);
									Launcher.initialiseCityView(c);
								}
							});
							
						}
						
						cv.add(scrollableZrc,2,0);
				}
			}
		});
		
		
		initiate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel zrc = new JPanel();
				zrc.setLayout(new GridLayout(0,1,10,10));
				JTextField name = new JTextField("Army Number " + (Launcher.getPlayer().getControlledArmies().size()+1));
				JButton send = new JButton("Initiate");
				zrc.add(name);
				zrc.add(send);
				JScrollPane scrollableZrc = new JScrollPane(zrc);
				Launcher.setComponent(scrollableZrc , 0, 162, 550, 230, false);
				scrollableZrc.setBackground(Color.RED);
				scrollableZrc.setOpaque(true);
				scrollableZrc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollableZrc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				send.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Launcher.getPlayer().initiateArmy(c, u, name.getText());
						Launcher.initialiseCityView(c);
					}
				});
				
				cv.add(scrollableZrc,2,0);
			}
		});
	}
	
	


}
