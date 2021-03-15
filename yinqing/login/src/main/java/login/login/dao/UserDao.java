package login.login.dao;

import com.yang.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 通过Id查找用户
     * @return 用户的基本信息
     *
     */
    public User findById(String id);

    /**
     * 添加一条用户记录
     * @param user
     */
    public void registerUser(@Param("user") User user);

    /**
     * 修改密码 ，需要提价验证码
     * @param password 新的密码
     * @return
     */
    public int updatePassword(@Param("id") String id, @Param("password") String password);

    /**
     * 允许用户修改用户名
     * @param name
     * @return
     */
    public int updateName(String id,String name);

    /**
     * 返回用户总数
     * @return
     */
    public int total();


    public boolean deleteInfo(String id);


    public boolean deleteUser(String id);

    

}
