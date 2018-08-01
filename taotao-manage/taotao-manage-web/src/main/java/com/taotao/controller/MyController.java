package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.MyService;
import com.taotao.service.impl.MyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller("myController")
public class MyController {

    @Autowired
    private MyService myService;

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping("/hello")
    public String sayHello(Model model) throws Exception {
        TbItem item=myService.findItem(536563);
        model.addAttribute("item",item);
        System.out.println(item.getTitle());
        return "hello";
    }



}
