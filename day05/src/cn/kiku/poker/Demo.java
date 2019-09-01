package cn.kiku.poker;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        ArrayList<String> poker = new ArrayList<>();

        poker.add("大王");
        poker.add("小王");

        String[] colors = {"♠", "♥", "♣", "♦"};
        String[] nums = { "3", "4", "5", "6", "7", "8",
                          "9", "10", "J", "Q", "K", "A", "2"  };

        

        for (String color : colors) {
            for (String num : nums) {
                //System.out.println(color+num);
                poker.add(color+num);
            }
        }

        Collections.shuffle(poker);

        ArrayList<String> play1 = new ArrayList<>();
        ArrayList<String> play2 = new ArrayList<>();
        ArrayList<String> play3 = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();

        for (int i = 0; i < poker.size(); i++) {
            if(i >= 51){
                dipai.add(poker.get(i));
            }else if(i % 3 ==0){
                play1.add(poker.get(i));
            }else if(i % 3 ==1){
                play2.add(poker.get(i));
            }else if(i % 3 ==2){
                play3.add(poker.get(i));
            }
        }
        //System.out.println(poker);
        System.out.println(play1);
        System.out.println(play2);
        System.out.println(play3);
        System.out.println(dipai);
        System.out.println("mxy");
    }
}
