package net.gslab.controller;


import java.util.ArrayList;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gslab.entity.User;
import net.gslab.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class AboutController extends BaseController {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public @ResponseBody List<User> list(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="groupName")String groupName) {
		System.out.println(groupName);
		System.out.println("in the AboutController");
		// String result =
		// "{\"name\":\""+user.getUsername()+"\",\"pwd\":\""+user.getPassword()+"\"}";//user
		// //接到前台传到的数据，并拼接成新的json对象
		// response.setContentType("application/json");//设置response的传输格式为json
		// System.out.println(result);
		// try {
		// PrintWriter out = response.getWriter();
		// out.write(result);//给页面上传输json对象
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// String result = "[\"../images/fep.jpg\"]";

		List<User> userLists = new ArrayList<User>();


		userLists = userService.listUsers(groupName);

		if (userService == null) {
			System.err.println("NUll userService in the About Controller");
		} else {
			for (int i = 0; i < userLists.size(); i++) {
				User user = userLists.get(i);
				//System.out.println(user.getStudentId());
				user.setImgUrl("../images/"+user.getImgUrl());
				//String userName = user.getUserName();
				//String imgUrl = "../images/"+user.getImgUrl();
				//String jsonStr = "{\"userName\":\""+userName+"\",\"imgUrl\":\""+imgUrl+"\"}";
				//String jsonStr=imgUrl;
				//System.out.println(jsonStr);
				//lists.add(user);
				
			}

		}

//		 String result = "../images/fep.jpg";
//		 String result2 = "../images/gzy.jpg";
//		 List<String> lists = new ArrayList<String>();
//		 lists.add(result);
//		 lists.add(result2);
		return userLists;

	}

}
