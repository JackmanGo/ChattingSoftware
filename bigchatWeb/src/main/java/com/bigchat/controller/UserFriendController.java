package com.bigchat.controller;


import com.bigchat.domain.User;
import com.bigchat.serviceImpl.UserFriendsServiceImpl;
import com.bigchat.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 16-3-25.
 */
@RestController
public class UserFriendController {
    @Autowired
    private UserFriendsServiceImpl user_Friend_Service;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "agreeAddFriend")
    @ResponseBody
    public Map<String, Boolean> addFriend(HttpSession session,String login_id, String others_id){
      //  User login_user = (User) session.getAttribute("user");
        boolean result = user_Friend_Service.addFriend(login_id,others_id);
        Map<String,Boolean> map = new HashMap<String, Boolean>();
        map.put("result",result);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "checkFriend")
    public Map<String,Boolean> checkFriend(HttpSession session, String others_id){
         User login_user = (User) session.getAttribute("user");
         boolean result = user_Friend_Service.checkFriend(login_user.getUserId(),others_id);
         Map<String,Boolean> map = new HashMap<String, Boolean>();
         map.put("result",result);
         return map;
    }
    @ResponseBody
    @RequestMapping(value = "getAllFriends")
    public Map<String,List<String>> getAllMyFriends(HttpSession session,String login_id){
      //User login_user = (User) session.getAttribute("user");
      Map<String,List<String>> map = new HashMap<String, List<String>>();
      List<String> list = user_Friend_Service.getAllMyFriends(login_id);
      map.put("info",list);
      return map;
    }

}
