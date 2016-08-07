package daoImpl;

import domain.User;
import domain.User_Friends;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import utils.JdbcUtils;

import javax.annotation.Resources;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 16-3-24.
 */
@Repository(value ="user_Friends_Dao")
public class User_Friends_Dao {
    //新增好友
    public boolean addFriend(String login_user_id,String friends_id) {
        SqlSession session = JdbcUtils.getConnection();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_a_id", login_user_id);
        map.put("user_b_id", friends_id);
        User_Friends user_friends = session.selectOne("User_FriendsMapper.checkFriend", map);
        if (user_friends != null) {
            return false;
        } else {
            int result = session.insert("User_FriendsMapper.createFriends", map);
            session.commit();
            session.close();
            if (result != 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    //查看是否允许添加该好友
    public boolean checkFriendendIsHas(String login_user,String others_id) {
        SqlSession session = JdbcUtils.getConnection();
        Map<String,String> map = new HashMap<String, String>();
        map.put("user_a",login_user);
        map.put("user_b",others_id);
        User_Friends  user_Friends = session.selectOne("User_FriendsMapper.checkFriend",map);
        session.commit();
        session.close();
        if (user_Friends==null){
            return false;
        }else {
            return true;
        }
    }
    //查看自己的所有好友
    public List<String> getAllMyFriends(String user_id) {
        SqlSession session = JdbcUtils.getConnection();
        List<String> list1 =  session.selectList("User_FriendsMapper.getAllMyFriend_1",user_id);
        List<String> list2 =  session.selectList("User_FriendsMapper.getAllMyFriend_2",user_id);
        list1.addAll(list2);
        session.commit();
        session.close();
        return list1;
    }
}
