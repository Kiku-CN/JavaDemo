package cn.kiku.demo02;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileSort {
    public static void main(String[] args) throws IOException {

        Map<String,String> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));

        String line;

        while ((line = br.readLine()) != null) {
            String[] split = line.split("-");
            map.put(split[0], split[1]);
        }
        System.out.println(map);

        for (String s : map.keySet()) {
            bw.write(s+"->"+map.get(s));
            bw.newLine();
        }

        bw.close();
        br.close();



    }
}
