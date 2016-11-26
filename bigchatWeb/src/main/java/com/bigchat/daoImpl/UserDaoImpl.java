package com.bigchat.daoImpl;


import com.bigchat.dao.UserDao;
import com.bigchat.domain.User;
import com.bigchat.utils.JdbcUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
	// 登录
	public User loginDao(User user) {
		SqlSession session = JdbcUtils.getConnection();
		User selectuser = session.selectOne("UserMapper.login",user);
		return selectuser;
   	}
	// 注册
	public boolean registerDao(User user) {
		SqlSession session = JdbcUtils.getConnection();
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
	public User findUserById(String userId) {
		SqlSession session = JdbcUtils.getConnection();
        User user = session.selectOne("UserMapper.findById",userId);
		session.commit();
		session.close();
		return user;
	}
	
}
