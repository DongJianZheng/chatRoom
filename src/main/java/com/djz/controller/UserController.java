package com.djz.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.djz.config.bean.UploadConfig;
import com.djz.pojo.User;
import com.djz.service.UserService;
import com.djz.util.FastDFSClient;
import com.djz.util.FileUtil;
import com.djz.util.Result;

 

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UploadConfig  uploadConfig;
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value = {"","/","index"}, method = RequestMethod.GET)
	public String index(){
		return "login.jsp";
	}
	
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping(value = "toRegister", method = RequestMethod.GET)
	public String toRegister(){
		return "register.jsp";
	}
	
	/**
	 * 跳转修改密码页面
	 * @return
	 */
	@RequestMapping(value = "toChange", method = RequestMethod.GET)
	public String toChange(){
		return "change.jsp";
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session ,HttpServletRequest request) {
		User user = userService.login(username, password);
		if(user != null) {
			session.setAttribute("token", user.getId());
			session.setAttribute("name", user.getName());
			return "chatroom.jsp";
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			return "login.jsp";
		}	
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(User user, HttpServletRequest request,@RequestParam(value="fileUpload",required=false)MultipartFile fileUpload) {
		User u = userService.getByUsername(user);
		// 获取文件名
        String fileName = fileUpload.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
		 
        	fileName= UUID.randomUUID().toString()+"."+extName;
			FileUtil.uploadFile(fileUpload.getBytes(), uploadConfig.getPath(),fileName);
			
        } catch (Exception e1) {
			e1.printStackTrace();
		}
        if(u == null) {
			user.setId(System.currentTimeMillis()/100);
			user.setHeadImg(uploadConfig.getHeadImg()+fileName);
			userService.saveOrUpdate(user);
			request.setAttribute("msg", "注册成功");
			return "login.jsp";
		} else {
			request.setAttribute("msg", "用户名已存在");
			return "register.jsp";
		}
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "change", method = RequestMethod.POST)
	public Result change(String username, String oldpass, String newpass) {
		User user = userService.login(username, oldpass);
		if(user == null)
			return new Result(400,"用户名或密码错误",null);
		else {
			user.setPassword(newpass);
			userService.saveOrUpdate(user);
			return new Result(200,"修改密码成功",null);
		}
			
	}
}