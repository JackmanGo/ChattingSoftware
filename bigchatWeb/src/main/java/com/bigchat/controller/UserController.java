package com.bigchat.controller;

import com.bigchat.domain.User;
import com.bigchat.serviceImpl.UserFriendsService;
import com.bigchat.serviceImpl.UserService;
import com.bigchat.vo.HttpErrorCode;
import com.bigchat.vo.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 16-3-22.
 */
@RestController
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "user_Friend_Service")
    private UserFriendsService user_friend_service;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="login")
    public JsonResponse login(HttpSession session, String userId, String userPassword){
        logger.debug("接收数据:");
        return JsonResponse.error(HttpErrorCode.ERROR_LOGIN);
        /*
          将内容或对象作为 HTTP 响应正文返回，使用@ResponseBody将会跳过视图处理部分，
          而是调用适合HttpMessageConverter，将返回值写入输出流。
        User user = new User();
        user.setUserId(user_id);
        user.setUserPassword(user_password);
        boolean isSuccess =  userService.loginService(user);
        if(isSuccess){
            session.setAttribute("user",user);
            return JsonResponse.success();
        }else{
            return JsonResponse.error(HttpErrorCode.ERROR_LOGIN);
        }
        */
    }

    @RequestMapping(value = "register")
    @ResponseBody
    public Map<String, String> register(HttpSession session,String user_id,String user_password){
        Map<String,String> map = new HashMap<String, String>();
        User user = userService.findUserById(user_id);
        if(user==null){
            User new_user = new User();
            new_user.setUserId(user_id);
            new_user.setUserPassword(user_password);
            boolean isSuccess= userService.registerService(new_user);
            if(isSuccess) {
                map.put("result", "注册成功");
                session.setAttribute("user",user);
                //每个用户都有system好友
                user_friend_service.addFriend(user_id,"system");
            }else{
                map.put("result","注册失败");
            }
        }else{
            map.put("result","该id已经存在");
        }
        return  map;
    }

    @RequestMapping(value = "findOtherUser")
    @ResponseBody
    public Map<String,String> findOtherUser(String user_id) {
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
        return map;
    }
}
