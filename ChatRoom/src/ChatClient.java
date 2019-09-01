import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
//版本1.3
public class ChatClient extends Frame {
	TextField tf = null;
	TextArea ta = null;
	Socket socket = null;
	OutputStreamWriter ow = null;
	InputStreamReader ir = null;
	int count = 0;

	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	void launchFrame() {
		setLocation(300, 300);
		setSize(400, 400);
		tf = new TextField();
		ta = new TextArea();
		ta.setEditable(false);
		tf.addActionListener(new TFListener());
		add(ta, BorderLayout.NORTH);
		add(tf, BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					socket.shutdownOutput();
					System.out.println("关闭窗口,释放资源中······");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		pack();
		setVisible(true);
		connect();
		receive();
	}

	private void connect() {
		try {
			socket = new Socket("127.0.0.1", 8888);
			try {
				ow = new OutputStreamWriter(socket.getOutputStream());
				ir = new InputStreamReader(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("连接成功");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("连接失败");
			System.exit(-1);
		}
	}

	private void disconnect() {
		try {
			ow.close();
			ir.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void receive() {
		char[] chars = new char[1024];
		int len = 0;
		while (true) {
			try {
				len = ir.read(chars);
				if (len != -1) {
					String rec = new String(chars, 0, len);
					System.out.println("接收到其他用户的消息:" + rec);
					ta.append(rec + "\n");
				} else {
					System.out.println("断开与服务器的连接");
					System.exit(-1);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

	private class TFListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String input = tf.getText().trim();
			tf.setText("");
			//ta.append(input + "\n");
			try {
				ow.write(input);
				ow.flush();
				//ow.close();
				System.out.println("发送完成");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
