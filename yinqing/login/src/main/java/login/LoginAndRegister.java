package login;

import login.login.config.Gc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("login.login.dao")
public class LoginAndRegister {
    public static void main(String[] args) {
        SpringApplication.run(LoginAndRegister.class, args);
        Thread t=new Thread(new Gc());
        t.start();
    }

}
