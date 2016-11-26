import com.bigchat.app.MainApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wang on 16-11-26.
 */
@SpringApplicationConfiguration(classes = MainApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTest {
}
