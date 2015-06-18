package net.gslab.controller;

import java.util.List;

import javax.annotation.Resource;

import net.gslab.entity.User;
import net.gslab.entity.User.ClassType;
import net.gslab.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/view")//   RequestMapping，每个网页的“地址”，叫做统一资源标识符，
public class UserController extends BaseController {
	
	@Resource(name="userServiceImpl")
	private UserService userService;//这两句话相当于 new userServiceImpl。这样写，是因为配置文件里面写好了，这样写就可以直接调用，bean
	
	@RequestMapping(value="/userRegister",method=RequestMethod.POST)  //form表单一般可以用post提交
	public String register(User user){
		System.out.println("in the userController");
		//		System.out.println("u: "+user.getUserName());
		if(user!=null){userService.register(user);}
		return "success";
	}
	
	@RequestMapping(value="/userList")
	public String show(Model model){
		List<User> users=userService.listUsers();
		model.addAttribute("users",users);
		//System.out.println("in the show");
		return "show";  //页面，在前台view文件夹下 show.jsp
	}
	


}
