package com.bigchat.serviceImpl;

import com.bigchat.daoImpl.UserDaoImpl;
import com.bigchat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private UserDaoImpl userDao;
	
	public boolean registerService(User user){
		
		return userDao.registerDao(user);
	}
	public User loginService(User user) {
		// TODO Auto-generated method stub
		return  userDao.loginDao(user);
	}
	public User findUserById(String friendId) {
	   return  userDao.findUserById(friendId);
	}
}
