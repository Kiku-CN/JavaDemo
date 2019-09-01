package cn.kiku.day02;

public class Laptop {
    public  void on() {
        System.out.println("开机");
    }

    public void  off() {
        System.out.println("关机");
    }

    public void useUSB(USB usb) {
        usb.open();
        if(usb instanceof Mouse) {
            ((Mouse) usb).click();

        }
        if(usb instanceof KeyBoard) {
            ((KeyBoard) usb).type();

        }

        usb.close();
    }
}
