package com.jiahao.resources.sever.dao;

import com.jiahao.resources.sever.dto.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface GoodDao extends JpaRepository<Good,Integer> {
    public Good getGoodById(int id);

    @Query(value = "select * from home where type = ?1 limit ?2,?3",nativeQuery = true)
    public List<Good> findTopNByType(String type,int start,int len);

    @Transactional
    @Modifying
    @Query(value = "update home set link = ?1",nativeQuery = true)
    public void updateLink(String link);
}
