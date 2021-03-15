package login.login.config;

import com.yang.entities.User;

import java.util.ArrayList;

public class Gc implements Runnable{

    public static ArrayList<User> users=new ArrayList();

    @Override
    public void run() {
        while (true){
            if(users.size()>10){
               ArrayList<User> list=new ArrayList<>();
                for (int i = 3; i < users.size(); i++) {
                    list.add(users.get(i));
                }
            }
        }
    }
}
