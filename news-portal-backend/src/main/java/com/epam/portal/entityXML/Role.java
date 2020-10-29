//package com.epam.portal.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Role {
//
//    private long id;
//    private String name;
//    private List<User> userList;
//
//    public Role() {
//    }
//
//    public Role(String name, List<User> userList) {
//        this.name = name;
//        this.userList = userList;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }
//
//    public void add(User tempUser) {
//
//        if (userList == null) {
//            userList = new ArrayList<>();
//        }
//
//        userList.add(tempUser);
//
//        tempUser.setRole(this);
//    }
//
//}
