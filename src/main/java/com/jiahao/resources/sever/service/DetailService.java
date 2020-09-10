package com.jiahao.resources.sever.service;

import com.jiahao.resources.sever.dao.DetailDao;
import com.jiahao.resources.sever.dto.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    @Autowired
    DetailDao detailDao;

    public Detail getDetailsByHomeId(Integer homeId){
        Optional<Detail> byId = detailDao.findById(homeId);
        return byId.isPresent()?byId.get():null;
    }
}
