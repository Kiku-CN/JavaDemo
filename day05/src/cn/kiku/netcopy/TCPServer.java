package cn.kiku.netcopy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server =new ServerSocket(8923);
        while (true) {
            Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();
                        File file = new File("d:\\upload");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        //创建本地字节输出流
                        String fileName = "kiku" + new Random().nextInt(100)+System.currentTimeMillis() + ".png";
                        FileOutputStream fos = new FileOutputStream(file + "\\" + fileName);
                        //读取网络字节输入流写入到本地字节输出流
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while ((len = is.read(bytes)) != -1) {
                            fos.write(bytes, 0 ,len);
                        }
                        //获取网络字节输出流,并写入字符串
                        OutputStream os = socket.getOutputStream();
                        os.write("上传成功".getBytes());
                        //释放资源
                        fos.close();
                        socket.close();
                    }   catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        //server.close();
    }
}
