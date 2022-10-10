package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args){
        List lis = new ArrayList<>();
        for(int i=100;i<=200;i++){
            boolean flag=true;
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                lis.add(i);
                System.out.print(" "+i);
            }
        }
        System.out.println(" "+lis.size());
    }

}
