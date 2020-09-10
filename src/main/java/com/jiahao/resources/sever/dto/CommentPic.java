package com.jiahao.resources.sever.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment_pic")
@Data
public class CommentPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public CommentPic() {
    }

    public CommentPic(String image,Comment comment) {
        this.image = image;
        this.comment = comment;
    }

    public CommentPic(String image) {
        this.image = image;
    }
}
