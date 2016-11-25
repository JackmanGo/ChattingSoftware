package com.bigchat.daoImpl;


import com.bigchat.domain.User;
import com.bigchat.utils.JdbcUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


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
		user.setUserId(user_id);
		user.setUserPassword(password);
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
