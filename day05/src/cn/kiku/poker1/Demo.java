package cn.kiku.poker1;

import java.util.*;

public class Demo {
    static Map<Integer, String> pokers = new HashMap<>();
    public static void main(String[] args) {
        List<String> colors = List.of("♠", "♥", "♣", "♦");
        List<String> nums = List.of("3", "4", "5", "6", "7", "8",
                "9", "10", "J", "Q", "K", "A", "2" );

        int i = 0;
        for (int n = 0; n < nums.size(); n++) {
            for (int c = 0; c < colors.size(); c++) {
                pokers.put(i,colors.get(c) + nums.get(n));
                i++;
            }
        }
        pokers.put(++i, "小王");
        pokers.put(++i, "大王");
        //System.out.println(pokers);

        List<Integer> index= new ArrayList(pokers.keySet());
        //System.out.println(index);

        Collections.shuffle(index);

        List<Integer> dipai = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        List<Integer> p3 = new ArrayList<>();

        for (int ii = 0; ii < index.size(); ii++) {
            if (ii >= 51) {
                dipai.add(index.get(ii));

            } else if (ii % 3 == 0) {
                p1.add(index.get(ii));
            } else if (ii % 3 == 1) {
                p2.add(index.get(ii));
            } else if (ii % 3 == 2) {
                p3.add(index.get(ii));
            }
        }
        //比较器
        class Compara implements Comparator <Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        Compara c = new Compara();


        Collections.sort(p1,c);
        Collections.sort(p2,c);
        Collections.sort(p3,c);
        Collections.sort(dipai,c);
        show(p1);
        System.out.println();
        show(p2);
        System.out.println();
        show(p3);
        System.out.println();
        show(dipai);
        System.out.println();

    }

    public static void show(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(pokers.get(integer));
        }
    }


}
