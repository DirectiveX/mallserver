package com.jiahao.resources;

import com.jiahao.resources.sever.dto.Good;
import com.jiahao.resources.sever.service.GoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ResourcesApplicationTests {
    private char [] chars = {'去','我','额','人','他','有','哦','啊','是','的','发','给'};
    private Map<Integer,String> map = new HashMap<Integer,String>(){{put(0,"pop");put(1,"news");put(2,"sell");}};

    @Autowired
    GoodService goodService;
    @Test
    public void save() {
        List<Good> list = new ArrayList<>();
        for(int i = 0;i < 114;i ++){
            Good good = new Good(randomString(),"http://localhost:8092/home/"+ i +".jpg",i + 1,map.get(i%3),"https://www.bilibili.com",i * 10.81);
            list.add(good);
        }
        goodService.saveAll(list);
    }

    private String randomString(){
        int len = (int)(Math.random()* 100)%12;
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < len;i ++){
            builder.append(chars[(int)(Math.random()* 100)%12]);
        }
        return builder.toString();
    }

}
