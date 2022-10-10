package com.example.demo.test;

import com.example.demo.bean.UserInfo;
import com.example.demo.util.ListUtilsMain;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SortListTest {
    public static void main(String[] args) throws Exception {

        SortListTest sortListTest =new SortListTest();
        List<UserInfo> list = new ArrayList<UserInfo>();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        UserInfo user1 = new UserInfo(3, "bbb", formater.parse("1980-12-01"), 1, 1.2f, 'a');
        UserInfo user2 = new UserInfo(0, "126", formater.parse("1900-10-11"), 03, -3.6f, 'c');
        UserInfo user3 = new UserInfo(12, "5", formater.parse("1973-08-21"), 15, 9.32f, 'f');
        UserInfo user4 = new UserInfo(465, "1567", formater.parse("2012-01-26"), 20, 12.56f, '0');
        UserInfo user5 = new UserInfo(2006, "&4m", formater.parse("2010-05-08"), 100, 165.32f, '5');
        UserInfo user6 = new UserInfo(5487, "hf67", formater.parse("2016-12-30"), 103, 56.32f, 'm');
        UserInfo user7 = new UserInfo(5487,"jigg", formater.parse("2000-10-16"), 103, 56.32f, 'm');
        UserInfo user8 = new UserInfo(5487, "jigg", formater.parse("1987-07-25"), 103, 56.32f, 'm');

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        list.add(user8);

        log.info("-------原来序列-------------------");

        // 按userId升序、username降序、birthDate升序排序
        String [] sortNameArr = {"userId","username","birthDate"};
        boolean [] isAscArr = {true,false,true};
        ListUtilsMain.sort(list,sortNameArr,isAscArr);
        log.info("-------按按userId升序、username降序、birthDate升序排序（如果userId相同，则按照username降序,如果username相同，则按照birthDate升序）------------------");
        sortListTest.printfUserInfoList(list);
        // 按userId、username、birthDate都升序排序
        ListUtilsMain.sort(list, true, "userId", "username","birthDate");
        log.info("--------按userId、username、birthDate排序（如果userId相同，则按照username升序,如果username相同，则按照birthDate升序）------------------");
        sortListTest.printfUserInfoList(list);
        // 按userId、username都倒序排序
        ListUtilsMain.sort(list, false, "userId", "username");
        log.info("--------按userId和username倒序（如果userId相同，则按照username倒序）------------------");
        sortListTest.printfUserInfoList(list);
        // 按username、birthDate都升序排序
        ListUtilsMain.sort(list, true, "username", "birthDate");
        log.info("----------按username、birthDate升序（如果username相同，则按照birthDate升序）-----------------");
        sortListTest.printfUserInfoList(list);
        // 按birthDate倒序排序
        ListUtilsMain.sort(list, false, "birthDate");
        log.info("-----------按birthDate倒序---------------------");
        sortListTest.printfUserInfoList(list);
        // 按fRate升序排序
        ListUtilsMain.sort(list, true, "fRate");
        log.info("--------按fRate升序---------------");
        sortListTest.printfUserInfoList(list);
        // 按ch倒序排序
        ListUtilsMain.sort(list, false, "ch");
        log.info("-------按ch倒序----------------");
        sortListTest.printfUserInfoList(list);
    }

    private void printfUserInfoList(List<UserInfo> list) {
        for (UserInfo user : list) {
            System.out.println(user.toString());
        }
    }
}
