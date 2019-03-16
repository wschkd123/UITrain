package com.wsc.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author shichao5
 * @date 2019/1/22
 * @describ
 */
public class TestSort {

    public static void main(String[] args) {
        final List<Integer> re = new ArrayList<>();

        re.add(1);
        re.add(2);
        re.add(6);
        re.add(5);
        re.add(8);
        re.add(9);
        re.add(4);

        Collections.sort(re, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //下面这么写，结果是降序
                if (o1 < o2) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                }
                return 0;
            }

        });

        System.out.println(re);
    }

}
