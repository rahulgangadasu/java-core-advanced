package java_code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collections1 {
    public static void main(String[] args) {
        List <Integer> list = new ArrayList<>();
        list.add(10);
        Collections.addAll(list, 20, 30, 50);
        System.out.println(list);
    }
}
