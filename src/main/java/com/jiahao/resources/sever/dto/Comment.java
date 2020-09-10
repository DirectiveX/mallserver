package com.jiahao.resources.sever.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    @Column(name = "head_sculpture")
    private String headSculpture;
    @Column(name = "detail")
    private String detailInfo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String color;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "comment")
    @JsonIgnoreProperties("comment")
    private List<CommentPic> commentPics;

    public Comment() {
    }

    public Comment(String username, String headSculpture, String detailInfo, Date date, String color,Detail detail) {
        this.username = username;
        this.headSculpture = headSculpture;
        this.detailInfo = detailInfo;
        this.date = date;
        this.color = color;
        this.detail = detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
