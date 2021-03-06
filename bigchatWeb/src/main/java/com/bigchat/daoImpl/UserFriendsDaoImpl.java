package com.bigchat.daoImpl;

import com.bigchat.dao.UserFriendsDao;
import com.bigchat.domain.UserFriends;
import com.bigchat.utils.JdbcUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 16-3-24.
 */
@Repository
public class UserFriendsDaoImpl implements UserFriendsDao {
    //新增好友
    public boolean addFriend(String login_user_id, String friends_id) {
        SqlSession session = JdbcUtils.getConnection();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_a_id",login_user_id);
        map.put("user_b_id",friends_id);
        int result = session.insert("UserFriendsMapper.createFriends", map);
        session.commit();
        session.close();
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    //查看是否允许添加该好友
    public boolean checkFriendendIsHas(String login_user, String others_id) {
        SqlSession session = JdbcUtils.getConnection();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_a", login_user);
        map.put("user_b", others_id);
        UserFriends user_Friends = session.selectOne("UserFriendsMapper.checkFriend", map);
        session.commit();
        session.close();
        if (user_Friends == null) {
            return false;
        } else {
            return true;
        }
    }

    //查看自己的所有好友
    public List<String> getAllMyFriends(String user_id) {
        SqlSession session = JdbcUtils.getConnection();
        List<String> list1 = session.selectList("UserFriendsMapper.getAllMyFriend_1", user_id);
        List<String> list2 = session.selectList("UserFriendsMapper.getAllMyFriend_2", user_id);
        list1.addAll(list2);
        session.commit();
        session.close();
        return list1;
    }
}
