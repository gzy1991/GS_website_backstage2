package net.gslab.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.gslab.dao.BaseDao;
import net.gslab.dao.UserDao;
import net.gslab.entity.User;
import net.gslab.entity.User.Category;
import net.gslab.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	
	@Resource(name = "userDaoImpl")
	private UserDao userDao;
	
	
	//这个类继承userService,接口也要继承BaseDao，否则出问题
	public void register(User user){
		
		userDao.save(user);
	}
	
	@Transactional
	public User getUserByName(String username){
		return userDao.getUserByName(username);
	}
	
	public User getUserById(int id){
		return userDao.getUserById(id);
	}
	

	
	public List<User> queryUserByUserName(String username){
		return userDao.queryUserByUserName(username);
	}
	

	
	public List<User> listUsers(){
		@SuppressWarnings("unchecked")
		List<User> users = userDao.find("from User");
		System.out.println("listUsers() in the UserService"+users);
		return users;
	}
	
	public List<User> listUsers(String groupName){
		int groupId;
		if(groupName.equals("fep")){
			groupId=0;
		}else if(groupName.equals("xnn")){
			groupId=1;
		}else if(groupName.equals("vlab")){
			groupId=2;
		}else if(groupName.equals("ome")){
			groupId=3;
		}else{
			groupId=4;
		}
		System.out.println("in the UserServiceImpl"+groupName);
		List<User> users = userDao.find("from User u where u.category="+groupId);
		return users;
	}

	@Override
	public void loginSuccess(User dbUser) {
		// TODO Auto-generated method stub
		
	}
	
	public User search(int id){
		User user = userDao.getUserById(id);
		return user;
	}

}
