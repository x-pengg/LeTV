import com.google.common.collect.Lists;
import com.maybe.live.kit.Mail;
import com.maybe.live.kit.MailKit;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * @author: Tate
 * @date: 2016/5/20 10:02
 */
public class SendEmailTest {


    @Test
    public void testName() throws Exception {

        MailKit mailKit = new MailKit();
        Mail mail = new Mail();

        mail.setHost("smtp.qq.com");
        mail.setSmtpPort(587);
        mail.setSender("i@ridog.me");
        mail.setReceiver("710843409@qq.com");
        mail.setUsername("i@ridog.me");
        mail.setPassword("");
        mail.setSubject("激活你的账号");
        mail.setMessage("激活你的账号：http://maybelive.com/act/S4fd54fjhf5g4h5j4f65werEff45");
        mailKit.send(mail);
    }


    @Test
    public void testFormat() throws Exception {
        String fileName = "E:/Github/MBLive/target/classes/mail/reset_password.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                sb.append("\n").append(line);
            }
            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReadFile() throws Exception {
        URL resource = this.getClass().getClassLoader().getResource("mail/reset_password.txt");
        System.out.println(resource.toURI());
        String content = new String(Files.readAllBytes(Paths.get(resource.toURI())));
        System.out.println(MessageFormat.format(content,"你好"));
    }


    @Test
    public void testBase64(){
       //NTQ2OTAzOGItMDk4YS00ZGFiLWJiMzctMjVjNzYxZTY0YTZl
        String decodeToken = new String(Base64Utils.decodeFromString("NTQ2OTAzOGItMDk4YS00ZGFiLWJiMzctMjVjNzYxZTY0YTZl"));
        System.out.println(decodeToken);
    }

    @Test
    public void testParam() throws Exception {
        String a = "b", b = a;
        System.out.println(a);
        System.out.println(a instanceof String);

        System.out.println(20000000001L+1);

    }
}
