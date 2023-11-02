package uniandes.dpoo.taller4.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JPanel;

import uniandes.dpoo.taller4.view.Game;
import uniandes.dpoo.taller4.view.LightsOut;

@SuppressWarnings("serial")
public class Grid extends JPanel implements MouseMotionListener, MouseListener {
	private int square = 5;
	private Game parent;
	private int space = 2;
	private int e80;
	private int mx = -100;
	private int my = -100;
	private boolean pause = false;

	public Grid(Game game, int i) {
		setBorder(null);
		this.square = i;
		this.parent = game;
		setPreferredSize(new Dimension(500, 500));
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void setSquare(int n) {
		this.square = n;
	}

	public int getSquare() {
		return this.square;
	}

	@Override
	protected void paintComponent(Graphics gB) {
		Graphics2D g = (Graphics2D) gB;
		g.setColor(LightsOut.BASECOL);
		g.fillRect(0, 0, getSize().width, getSize().height);

		e80 = Math.min(getSize().width, getSize().height) / square;
		for (int i = 0; i < square && !pause; i++) {
			for (int j = 0; j < square; j++) {
				g.setColor(Color.DARK_GRAY);
				if (parent.isOn(i, j))
					g.setColor(Color.yellow);
				if (space + i * e80 <= mx && mx < space + i * e80 + e80 - 2 * space) {
					if (space + j * e80 <= my && my < space + j * e80 + e80 - 2 * space) {
						g.setColor(Color.white);
					}
				}
				g.fillRect(space + i * e80, space + j * e80, e80 - 2 * space, e80 - 2 * space);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int cX = e.getX();
		int cY = e.getY();
		int[] cells = getCellByCoord(cX, cY);
		if (!Arrays.equals(cells, new int[] { -1, -1 })) {
			parent.play(cells);
		}
		repaint();
	}

	private int[] getCellByCoord(int cX, int cY) {
		for (int i = 0; i < square; i++) {
			for (int j = 0; j < square; j++) {
				if (space + i * e80 <= cX && cX < space + i * e80 + e80 - 2 * space) {
					if (space + j * e80 <= cY && cY < space + j * e80 + e80 - 2 * space) {
						return new int[] { i, j };
					}
				}
			}
		}
		return new int[] { -1, -1 };
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
		repaint();
	}
}
