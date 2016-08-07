package serviceImpl;

import daoImpl.User_Friends_Dao;
import domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wang on 16-3-25.
 */
@Service(value = "user_Friend_Service")
public class User_Friend_Service {
    @Resource(name = "user_Friends_Dao")
    private User_Friends_Dao user_Friends_Dao;

    public boolean addFriend(String login_user,String other_id){
      return user_Friends_Dao.addFriend(login_user,other_id);
    }
    //返回true表示不能添加，false表示可以添加
    public boolean checkFriend(String login_user,String others_id) {
         return user_Friends_Dao.checkFriendendIsHas(login_user,others_id);
    }

    public List<String> getAllMyFriends(String user_id) {
        return  user_Friends_Dao.getAllMyFriends(user_id);
    }
}
