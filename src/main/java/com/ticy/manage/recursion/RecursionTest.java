package com.ticy.manage.recursion;

import com.ticy.manage.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author tkk
 * @Time 2021/8/17 14:22
 * @Description todo
 */
@RestController
public class RecursionTest {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @RequestMapping("/recursionTest")
    public List<Menu> recursionTest() {

        List<Menu> firstList = userInfoMapper.getMenuByPid("0");
        // List<Menu> collect = firstList.stream().filter(menu -> menu.getParentId().equals("0")).collect(Collectors.toList());
        for (Menu menu : firstList) {
            menu.setChirdMenu(getChildrenMenu(menu.getId()));
        }
        return firstList;

    }


    public List<Menu> getChildrenMenu(String pid) {
        //List<Menu> result = new ArrayList<>();
        List<Menu> list = userInfoMapper.getMenuByPid(pid);
        for (Menu menu : list) {
            menu.setChirdMenu(getChildrenMenu(menu.getId()));
        }

        return list;
    }

    @RequestMapping("/recursionTest2")
    public List<Menu> recursionTest2() {

        List<Menu> firstList = userInfoMapper.getMenuByPid("");
        List<Menu> collect = firstList.stream().filter(menu -> menu.getParentId().equals("0")).collect(Collectors.toList());
        collect.stream().forEach(menu -> {
            menu.setChirdMenu(getChildrenMenu2(firstList, menu.getId()));
        });
        return collect;
    }

    public List<Menu> getChildrenMenu2(List<Menu> allList, String pid) {
        List<Menu> collect = allList.stream().filter(menu -> menu.getParentId().equals(pid)).collect(Collectors.toList());

        collect.forEach(menu -> {
            menu.setChirdMenu(getChildrenMenu2(allList,menu.getId()));
        });
        return collect;
    }
}
