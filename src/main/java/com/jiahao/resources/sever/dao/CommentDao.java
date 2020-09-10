package com.jiahao.resources.sever.dao;

import com.jiahao.resources.sever.dto.Comment;
import com.jiahao.resources.sever.dto.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {
}
