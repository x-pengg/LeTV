import com.maybe.live.Application;
import com.maybe.live.domain.Content;
import com.maybe.live.domain.Type;
import com.maybe.live.service.ILiveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author: Tate
 * @date: 2016/6/1 14:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Rollback(false)
public class LiveTest {

    @Autowired
    ILiveService iLiveService;

    @Test
    public void testCreateLive() throws Exception {
        Content content = new Content();
        content.setTitle("丑丑开");
        content.setSummary("abcdefg");
        content.setTypeId(1);
        content.setCodeRate("5");
        content.setPlayMode(2);
        content.setStatus(false);
        iLiveService.createLive(content);

    }

    @Test
    public void testGetType() throws Exception {

        List<Type> allType = iLiveService.getAllType();
        allType.forEach(type-> System.out.println(type.getValue()));

    }
}
