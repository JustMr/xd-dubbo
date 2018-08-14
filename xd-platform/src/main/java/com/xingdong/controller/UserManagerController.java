package com.xingdong.controller;

import com.xingdong.entity.XdUser;
import com.xingdong.web.dao.XdUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 * Created by liushuangbo on 2018/8/8.
 */
@Controller
@RequestMapping(value = "/xingdong")
public class UserManagerController {

    @Autowired
    private XdUserMapper xdUserMapper;
    private Logger logger = LoggerFactory.getLogger(UserManagerController.class);

    @RequestMapping(value = "/login")
    public String login(XdUser user, HttpServletRequest request) {
        if (StringUtils.isEmpty(user.getUserName())
                || StringUtils.isEmpty(user.getPwd())) {
            return "redirect:/login.html";
        }

        List<XdUser> users = xdUserMapper.selectByFilter(user);
        if (users != null && users.size() == 1) {
            logger.debug("查到用户");
            request.setAttribute("nickName", users.get(0).getNickName());
            return "success";
        }else {
            return "redirect:/login.html";
        }
    }

}
