package view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;
import engine.*;
import exceptions.FriendlyFireException;
import units.*;

public class BattleView extends JPanel {

	private int xRes = Launcher.getxRes();
	private int yRes = Launcher.getyRes();
	private int midRes = Launcher.getMidRes();
	private int state = 0;
	private Army atArmy;
	private Army dfArmy;
	private Unit attackingUnit;
	private Unit defendingUnit;
	private JPanel attackingArmyPanel;
	private JPanel defendingArmyPanel;
	private JTextArea battleLog;
	private boolean lock = false;

	//TODO: bring back proper logic after testing
	
	public BattleView(Army attackingArmy, Army defendingArmy) {
		this.atArmy = attackingArmy;
		this.dfArmy = defendingArmy;
		Game game = Launcher.getGame();
		Player player = Launcher.getPlayer();
		this.setLayout(null);
		
		
		JLabel battleView = new JLabel();
		battleView.setText("Battle View");
		battleView.setFont(new Font("Ariel", Font.BOLD, 28));
		Launcher.setComponent(battleView, midRes-60, 15, 200, 50, true);
		this.add(battleView);
		
		JLabel playerNameLabel = new JLabel();
		playerNameLabel.setText("Player Name: " );
		playerNameLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(playerNameLabel, 575-40+400, 0, 300, 25, false);
		this.add(playerNameLabel);

		JLabel turnCountLabel = new JLabel();
		turnCountLabel.setText("Turn Count: ");
		turnCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(turnCountLabel, 575-40+400, 0, 300, 75, false);
		this.add(turnCountLabel);

		JLabel foodCountLabel = new JLabel();
		foodCountLabel.setText("Food: " );
		foodCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(foodCountLabel, 575-40+400, 0, 300, 125, false);
		this.add(foodCountLabel);

		JLabel goldCountLabel = new JLabel();
		goldCountLabel.setText("Gold: ");
		goldCountLabel.setFont(new Font("Ariel", Font.BOLD, 17));
		Launcher.setComponent(goldCountLabel, 575-40+400, 0, 300, 175, false);
		this.add(goldCountLabel);
		
		JLabel attackingArmyName = new JLabel("Attacking Army: " + attackingArmy.getName());
		attackingArmyName.setFont(new Font("Ariel",Font.BOLD,18));
		Launcher.setComponent(attackingArmyName, 80, 70, 400, 50, false);
		this.add(attackingArmyName);
		
		attackingArmyPanel = new JPanel();
		JScrollPane scrollableAttackingArmyPanel = new JScrollPane(attackingArmyPanel);
		attackingArmyPanel.setLayout(new GridLayout(0,1,3,3));
		scrollableAttackingArmyPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollableAttackingArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableAttackingArmyPanel, 10, 130, 440, 540, false);
		this.add(scrollableAttackingArmyPanel);
		
		JLabel defendingArmyName = new JLabel("Defending Army: " + defendingArmy.getName());
		defendingArmyName.setFont(new Font("Ariel",Font.BOLD,18));
		Launcher.setComponent(defendingArmyName, 550, 70, 400, 50, false);
		this.add(defendingArmyName);
		
		defendingArmyPanel = new JPanel();
		JScrollPane scrollableDefendingArmyPanel = new JScrollPane(defendingArmyPanel);
		defendingArmyPanel.setLayout(new GridLayout(0,1,3,3));
		scrollableDefendingArmyPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollableDefendingArmyPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Launcher.setComponent(scrollableDefendingArmyPanel, 460, 130, 440, 540, false);
		this.add(scrollableDefendingArmyPanel);
		
		JButton manualAttack = new JButton("Manual Attack");
		Launcher.setComponent(manualAttack, 360, 10, 120, 70, false);
		this.add(manualAttack);
		
		JButton autoResolve = new JButton("Auto-Resolve");
		Launcher.setComponent(autoResolve, 680, 10, 120, 70, false);
		this.add(autoResolve);
		
		battleLog = new JTextArea();
		battleLog.setEditable(false);		
		battleLog.setFont(new Font("Ariel",Font.ITALIC,16));
		battleLog.append("Battle Log: " + "\n\n");
		battleLog.append("The battle will occur between: \nthe " + attackingArmy.getName() + " army and \nthe " + defendingArmy.getName() + " army\nat " + defendingArmy.getCurrentLocation() + "\n\n");
		battleLog.append(attackingArmy.getName() + " has " + attackingArmy.getUnits().size() + " units\n\n");
		battleLog.append(defendingArmy.getName() + " has " + defendingArmy.getUnits().size() + " units\n\n");
		battleLog.append("Please choose how you would like to proceed:\nManual Attack or Auto-Resolve");
		JScrollPane scrollableBattleLog = new JScrollPane(battleLog);
		Launcher.setComponent(scrollableBattleLog, 910, 130, 350, 540, false);
		this.add(scrollableBattleLog);
		
		JButton scrollToBottom = new JButton("Toggle Battlelog Update Lock");
		Launcher.setComponent(scrollToBottom, 40, 10, 230, 60, false);
		this.add(scrollToBottom);
		
		
		//functionality
		
		scrollToBottom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lock = !lock;
				battleLog.append("\nLock set to " + lock + "\n");
				if(lock) scrollableBattleLog.getVerticalScrollBar().setValue(scrollableBattleLog.getVerticalScrollBar().getMaximum());
			}
		});
		
		scrollableBattleLog.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            if(lock) {
	            	e.getAdjustable().setValue(e.getAdjustable().getMaximum());
	            }  
	        }
	    });
		
		
		
		for(Unit au : attackingArmy.getUnits()) {
			attackingArmyPanel.add(new AttackingUnitComponent(au, this));
		}
		
		for(Unit du : defendingArmy.getUnits()) {
			defendingArmyPanel.add(new DefendingUnitComponent(du, this));
		}
		
//		(int) (Math.random()*attackingArmy.getUnits().size())
//		(int) (Math.random()*defendingArmy.getUnits().size())
//		int result = JOptionPane.showConfirmDialog(null, "bruh","Enter Information", JOptionPane.PLAIN_MESSAGE);
//		if(result==JOptionPane.OK_OPTION) System.out.println("yikes");
		
		
		BattleView temp = this;
		
		manualAttack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=1;
				temp.remove(manualAttack);
				temp.remove(autoResolve);
				battleLog.append("\n\nYou have chosen Manual Attack\nYour Turn:\n");
				temp.revalidate();
				temp.repaint();
			}
		});
		
		
		
		autoResolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state=2;
				temp.remove(manualAttack);
				temp.remove(autoResolve);
				battleLog.append("\n\nYou have chosen Auto-Resolve\n");
				int turn = 1;
				while (attackingArmy.getUnits().size() != 0 && defendingArmy.getUnits().size() != 0) {
					Unit unit1 = attackingArmy.getUnits().get((int) (Math.random() * attackingArmy.getUnits().size()));
					Unit unit2 = defendingArmy.getUnits().get((int) (Math.random() * defendingArmy.getUnits().size()));
					if (turn == 1) {
						try {unit1.attack(unit2);}
						catch(FriendlyFireException ffe) {System.out.println("");}	
					}
						
					else {
						try {unit2.attack(unit1);}
						catch(FriendlyFireException ffe) {System.out.println("");}
					}
					turn = turn == 1 ? 0 : 1;
				}
				
				attackingArmyPanel.removeAll();
				for(Unit au : attackingArmy.getUnits()) {
					attackingArmyPanel.add(new AttackingUnitComponent(au, temp));
				}
				
				defendingArmyPanel.removeAll();
				for(Unit du : defendingArmy.getUnits()) {
					defendingArmyPanel.add(new DefendingUnitComponent(du, temp));
				}
				
				if(defendingArmy.getUnits().size()>0) {
					int result = JOptionPane.showConfirmDialog(null, "Your army has lost the battle","Auto-Resolve Outcome", JOptionPane.PLAIN_MESSAGE);
					if(result==JOptionPane.OK_OPTION) Launcher.initialiseWorldMap();
					
				}
				else {
					int result = JOptionPane.showConfirmDialog(null, "Your army has won the battle","Auto-Resolve Outcome", JOptionPane.PLAIN_MESSAGE);
					Launcher.getGame().occupy(attackingArmy, defendingArmy.getCurrentLocation());
					if(result==JOptionPane.OK_OPTION) Launcher.initialiseWorldMap();
				}

			}
		});
		
	}
	
	public int getState() {
		return this.state;
	}
	
	public void resetUnits() {
		this.attackingArmyPanel.removeAll();
		for(Unit au : this.atArmy.getUnits()) {
			this.attackingArmyPanel.add(new AttackingUnitComponent(au, this));
		}
		this.defendingArmyPanel.removeAll();
		for(Unit du : this.dfArmy.getUnits()) {
			this.defendingArmyPanel.add(new DefendingUnitComponent(du, this));
		}
	}
	
	public JTextArea getBattleLog() {
		return this.battleLog;
	}
	
	public Unit getAttackingUnit() {
		return this.attackingUnit;
	}
	
	public Army getAttackingArmy() {
		return this.atArmy;
	}
	
	public Army getDefendingArmy() {
		return this.dfArmy;
	}
	
	public void setAttackingUnit(Unit z) {
		this.attackingUnit = z;
	}
	
	public Unit getDefendingUnit() {
		return this.defendingUnit;
	}
	
	public void setDefendingUnit(Unit z) {
		this.defendingUnit = z;
	}
	
}
