package controller;

import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceImpl.UserService;
import serviceImpl.User_Friend_Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 16-3-22.
 */
@Controller
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "user_Friend_Service")
    private User_Friend_Service user_friend_service;
    @RequestMapping(value="login")
    @ResponseBody
    public Map<String, Boolean> login(HttpSession session,String user_id,String user_password){
        /*
          将内容或对象作为 HTTP 响应正文返回，使用@ResponseBody将会跳过视图处理部分，
          而是调用适合HttpMessageConverter，将返回值写入输出流。
         */
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_password(user_password);
        boolean isSuccess =  userService.loginService(user);
        if(isSuccess){
            session.setAttribute("user",user);
        }
        Map<String,Boolean> map  = new HashMap<String, Boolean>();
        map.put("result",isSuccess);
        return map;
    }

    @RequestMapping(value = "register")
    @ResponseBody
    public Map<String, String> register(HttpSession session,String user_id,String user_password){
        Map<String,String> map = new HashMap<String, String>();
        User user = userService.findUserById(user_id);
        if(user==null){
            User new_user = new User();
            new_user.setUser_id(user_id);
            new_user.setUser_password(user_password);
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
            map.put("user_name", friend.getUser_name());
            map.put("user_signature", friend.getSignature());
        }
        return map;
    }
}
