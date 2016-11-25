package com.bigchat.serviceImpl;

import com.bigchat.daoImpl.UserDao;
import com.bigchat.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserService {

	@Resource(name ="userDao")
	private UserDao userDao;
	
	public boolean registerService(User user){
		
		return userDao.registerDao(user.getUserId(), user.getUserPassword());
	}
	public boolean loginService(User user) {
		// TODO Auto-generated method stub
		return  userDao.loginDao(user);
	}
	public User findUserById(String friend_id) {
	   return  	 userDao.findUserById(friend_id);
	}
}
