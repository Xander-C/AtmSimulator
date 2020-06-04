package com.xander.atm.controller;


import com.xander.atm.pojo.Account;
import com.xander.atm.service.ApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController {
    @Resource(name = "apiService")
    ApiService apiService;


    @RequestMapping("/check")
    public int check(String id, String pwd, HttpServletRequest request){
        if (request.getSession().getAttribute(id)!=null&&(int)request.getSession().getAttribute(id)==3) return 3;
        int result = apiService.check(id, pwd);
        if (result != 1) return result;
        if (request.getSession().getAttribute(id)==null)
            request.getSession().setAttribute(id, 0);
        request.getSession().setAttribute(id, (int)request.getSession().getAttribute(id) + 1);
        return (int)request.getSession().getAttribute(id);


    }
}
