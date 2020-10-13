package com.nju.software.demo;

import java.util.Arrays;

public class Test {

    @org.junit.Test
    public void a1(){
//        checkRecord(4);
        helper(0,0);
        System.out.println(max);
    }

    int [][] arr = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
    int n = 4;
    int m = 5;
    int max = 0;
    public void helper(int i,int j){
        if(i<0||i>=n||j<0||j>=m){
            return ;
        }
        if(arr[i][j]==0){
            return ;
        }
        arr[i][j] = 0;
        max+=1;
        helper(i,j+1);
        helper(i,j-1);
        helper(i+1,j);
        helper(i-1,j);

    }
}
