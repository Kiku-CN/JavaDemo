package cn.kiku.day02;

public class KeyBoard implements USB {

    @Override
    public void open() {
        System.out.println("接入键盘");
    }

    @Override
    public void close() {
        System.out.println("关闭键盘");

    }

    public void type() {
        System.out.println("敲键盘");
    }
}
