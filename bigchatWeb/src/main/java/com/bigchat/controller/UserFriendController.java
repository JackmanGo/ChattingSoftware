package com.bigchat.controller;


import com.bigchat.domain.User;
import com.bigchat.serviceImpl.UserFriendsServiceImpl;
import com.bigchat.serviceImpl.UserServiceImpl;
import com.bigchat.vo.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private UserFriendsServiceImpl userFriendService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "agreeAddFriend")
    public JsonResponse addFriend(HttpSession session,String loginId, String othersId){
      //  User login_user = (User) session.getAttribute("user");
        boolean result = userFriendService.addFriend(loginId,othersId);
        Map<String,Boolean> map = new HashMap<String, Boolean>();
        map.put("result",result);
        return JsonResponse.success(map);
    }
    @RequestMapping(value = "checkFriend")
    public JsonResponse checkFriend(HttpSession session, String others_id){
         User login_user = (User) session.getAttribute("user");
         boolean result = userFriendService.checkFriend(login_user.getUserId(),others_id);
         Map<String,Boolean> map = new HashMap<String, Boolean>();
         map.put("result",result);
         return JsonResponse.success(map);
    }
    @RequestMapping(value = "getAllFriends")
    public JsonResponse getAllMyFriends(HttpSession session, String loginId){
      //User login_user = (User) session.getAttribute("user");
      Map<String,List<String>> map = new HashMap<String, List<String>>();
      List<String> list = userFriendService.getAllMyFriends(loginId);
      map.put("info",list);
      return JsonResponse.success(map);
    }

}
