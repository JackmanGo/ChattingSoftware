package com.bigchat.dao;

import java.util.List;

/**
 * Created by wang on 16-11-26.
 */
public interface UserFriendsDao{
    public boolean addFriend(String login_user_id, String friends_id);
    public boolean checkFriendendIsHas(String login_user, String others_id);
    public List<String> getAllMyFriends(String user_id);

}
