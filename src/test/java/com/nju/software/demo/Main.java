package com.nju.software.demo;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            System.out.println(in.nextInt());
            System.out.println(in.next());
//            int n = in.nextInt();
//        /* nextLine()是扫描器执行当前行，并返回跳过的输入信息，特别需要注意！！！
//
//            如果没有该行，则执行第一个in.nextLine()命令时的返回值是int n = in.nextInt()的值*/
//            in.nextLine();
//            HashSet<String> set = new HashSet<String>();
//            for (int i = 0; i < n; i++) {
//                String line =
//
//                        in.nextLine();
//                String[] arr = line.split(" ");
//                for (int j = 0; j < arr.length; j++) {
//                    set.add(arr[j]);
//                }
//            }
//            System.out.println("sum:" + set.size());

        }
    }

    @Test
    public void test1(){
        String str = "abcd";
        char[] ch = str.toCharArray();
//        System.out.println(ch.toString());

        ch[1] = ' ';
        String s ="" ;
        for(char i:ch){
            if(i!=' '){
                s+=i;
            }

        }
        System.out.println(s);

    }
    @Test
    public void test2(){
//        String str1 = "abcdabcd";
//        String str2 = "cdabe";
//        System.out.println(str1.indexOf(str2));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(String.valueOf(0));
        System.out.println(Integer.parseInt("-340"));
    }
}
