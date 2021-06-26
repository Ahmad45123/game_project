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
import units.*;

public class BattleView extends JPanel {

	private int xRes = Launcher.getxRes();
	private int yRes = Launcher.getyRes();
	private int midRes = Launcher.getMidRes();
	private int state = 0;

	//TODO: bring back proper logic after testing
	
	public BattleView(Army attackingArmy, Army defendingArmy) {
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
		
		JPanel attackingArmyPanel = new JPanel();
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
		
		JPanel defendingArmyPanel = new JPanel();
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
		
		JTextArea battleLog = new JTextArea();
		battleLog.setEditable(false);		
		battleLog.setFont(new Font("Ariel",Font.ITALIC,16));
		battleLog.append("Battle Log: " + "\n\n");
		battleLog.append("The battle will occur between: \nthe " + attackingArmy.getName() + " army and \nthe " + defendingArmy.getName() + " army\nat " + attackingArmy.getCurrentLocation() + "\n\n");
		battleLog.append(attackingArmy.getName() + " has " + attackingArmy.getUnits().size() + " units\n\n");
		battleLog.append(defendingArmy.getName() + " has " + defendingArmy.getUnits().size() + " units\n\n");
		battleLog.append("Please choose how you would like to proceed:\nManual Attack or Auto-Resolve");
		JScrollPane scrollableBattleLog = new JScrollPane(battleLog);
		Launcher.setComponent(scrollableBattleLog, 910, 130, 350, 540, false);
		this.add(scrollableBattleLog);
		
		
		//functionality
		for(Unit au : attackingArmy.getUnits()) {
			attackingArmyPanel.add(new AttackingUnitComponent(au));
		}
		
		for(Unit du : defendingArmy.getUnits()) {
			defendingArmyPanel.add(new DefendingUnitComponent(du));
		}
		
		//(int) (Math.random()*attackingArmy.getUnits().size())
		//(int) (Math.random()*defendingArmy.getUnits().size())
		
	}
}
