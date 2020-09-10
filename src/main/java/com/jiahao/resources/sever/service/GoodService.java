package com.jiahao.resources.sever.service;

import com.jiahao.resources.sever.dao.GoodDao;
import com.jiahao.resources.sever.dto.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    @Autowired
    private GoodDao goodDao;

    public Good getGoodById(int id){
        return goodDao.getGoodById(id);
    }

    public List<Good> findTopNByType(String type,int pageNo){
        return goodDao.findTopNByType(type,(pageNo-1) * 10,10);
    }

    public List<Good> saveAll(List<Good> goods){
        return goodDao.saveAll(goods);
    }

    public void updateLink(String link){
        goodDao.updateLink(link);
    }
}
