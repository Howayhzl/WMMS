package com.ncms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ncms.constant.Constants;
import com.ncms.utils.ShiroUtils;
import com.ncms.utils.string.VerifyCodeUtil; 

/**
 * Created by Administrator on 2017/8/3.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/login")
    public String login(){
        return Constants.URL_LOGIN;
    }

    @RequestMapping(value = "/welcome")
    public ModelAndView index(){
		String loginuser_name = ShiroUtils.getUsername();
		ModelAndView mav=new ModelAndView(Constants.URL_HOME);
		mav.addObject("loginuser_name", loginuser_name);
        return mav;
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Constants.URL_LOGIN;
    }
    
    /**
	 * @descript 生成
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/static/imageGen")
	public void AuthImage(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串
		String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		// 删除以前的
		session.removeAttribute("verifyCode");
		session.setAttribute("verifyCode", verifyCode.toLowerCase());
		// 生成图片
		int w = 134, h = 40;
		try {
			VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
