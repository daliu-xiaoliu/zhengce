package login.login.Controller;


import com.yang.entities.Restful;
import com.yang.entities.User;
import io.lettuce.core.ScriptOutputType;
import login.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;


    /**
     * 用于实现登录功能，同时将用户数据写入redis
     * 用来验证用户的登录状态
     * @param userId
     * @param password
     * @return
     */
    @PostMapping("/userLogin")
    public Restful login(String userId,String password){
        System.out.println(userId+"  "+password);
        if(null !=userId && null !=password){
            if(service.login(userId, password)){
                service.loginAuthentication(userId);
                return new Restful("ok",service.find(userId));
            }
        }
        return new Restful("401","用户名或密码错误","null");
    }

    /**
     *
     * @param userId
     * @param matchCode
     */
    @PostMapping("/updatePassword")
    public Restful forgetPassword(String userId,String matchCode,String password){
        User user=service.find(userId);
        System.out.println(userId+matchCode+password);
        if(user== null){
            return new Restful("404","user was not found","false");
        }
        System.out.println(service.getCode(user.getUserEmail()));
        if(matchCode.equals(service.getCode(user.getUserEmail()))){
            boolean upPass = service.updatePassword(userId, password);
            if(upPass){
                return new Restful("200","修改密码成功",service.find(userId));
            }else {
                return new Restful("500","服务器错误,可能用户不存在",user);
            }
        }
        return new Restful("401","修改密码失败，验证码错误",user);

    }

    @GetMapping("/sendCode")
    public boolean sendCode(String userEmail){
        System.out.println(userEmail);
        if(service.sendCode(userEmail)){
            return true;
        }
        return false;
    }

    @GetMapping("/isLogin")
    public boolean isLogin(String userId){
        return service.isLogin(userId);
    }


    /**
     * 删除用户信息
     * @param userId
     * @param password
     * @return
     */
    @DeleteMapping("/deleteInfo")
    public Restful deleteInfo(String userId,String password){

        if(service.delete(userId,password)){
            return new Restful("删除成功","0");
        }
        return new Restful("密码错误，删除失败",0);
    }

}
