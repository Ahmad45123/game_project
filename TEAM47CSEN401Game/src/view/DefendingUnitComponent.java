package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;

import exceptions.FriendlyFireException;
import units.*;

public class DefendingUnitComponent extends JPanel {

	public DefendingUnitComponent(Unit z, BattleView bv) {
		this.setLayout(new BorderLayout());
		this.add(new JLabel(z.getClass().getSimpleName()), BorderLayout.NORTH);
		this.add(new JLabel(
				"Lv " + z.getLevel() + " Soldier Count: " + z.getCurrentSoldierCount() + "/" + z.getMaxSoldierCount()),
				BorderLayout.CENTER);
		JButton choose = new JButton("Attack");
		this.add(choose, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));

		choose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (bv.getState() == 0) {
					JOptionPane.showMessageDialog(null, "Please choose between Manual Attack and Auto Resolve");

				} else if (bv.getState() == 1) {
					if (bv.getAttackingUnit() == null)
						JOptionPane.showMessageDialog(null, "Please choose an attacking unit");
					else {
						try {
							bv.getBattleLog().append("\n" + bv.getAttackingUnit().getClass().getSimpleName() + " Lv. "
									+ bv.getAttackingUnit().getLevel() + "\n");
							bv.getBattleLog().append(
									"Has attacked: " + z.getClass().getSimpleName() + " Lv. " + z.getLevel() + "\n");
							bv.getBattleLog().append(
									"Thus making it lose " + bv.getAttackingUnit().calculateAttack(z) + " Soldiers\n");
							if (bv.getAttackingUnit().calculateAttack(z) == z.getCurrentSoldierCount()) {
								bv.getBattleLog().append("and finishing the unit off\n");
							}
							bv.getAttackingUnit().attack(z);

							bv.revalidate();
							bv.repaint();
							if (bv.getDefendingArmy().getUnits().size() == 0) {
								int result = JOptionPane.showConfirmDialog(null, "Your army has won the battle",
										"Manual Attack Outcome", JOptionPane.PLAIN_MESSAGE);
								Launcher.getGame().occupy(bv.getAttackingArmy(),
										bv.getDefendingArmy().getCurrentLocation());
								if (result == JOptionPane.OK_OPTION)
									Launcher.initialiseWorldMap();
							}
							bv.setAttackingUnit(null);
							bv.getBattleLog().append("\nThe Enemy's Turn: \n");
							Unit unit1 = bv.getDefendingArmy().getUnits()
									.get((int) (Math.random() * bv.getDefendingArmy().getUnits().size()));
							Unit unit2 = bv.getAttackingArmy().getUnits()
									.get((int) (Math.random() * bv.getAttackingArmy().getUnits().size()));
							bv.getBattleLog().append(
									"\n" + unit1.getClass().getSimpleName() + " Lv. " + unit1.getLevel() + "\n");
							bv.getBattleLog().append("Has attacked: " + unit2.getClass().getSimpleName() + " Lv. "
									+ unit2.getLevel() + "\n");
							bv.getBattleLog()
									.append("Thus making it lose " + unit1.calculateAttack(unit2) + " Soldiers\n");
							if (unit1.calculateAttack(unit2) == unit2.getCurrentSoldierCount()) {
								bv.getBattleLog().append("and finishing the unit off\n");
							}
							unit1.attack(unit2);

							if (bv.getAttackingArmy().getUnits().size() == 0) {
								int result = JOptionPane.showConfirmDialog(null, "Your army has lost the battle",
										"Manual Attack Outcome", JOptionPane.PLAIN_MESSAGE);
								if (result == JOptionPane.OK_OPTION)
									Launcher.initialiseWorldMap();
							}

							bv.getBattleLog().append("\nYour Turn: \n");
							bv.resetUnits();

						} catch (FriendlyFireException ffe) {
						}
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"You have not chosen Manual Attack so you cannot select units manually");
				}
			}

		});

	}

}
