package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        // 序列化操作：java对象转换成json字符串
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setUsername("猴哥");
        user.setPassword("悟空，快来救我");
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        //反序列化：json字符串准换成java对象
        String s2 = "{\"username\":\"猴哥\",\"password\":\"悟空，快来救我\"}";
        User u2 = mapper.readValue(s2,User.class);
        System.out.println(u2);

        // 反序列化时，json 键必须对应类中的成员变量，找不到就会报错
        String s3 = "{\"username\":\"猴哥\",\"password\":\"悟空，快来救我\"}";
        User u3 = mapper.readValue(s3,User.class);
        System.out.println(u3);
    }
}
