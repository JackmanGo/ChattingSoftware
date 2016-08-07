package serviceImpl;

import daoImpl.UserDao;
import domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserService {

	@Resource(name ="userDao")
	private UserDao userDao;
	
	public boolean registerService(User user){
		
		return userDao.registerDao(user.getUser_id(), user.getUser_password());
	}
	public boolean loginService(User user) {
		// TODO Auto-generated method stub
		return  userDao.loginDao(user);
	}
	public User findUserById(String friend_id) {
	   return  	 userDao.findUserById(friend_id);
	}
}
