package uniandes.dpoo.taller4.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class LightsOut {
	
	public static final Font FONT = new Font("SansSerif", Font.BOLD, 13);
	public static final Color BASECOL = new Color(0, 102, 255);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

}
