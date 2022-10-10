package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class testA {
    public static void main(String[] args) {
        List<String> a = Arrays.asList("1","2","3","4","5","6","7");
        List<String> b = Arrays.asList("1","2","3","4");
        boolean flag=false;
        for(int i =0;i<a.size();i++){
            flag=true;
            for(int j=0;j<b.size();j++) {
                if (b.contains(a.get(i))) {
                    flag=false;
                    break;
                }
            }
            if(flag){
                if(a.get(i).equals("1")){
                    log.info("1");
                }else if(a.get(i).equals("2")){
                    log.info("2");
                }else if(a.get(i).equals("3")){
                    log.info("3");
                }else if(a.get(i).equals("4")){
                    log.info("4");
                }else if(a.get(i).equals("5")){
                    log.info("5");
                }else if(a.get(i).equals("6")){
                    log.info("6");
                }else if(a.get(i).equals("7")){
                    log.info("7");
                }
            }
        }
    }
}
