import com.maybe.live.Application;
import com.maybe.live.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Tate
 * @date: 2016/5/23 11:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Rollback(false)
public class UserRepoTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testName() throws Exception {
        int i = userRepository.updateEnabledByEmail("i@ridog.me");
        System.out.println(i);
    }

    @Test
    @Transactional
    public void testRollBack() throws Exception {
        int i = userRepository.updateEnabledByEmail("i@ridog.me");
        System.out.println(i);
        if (true) {
            throw new RuntimeException("出现了一个错误");
        }
        int j = userRepository.updateEnabledByEmail("i@ridog.me");
        System.out.println(j);
    }

    @Test
    @Transactional
    public void testJpa() throws Exception {
        int i = userRepository.updatePasswordByEmail("asdf", "i@ridog.me");
        System.out.println(i);

    }

}
