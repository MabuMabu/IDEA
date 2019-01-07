package com.redis.com;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Tests {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);
        config.setMaxIdle(1);
        config.setMaxWaitMillis(-1);

        //注意超时时间外网不要设太低
        //JedisPool pool = new JedisPool(config, "localhost", 6379, 60);
        JedisPool pool = new JedisPool(config, "192.168.109.128", 6379, 6000);
        Jedis jd = pool.getResource();
        System.out.println("连接成功！");
        //选择数据库：默认0 配置文件默认16个数据库
        jd.select(0);
        byte[] value = jd.get("person".getBytes());
        Object obj = SerializeUtil.unserialize(value);
        if(obj != null && obj instanceof Person){
            Person per = (Person) obj;
            System.out.println("id:"+per.getId()+",age:"+per.getAge()+",name:"+per.getName());
        }
    }
}
