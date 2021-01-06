package com.openlab.controller;

import com.openlab.pojo.User;
import com.openlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/managerUser")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/getUserList")
    public String getUserList(Model model){
        List<User> list = userService.getUserList();
        model.addAttribute("page",list);
        return "user/list";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }
    @RequestMapping("/add")
    public String add(User user){
        userService.creatUser(user);//添加表单的元素
        return "redirect:/managerUser/getUserList";
    }
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id")Long id,Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }
    @RequestMapping("/edit")
    public String edit(User user){
        userService.updateUser(user.getId(),user);
        return "redirect:/managerUser/getUserList";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/managerUser/getUserList";
    }
}
