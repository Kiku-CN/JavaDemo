package cn.kiku.demo01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {


	public static void main(String[] args) {
		Frame f = new Frame("haha");
		f.setBounds(100,100,222 ,100);
		System.out.println("RR");

		System.out.println("LL");
		f.setVisible(true);
		Dialog d = new Dialog(f,"game Over",true);
		d.setLayout(new GridLayout());
		Button b1 = new Button("replay");
		d.add(b1);
		d.setBounds(100, 100 ,100 , 100);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("iuuuu");
			}
		});
		d.setVisible(true);

	}

}
