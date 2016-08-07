package daoImpl;

import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import utils.JdbcUtils;

import javax.annotation.Resources;

@Repository(value ="userDao")
public class UserDao {
	// 登录
	public boolean loginDao(User user) {
		SqlSession session = JdbcUtils.getConnection();
		User selectuser = session.selectOne("UserMapper.login",user);
		return selectuser!=null;
   	}
	// 注册
	public boolean registerDao(String user_id, String password) {
		SqlSession session = JdbcUtils.getConnection();
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_password(password);
		int result = session.insert("UserMapper.save",user);
		session.commit();
		session.close();
		if(result!=0){
			return true;
		}else{
			return false;
		}
	}
	//查找
	public User findUserById(String friend_id) {
		SqlSession session = JdbcUtils.getConnection();
        User user = session.selectOne("UserMapper.findById",friend_id);
		session.commit();
		session.close();
		return user;
	}
	
}
