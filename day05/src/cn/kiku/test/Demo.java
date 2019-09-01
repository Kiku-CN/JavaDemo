package cn.kiku.test;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInput;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Demo {


	public static void main(String[] args) throws IOException {
		Frame f = new Frame("MyFirst");
		f.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Frame f = (Frame)e.getSource();
				System.out.println("yaya");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
			}
		});
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(9);
			}
		});
		f.pack();
		//f.setVisible(true);
		DataInput i;

	}

}

class TFFrame extends Frame {
	TextField num1, num2, num3;



	public void launchFrame () {
		num1 = new TextField(10);
		num2 = new TextField(10);
		num3 = new TextField(20);
		Label lblPlus = new Label("+");
		Button btEqual = new Button("=");
		btEqual.addActionListener(new MyActionListener(this));
		setLayout(new FlowLayout());
		this.add(num1);
		this.add(lblPlus);
		this.add(num2);
		this.add(num3);
		this.add(btEqual);
		this.pack();
		this.setVisible(true);
	}
}

class MyActionListener implements ActionListener {

	TFFrame f ;

	public MyActionListener (TFFrame f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int n1 = Integer.parseInt(f.num1.getText());
		int n2 = Integer.parseInt(f.num2.getText());
		f.num3.setText((n1+n2)+"");

	}
}