package uniandes.dpoo.taller4.view.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.view.Game;
import uniandes.dpoo.taller4.view.LightsOut;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener {
	private static final String[] KEYS = { "NUEVO", "REINICIAR", "TOP 10", "CAMBIAR JUGADOR" };
	private Game parent;

	public Menu(Game game) {
		this.parent = game;
		JButton[] menuBtn = new JButton[4];

		for (int i = 0; i < 4; i++) {
			menuBtn[i] = new JButton(KEYS[i]);
			menuBtn[i].setForeground(LightsOut.BASECOL);
			menuBtn[i].setFont(LightsOut.FONT);
			menuBtn[i].setBackground(Color.WHITE);
			menuBtn[i].addActionListener(this);
			add(menuBtn[i]);
		}
		setBackground(LightsOut.BASECOL);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String lbl = e.getActionCommand();
		if (KEYS[0].equals(lbl)) {
			parent.newGame();
		} else if (KEYS[1].equals(lbl)) {
			parent.restart();
		} else if (KEYS[2].equals(lbl)) {
			parent.top10();
		} else if (KEYS[3].equals(lbl)) {
			parent.change();
		}
	}

}
