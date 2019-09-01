import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ChatServer {

	ServerSocket ss = null;
	ArrayList<OutputStreamWriter> ows = new ArrayList<>();
	ArrayList<Client> clients = new ArrayList<>();

	public static void main(String[] args) {
		new ChatServer().start();
	}

	private void start() {
		try {
			ss = new ServerSocket(8888);
			System.out.println("开始监听");
		} catch (IOException e) {
			System.out.println("端口被占用");
			System.exit(-1);
		}
		while (true) {
			Socket accept = null;
			try {
				accept = ss.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Client c = new Client(accept);
			clients.add(c);
			System.out.println("一位新用户接入," + "当前共有" + clients.size() + "位用户在线");
			new Thread(c).start();
		}
	}

	private class Client implements Runnable {
		InputStreamReader ir = null;
		OutputStreamWriter ow = null;
		private Socket s;

		Client(Socket s) {
			this.s = s;
			try {
				ir = new InputStreamReader(s.getInputStream());
				//ows.add(new OutputStreamWriter(s.getOutputStream()));
				ow = new OutputStreamWriter(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					char[] chars = new char[1024];
					int len = ir.read(chars);
					String input = null;
					if (len != -1) {
						input = new String(chars, 0, len);
						System.out.println("收到用户消息:" + input);
					} else {
						s.shutdownOutput();
						clients.remove(this);
						System.out.println("一位用户注销");
						break;
					}
					for (Client c : clients) {
						c.ow.write(input);
						c.ow.flush();
					}
				}
			} catch (SocketException e) {
				System.out.println("一位用户异常退出");
				clients.remove(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
