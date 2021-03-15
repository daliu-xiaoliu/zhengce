package login.login.config;

import com.yang.util.CrossDomainConfig;
import com.yang.util.DruidConfig;
import com.yang.util.EmailSend;
import com.yang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyConfig {

    @Value("${email.fromUser}")
    private String emailFrom;

    @Value("${email.title}")
    private String emailTitle;
    @Value("${email.text}")
    private String emailText;

    @Autowired
    private JavaMailSender sender;

    @Bean
    public RedisUtil getRedisUtil(){
        return new RedisUtil();
    }

    @Bean
    public DruidConfig getDruid(){
        return new DruidConfig();
    }



    @Bean
    public CrossDomainConfig getCross(){
        return new CrossDomainConfig();
    }

}
