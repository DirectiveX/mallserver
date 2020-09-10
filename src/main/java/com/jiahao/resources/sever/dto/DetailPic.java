package com.jiahao.resources.sever.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "detail_pic")
@Data
public class DetailPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    public DetailPic() {
    }

    public DetailPic(String image) {
        this.image = image;
    }

    public DetailPic(String image,Detail detail) {
        this.image = image;
        this.detail = detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
