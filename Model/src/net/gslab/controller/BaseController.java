package net.gslab.controller;

import javax.servlet.http.HttpServletRequest;

import net.gslab.entity.User;
import net.gslab.setting.CommonConstant;

public class BaseController {
	protected static final String ERROR_MSG_KEY="errorMsg";
	
	protected User getSessionUser(HttpServletRequest request){//获得session，打开浏览器后，就会产生一个session
		return (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}//CommonConstant，常量，
	
	protected void setSessionUser(HttpServletRequest request,User user){//设置session
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
	}

}
