package net.gslab.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.gslab.dao.BaseDao;
import net.gslab.dao.UserDao;
import net.gslab.entity.User;
import net.gslab.entity.User.Category;
import net.gslab.entity.User.Locked;
import net.gslab.entity.User.UserType;
import net.gslab.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Resource(name = "userDaoImpl")
	private UserDao userDao;
	
	@Override
	public void setBaseDao(BaseDao<User> userDao) {
		// TODO Auto-generated method stub
		super.setBaseDao(userDao);
	}
	
	//这个类继承userService,接口也要继承BaseService接口，否则出问题
	public void register(User user){             //  对特殊字段进行处理，验证能不能通过
		user.setCredit(5);   //积分属性
		//System.out.println("member.value"+UserType.member.value());
		user.setUserType(UserType.member.value());  //设置type数字
		//System.out.println("unlockvalue:"+Locked.unlocked.value());
		user.setLocked(Locked.unlocked.value());
		userDao.save(user);
	}
	
	@Transactional
	public User getUserByName(String username){
		return userDao.getUserByName(username);
	}
	
	public User getUserById(int id){
		return userDao.getUserById(id);
	}
	
	public void lockUser(String username){
		User user = userDao.getUserByName(username);
		user.setLocked(2);
		userDao.update(user);
	}
	
	public void unlockUser(String username){
		User user = userDao.getUserByName(username);
		user.setLocked(1);
		userDao.update(user);
	}
	
	public List<User> queryUserByUserName(String username){
		return userDao.queryUserByUserName(username);
	}
	
	public void loginSuccess(User user){
		user.setCredit(5+user.getCredit());
		userDao.update(user);
	}
	
	public List<User> listUsers(){             //?
		@SuppressWarnings("unchecked")  
		List<User> users = userDao.find("from User");
		System.out.println("listUsers() in the UserService"+users);
		return users;
	}
	
	public List<User> listUsers(String groupName){     //按组查询人员，并返回列表
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

}
