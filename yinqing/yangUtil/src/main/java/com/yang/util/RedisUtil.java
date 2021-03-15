package com.yang.util;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    public boolean expire(String key,long time){
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存值
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {

        try {

            redisTemplate.opsForValue().set(key, value);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }
    }


    /**
     109
     * 普通缓存放入并设置时间
     110
     * @param key 键
    111
     * @param value 值
    112
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
    113
     * @return true成功 false 失败
    114
     */

    public boolean set(String key, Object value, long time) {

        try {

            if (time > 0) {

                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);

            } else {

                set(key, value);

            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public Object get(String key){

        return redisTemplate.opsForValue().get(key);
    }


    /***
     * 自增 用于登录验证
     * @param key
     * @return
     */
    public boolean incr(String key){
        if(null == get(key)){
            set(key,1,5*60);
            return true;
        }
        int count= (int) get(key);
        if(count==5){
            return false;
        }else{
            redisTemplate.opsForValue().increment(key,1);
            return true;
        }

    }



    public boolean deleteKey(String key){
        try{
            if(null == key){
                return false;
            }
            return redisTemplate.delete(key);
        }catch (Exception  e){
            e.printStackTrace();
        }
        return false;
    }


}
