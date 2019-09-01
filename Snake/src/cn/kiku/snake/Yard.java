package cn.kiku.snake;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Date;

public class Yard extends Frame {
	public static final int ROWS = 30;
	public static final int COLUMNS = 30;
	public static final int GRID_SIZE = 20;
	public static final int YARD_WIDTH = COLUMNS * GRID_SIZE;
	public static final int YARD_HEIGHT = ROWS * GRID_SIZE;
	public static Insets insets;
	public static int windowsWidth;
	public static int windowHeight;
	public static boolean over = false;
	public static int score = 0;
	public PaintThread paintThread = new PaintThread();


	public static Yard yard;

	public static Snake snake = new Snake();;
	public static Egg egg = new Egg();

	void launch() {
		setVisible(true);
		insets = getInsets();
		windowsWidth = COLUMNS * GRID_SIZE + insets.right + insets.left;
		windowHeight = ROWS * GRID_SIZE + insets.top + insets.bottom;
		//初始化窗口
		setLocation(100, 100);
		System.out.println(Arrays.toString(new int[]{insets.left,insets.right, insets.top,insets.bottom}));
		setSize(windowsWidth, windowHeight);
		setResizable(false);
		//窗口监听器
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		} );
		//添加监听器
		addKeyListener(new KeyMonitor());
		//多线程
		new Thread(paintThread).start();
		/*while(true) {
			if (over) {
				System.out.println("LLLLL");
				Dialog d = new Dialog(yard,"gameOver",false);
				d.setLayout(new GridLayout());
				Button b1 = new Button("GG");
				d.add(b1);
				d.setBounds(YARD_WIDTH / 2, YARD_WIDTH /2, 100 , 100);
				b1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						d.setVisible(false);
						System.exit(-1);
					}
				});
				d.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						d.setVisible(false);
					}
				});
				d.setVisible(true);
			}
			over = false;
		}*/


	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		Color c = g.getColor();
		Insets insets = getInsets();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D)g;
		//画边界
		for (int i = 0; i < ROWS + 1; i++) {
			//边界特殊画笔
			if (i == 0 || i == ROWS) {
				g2.setStroke(new BasicStroke(5.0f));
				g2.setColor(Color.RED);
			}
			g.drawLine(0 + insets.left, GRID_SIZE * i + insets.top,
					COLUMNS * GRID_SIZE + insets.left, GRID_SIZE * i + insets.top);
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(2.0f));
		}
		for (int i = 0; i < COLUMNS + 1; i++) {
			//边界特殊画笔
			if ((i == 0 || i == ROWS)) {
				g2.setStroke(new BasicStroke(5.0f));
				g2.setColor(Color.RED);
			}
			g.drawLine(GRID_SIZE * i + insets.left, 0 + insets.top,
					GRID_SIZE * i + insets.left, COLUMNS * GRID_SIZE + insets.top);
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(2.0f));
		}
		g.setColor(c);

		if (!snake.alive) {
			Color cc = g.getColor();
			g.setColor(Color.RED);
			g.setFont(new Font("宋体", Font.BOLD, 50));
			g.drawString("游戏结束", YARD_WIDTH / 2 -100,YARD_HEIGHT / 2);
			g.drawString("你的得分为:" + score, YARD_WIDTH / 2 -160,YARD_HEIGHT / 2+50);
			g.drawString("请按回车键重新开始游戏",YARD_WIDTH / 2 - 280,YARD_HEIGHT / 2 + 100);
			g.setColor(cc);
		} else {
			//画物体
			egg.draw(g);
			if(snake.eat(egg))
			{
				//吃蛋立马重画防止画面叠加
				repaint();
				score++;
			} else {
				snake.move();
			}
			snake.draw(g);
			g.setColor(Color.RED);
			g.setFont(new Font("宋体", Font.BOLD, 22));
			g.drawString("Score:" + score * 1, 20, 60);
		}
	}

	//双缓冲消除闪烁
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		//super.update(g);
		if (offScreenImage == null) {
			offScreenImage = this.createImage(windowsWidth, windowHeight);
		}
		Graphics goff = offScreenImage.getGraphics();
		paint(goff);
		g.drawImage(offScreenImage,0, 0,null);
	}

	//内部线程类实现多线程
	private class PaintThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}

		}

	}


	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			snake.speeding =false;
			snake.keyHandle(e);
			if (snake.speeding) {
				Yard.this.repaint();
			}
		}
	}

	public static void main(String[] args) {
		yard = new Yard();
		yard.launch();
	}

}

