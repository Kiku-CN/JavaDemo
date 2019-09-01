package cn.kiku.snake;

import java.awt.*;
import java.util.Random;

public class Egg
{
	private int column, row;
	private int width = Yard.GRID_SIZE;
	private int height = Yard.GRID_SIZE;
	static Random random = new Random();
	Color color = null;

	public Egg(int column, int row) {
		this.column = column;
		this.row = row;
	}
	public Egg() {
		this(random.nextInt(Yard.COLUMNS),random.nextInt(Yard.ROWS));
	}

	public void revive() {
		column = random.nextInt(Yard.COLUMNS);
		row = random.nextInt(Yard.ROWS);
	}

	public Rectangle getRect() {
		return new Rectangle(column * width + Yard.insets.left, row * height + Yard.insets.top,
				width, height);
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillOval(column * width + Yard.insets.left, row * height + Yard.insets.top,
				width, height);
		if (g == null) {
			System.out.println("空画笔");
		}

		g.setColor(c);
	}
}
