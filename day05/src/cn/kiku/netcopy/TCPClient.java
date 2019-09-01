package cn.kiku.netcopy;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        //创建本地字节流
        FileInputStream fis = new FileInputStream("d:\\a.png");
        //创建客户端Socket
        Socket socket = new Socket("localhost",8923);

        OutputStream os = socket.getOutputStream();
        //读取本地文件输入流并写入到网络字节输出流
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes, 0 , len);
        }
        //手动关闭网络输出流,防止服务器端阻塞
        socket.shutdownOutput();
        //获取网络字节输入流,并输出到控制台
        InputStream is = socket.getInputStream();
        while ((len = is.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0 ,len));
        }
        //释放资源
        fis.close();
        socket.close();

    }
}
