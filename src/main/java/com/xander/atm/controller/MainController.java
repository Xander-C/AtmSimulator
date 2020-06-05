package com.xander.atm.controller;


import com.xander.atm.pojo.Account;
import com.xander.atm.pojo.Statement;
import com.xander.atm.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Controller
public class MainController {
    @Resource(name = "mainService")
    MainService mainService;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request){
        Account account = mainService.getAccountById((String)request.getSession().getAttribute("user"));
        model.addAttribute("name", "欢迎您，" + account.getName());
        model.addAttribute("money", account.getMoney());
        return "index";
    }

    @RequestMapping("/transaction")
    public String transaction(Model model, HttpServletRequest request){
        Account account = mainService.getAccountById((String)request.getSession().getAttribute("user"));
        model.addAttribute("name", "欢迎您，" + account.getName());
        return "transaction";
    }

    @RequestMapping("/history")
    public String history(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        Account account = mainService.getAccountById((String)request.getSession().getAttribute("user"));
        model.addAttribute("name", "欢迎您，" + account.getName());
        List<Statement> list = mainService.getStatements((String) request.getSession().getAttribute("user"));

        for(Statement i : list){
            String accountIdBase64 = i.getAccountId();
            byte[] base64decodedBytes0 = Base64.getDecoder().decode(accountIdBase64);
            String accountId = new String(base64decodedBytes0, StandardCharsets.UTF_8);
            i.setAccountId(accountId);
            String toAccountIdBase64 = i.getToAccount();
            if(toAccountIdBase64 != null) {
                byte[] base64decodedBytes1 = Base64.getDecoder().decode(toAccountIdBase64);
                String ToAccountId = new String(base64decodedBytes1, StandardCharsets.UTF_8);
                i.setAccountId(ToAccountId);
            }
        }
        model.addAttribute("statements", list);
        return "history";
    }

    @RequestMapping("/password")
    public String password(Model model, HttpServletRequest request){
        Account account = mainService.getAccountById((String)request.getSession().getAttribute("user"));
        model.addAttribute("name", "欢迎您，" + account.getName());
        return "password";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
