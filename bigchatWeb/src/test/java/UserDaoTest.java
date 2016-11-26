import com.bigchat.dao.UserDao;
import com.bigchat.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wang on 16-11-26.
 */
public class UserDaoTest extends AbstractTest{
    @Autowired
    public UserDao userDao;

    @Test
    public void testFindById(){
       User user = userDao.findUserById("1165196813");
       System.out.println(user);

    }
    @Test
    public void testLogin(){
        User user = userDao.loginDao(new User("1165196813","wx950709"));
        System.out.println(user);

    }
}
