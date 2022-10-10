package com.example.demo.test;

import com.example.demo.emun.testEnmu;
import com.example.demo.util.EnumUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class testB {
    public static void main(String[] args) {
        //log.info("*********"+testEnmu.values().getClass().);
        //Enum.GetName(typeof(d), i) == null
        //log.info("*******"+ EnumUtils.isValidEnum(testEnmu.class, "PPPP"));

        /*Map<String,String> map = new HashMap<>();
        map.put("aaaa","A");
        map.put("bbbb","A");
        map.put("cccc","A");
        for(Map.Entry<String,String> entry: map.entrySet()){
             log.info("key={}",entry.getKey());
             log.info("value={}",entry.getValue());
        }*/
    }

}
