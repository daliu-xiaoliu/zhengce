package login.login.Controller;

import com.yang.entities.Restful;
import com.yang.entities.User;
import login.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private LoginService service;

    @PostMapping("/registUser")
    public Restful register(User user,String code){
        if(service.register(user, code)){
            return new Restful("注册成功",service.find(user.getUserId()));
        }
        return new Restful("401","验证码错误",null);
    }

    @GetMapping("/updateName/{id}/{name}")
    public Restful updateName(@PathVariable("id") String id, @PathVariable("name") String name){
        if(id ==null || name ==null){
            return new Restful("400","名字不能为空");
        }
        if(service.updateName(id,name)){
            return new Restful("修改成功",service.find(id));
        }
        return new Restful("400","修改失败");
    }
}
