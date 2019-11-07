package com.offcn.springboot.controller;

import com.offcn.springboot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TestRestFul {

    private List<User> listUser=Collections.synchronizedList(new ArrayList<User>());

    /***
     * 获取全部用户信息
     * @return
     */
    @GetMapping("/info")
    public List<User> getUserList(){
        return listUser;
    }

    /***
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/info")
    public String createUser(User user) {
        listUser.add(user);
        return "success";
    }

    /***
     * 获取指定id用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id")Long id) {
        for (User user : listUser) {
            if(user.getId()==id) {
                return user;
            }
        }
        return null;
    }
    /**
     * 更新指定id用户信息
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id,User user) {
        for (User user2 : listUser) {
            if(user2.getId()==id) {
                user2.setName(user.getName());
                user2.setAge(user.getAge());
            }
        }
        return "success";
    }

    /***
     * 删除指定id用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        listUser.remove(getUser(id));
        return "success";
    }

}
