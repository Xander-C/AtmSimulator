package com.xander.atm.controller;


import com.xander.atm.pojo.Account;
import com.xander.atm.service.ApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController {
    @Resource(name = "apiService")
    ApiService apiService;


    @RequestMapping("/login/check")
    public String check(String id, String pwd, HttpServletRequest request){
        if (request.getSession().getAttribute(id)!=null&&(int)request.getSession().getAttribute(id)==3) return "3";
        int result = apiService.check(id, pwd);
        if (result == 0) {
            request.getSession().setAttribute(id, 0);
            request.getSession().setAttribute("user", id);
            return "0";
        }
        if (result == 4) return "4";
        if (request.getSession().getAttribute(id)==null)
            request.getSession().setAttribute(id, 0);
        request.getSession().setAttribute(id, (int)request.getSession().getAttribute(id) + 1);
        return (int)request.getSession().getAttribute(id) + "";
    }

    @RequestMapping("/save")
    public String save(double money, HttpServletRequest request){
        apiService.save(money, (String)request.getSession().getAttribute("user"));
        return "0";
    }

    @RequestMapping("/withdraw")
    public String withdraw(double money, HttpServletRequest request){
        return apiService.withdraw(money, (String)request.getSession().getAttribute("user")) + "";
    }

    @RequestMapping("/transfer")
    public String transfer(double money, String id, HttpServletRequest request){
        return apiService.transfer((String) request.getSession().getAttribute("user"), money, id) + "";
    }

    @RequestMapping("/change")
    public String change(String old, String newPwd, HttpServletRequest request){
        return apiService.change((String) request.getSession().getAttribute("user"), old, newPwd) + "";
    }

    @RequestMapping("/test-file")
    public String testFile(@RequestParam("file") MultipartFile file){
        apiService.resolveFile(file);
        return "1";
    }
}
