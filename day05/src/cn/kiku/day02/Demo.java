package cn.kiku.day02;

public class Demo {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        laptop.on();

        laptop.useUSB(new KeyBoard());
        laptop.useUSB(new Mouse());
        laptop.toString();

        laptop.off();
    }
}
