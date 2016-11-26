package com.bigchat.serviceImpl;

import com.bigchat.daoImpl.UserFriendsDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wang on 16-3-25.
 */
@Service
public class UserFriendsServiceImpl {
    @Autowired
    private UserFriendsDaoImpl userFriendsDao;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserFriendsServiceImpl.class);

    public boolean addFriend(String login_user,String other_id){
         logger.debug("好友关系{}，{}",login_user,other_id);
         boolean isAdd;
         isAdd= userFriendsDao.checkFriendendIsHas(login_user,other_id);
         if(!isAdd){
             isAdd=userFriendsDao.checkFriendendIsHas(other_id,login_user);
             if(!isAdd){
                 return userFriendsDao.addFriend(login_user,other_id);
             }
         }
         return false;
    }
    //返回true表示不能添加，false表示可以添加
    public boolean checkFriend(String login_user,String others_id) {
         return userFriendsDao.checkFriendendIsHas(login_user,others_id);
    }

    public List<String> getAllMyFriends(String user_id) {
        return  userFriendsDao.getAllMyFriends(user_id);
    }
}
