package cn.kiku.day01;

public class MyOuter {

    public void methodOuter(){
        int num = 10;
        num = 290;

        class MyInner {
           int num;

           public void method() {
               System.out.println(num);
           }
        }
    }
}
