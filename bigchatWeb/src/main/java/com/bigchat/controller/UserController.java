package com.bigchat.controller;

import com.bigchat.domain.User;
import com.bigchat.serviceImpl.UserFriendsServiceImpl;
import com.bigchat.serviceImpl.UserServiceImpl;
import com.bigchat.vo.HttpErrorCode;
import com.bigchat.vo.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 16-3-22.
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserFriendsServiceImpl userFriendService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="login",method = RequestMethod.POST)
    public JsonResponse login(HttpSession session, String userId, String userPassword){
        /*
          将内容或对象作为 HTTP 响应正文返回，使用@ResponseBody将会跳过视图处理部分，
          而是调用适合HttpMessageConverter，将返回值写入输出流。
        */
        User user =  userService.loginService(new User(userId,userPassword));
        boolean isSuccess = user!=null;
        if(isSuccess){
            session.setAttribute("user",user);
            Map<String,String> map = new HashMap<String, String>();
            map.put("result","true");
            return JsonResponse.success(map);
        }else{
            return JsonResponse.error(HttpErrorCode.ERROR_LOGIN);
        }
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public JsonResponse register(HttpSession session,String userId, String userPassword){
        Map<String,String> map = new HashMap<String, String>();
        User user = userService.findUserById(userId);
        if(user==null){
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setUserPassword(userPassword);
            boolean isSuccess= userService.registerService(newUser);
            if(isSuccess) {
                map.put("result", "注册成功");
                session.setAttribute("user",user);
                //每个用户都有system好友
                userFriendService.addFriend(userId,"SystemAdmine");
                return JsonResponse.success(map);
            }else{
                return JsonResponse.error("注册失败");
            }
        }else {
            return JsonResponse.error("注册失败,该id已经存在");
        }
    }

    @RequestMapping(value = "findOtherUser")
    public JsonResponse findOtherUser(String user_id) {
        User friend = userService.findUserById(user_id);
        Map<String, String> map = new HashMap<String, String>();
        if (friend == null) {
            map.put("result", "false");
        } else {
            map.put("result", "true");
            map.put("user_id", user_id);
            map.put("user_name", friend.getUserName());
            map.put("user_signature", friend.getSignature());
        }
        return JsonResponse.success(map);
    }
}
