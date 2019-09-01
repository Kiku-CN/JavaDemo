package cn.kiku.demo01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public  class CopyByByte {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\89236\\Desktop\\AC全新翻译图 .jpg");
        FileOutputStream fos = new FileOutputStream("d:\\c.png");
        long start = System.currentTimeMillis();

        byte[] bytes = new byte[1024];

        int len = 0;
        while((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        System.out.println("复制完毕");

        long end = System.currentTimeMillis();

        System.out.println("复制时间为:"+(end - start)+"毫秒");

        fos.close();
        fis.close();
    }
}