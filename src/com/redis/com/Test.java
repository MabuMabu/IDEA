package com.redis.com;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jd = new Jedis("192.168.109.128",6379);
        jd.select(0);
        Person person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setName("青鸟");
        jd.set("person".getBytes(),SerializeUtil.serialize(person));
        jd.expire("person".getBytes(),300);
        jd.close();
        System.out.println("成功!");
        byte[] value = jd.get("person".getBytes());
        Object obj = SerializeUtil.unserialize(value);
        if(obj != null && obj instanceof Person){
            Person per = (Person) obj;
            System.out.println("id:"+per.getId()+",age:"+per.getAge()+",name:"+per.getName());
        }
        System.out.println("1111");
    }
}
