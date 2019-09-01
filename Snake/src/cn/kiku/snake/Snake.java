package cn.kiku.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static cn.kiku.snake.Dir.*;

public class Snake {
	private Node head;
	private Node tail;
	private int size;
	public boolean alive = true;
	public boolean speeding = false;

	private Node origin = new Node(11, 11, R);

	public Snake() {
		head = origin;
		tail = origin;
		size = 1;
	}


	public void addToTail() {
		Node newTail = null;
		switch(tail.dir) {
			case U:
				newTail = new Node(tail.column, tail.row + 1, tail.dir);
				break;
			case D:
				newTail = new Node(tail.column, tail.row - 1, tail.dir);
				break;
			case L:
				newTail = new Node(tail.column + 1,tail.row, tail.dir);
				break;
			case R:
				newTail = new Node(tail.column - 1, tail.row, tail.dir);
				break;
		}
		tail.next = newTail;
		newTail.prev = tail;
		tail = newTail;
		size++;
	}

	public void addToHead() {
		Node newHead = null;
		switch(head.dir) {
			case U:
				newHead = new Node(head.column, head.row - 1, head.dir);
				break;
			case D:
				newHead = new Node(head.column, head.row + 1, head.dir);
				break;
			case L:
				newHead = new Node(head.column - 1,head.row, head.dir);
				break;
			case R:
				newHead = new Node(head.column + 1, head.row, head.dir);
				break;
		}
		head.prev = newHead;
		newHead.next = head;
		head = newHead;
		size++;
	}

	public void deleteTail() {
		if(size == 0) return;
		tail = tail.prev;
		tail.next = null;
		size--;
	}

	public void move() {
		addToHead();
		deleteTail();
		checkLive();
	}
	//检测是否蛇头是否撞墙或者撞到自己
	private void checkLive() {
		if (head.column < 0 || head.column > Yard.COLUMNS ||
				head.row < 0 || head.row > Yard.ROWS) {
			alive = false;
		}
		Node node = head.next;
		while (node != null) {
			if(node.column == head.column && node.row == head.row) {
				alive =false;
			}
			node = node.next;
		}
	}

	public void draw(Graphics g) {
		if(size <= 0) return;

		for (Node n = head; n != null; n = n.next)
		{
			n.draw(g);
		}
	}


	//按键事件处理
	public void keyHandle(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key){
			case KeyEvent.VK_UP:
				if (head.dir != Dir.D) {
					head.dir = Dir.U;
					if(head.dir == Dir.U)
						speeding = true;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (head.dir != Dir.U) {
					head.dir = Dir.D;
					if(head.dir == Dir.D)
						speeding = true;
				}
				break;
			case KeyEvent.VK_LEFT:
				if (head.dir != Dir.R) {
					head.dir = Dir.L;
					if(head.dir == Dir.L)
						speeding = true;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (head.dir != Dir.L) {
					head.dir = Dir.R;
					if(head.dir == Dir.R)
						speeding = true;
				}
				break;
			case KeyEvent.VK_ENTER:
				Yard.snake = new Snake();
				Yard.score = 0;
				break;
		}
	}
	//获取碰撞举行
	private Rectangle getRect() {
		return new Rectangle(head.column * Yard.GRID_SIZE + Yard.insets.left,
				head.row * Yard.GRID_SIZE + Yard.insets.top, head.width, head.height);
	}
	//碰撞检测
	public  boolean eat(Egg egg) {
		if (this.getRect().intersects(egg.getRect())) {
		//if (true) {
			egg.revive();
			addToTail();
			return true;
		}
		return false;
	}

	private class Node {
		private int width = Yard.GRID_SIZE;
		private int height = Yard.GRID_SIZE;
		int row, column;
		Node next = null;
		Node prev = null;
		Dir dir;

		Node(int column,int row, Dir dir) {
			this.column = column;
			this.row = row;
			this.dir = dir;
		}

		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(column * Yard.GRID_SIZE + Yard.insets.left, row * Yard.GRID_SIZE + Yard.insets.top,
					width, height);
			g.setColor(c);
		}
	}
}
