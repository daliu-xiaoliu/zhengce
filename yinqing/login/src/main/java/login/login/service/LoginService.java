package login.login.service;

import com.yang.entities.User;

import com.yang.util.RedisUtil;
import login.login.config.EmailSend;
import login.login.config.Gc;
import login.login.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EmailSend send;


    public boolean login(String id,String password){
        if(judgment(id,password)){
            return true;
        }
        return false;
    }

    /**
     * 优化查询操作,当用户一倍查找过时可以直接从缓存中获取
     * 可以尝试关闭Mybatis缓存
     * @param id
     * @return
     */
    public User find(String id){
        User user=null;
        for (int i = 0; i < Gc.users.size(); i++) {
            user=Gc.users.get(i);
            if(user.getUserId().equals(id)){
                return user;
            }
        }
        user=userDao.findById(id);
        if(null !=user){
            Gc.users.add(user);
            return user;
        }
        return null;
    }

    private boolean judgment(String id,String password){
        User user=find(id);
        if(null ==user){
            return false;
        }
        String pass=find(id).getPassword();
        if(null !=pass){
            if(pass.equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * 用于进行登录验证
     */
    public void loginAuthentication(String userId){
        try {
            redisUtil.set(userId,"user",60*60);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean isLogin(String userId){
        String lo=(String)redisUtil.get(userId);
        if(null == lo){
            return false;
        }
        if(!lo.equals("user")){
            return false;
        }
        return true;
    }

    /**
     * 获取用户的验证码
     * @return
     */
    public String getCode(String key){
       return (String) redisUtil.get(key);
    }

    /**
     * 修改用户密码
     * @param id
     * @param password
     */
    public boolean updatePassword(String id, String password) {
        Gc.users.remove(find(id));
        int updatePassword = userDao.updatePassword(id, password);
        if(updatePassword==1){
            return true;
        }
        return false;
    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    public boolean sendCode(String email){
        String code=getCode(email);
//        System.out.println(code+"qwe");
//        if(null !=code){
//            return true;
//        }
        code=send.generateCode();
        System.out.println(code);
        try{
            boolean set = redisUtil.set(email, code, 15 * 60);
            System.out.println(redisUtil.get(email));
            send.sendSimpleEmail(code, email);
            System.out.println("zxc");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 注册用户
     * @return
     */
    public boolean register(User user,String code){
        if(matchCode(code,user.getUserEmail())){
            userDao.registerUser(user);
            return true;
        }
            return false;
    }

    public boolean matchCode(String code,String match){
        String mat=getCode(match);
        if(mat.equals(code)){
            return true;
        }
        return false;
    }

    public boolean updateName(String id,String name){
        int updateName = userDao.updateName(id, name);
        if(updateName==1) return true;
        return false;
    }

    @Transactional()
    public boolean delete(String id,String password){
        if(isSelf(id,password)){
            userDao.deleteUser(id);
            return   userDao.deleteInfo(id);
        }
        return false;
    }

    public boolean isSelf(String id,String password){
        User user = userDao.findById(id);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

}
