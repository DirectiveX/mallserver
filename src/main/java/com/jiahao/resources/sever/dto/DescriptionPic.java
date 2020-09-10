package com.jiahao.resources.sever.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "description_pic")
@Data
public class DescriptionPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    public DescriptionPic() {
    }

    public DescriptionPic(String image) {
        this.image = image;
    }

    public DescriptionPic(String image, Detail detail) {
        this.image = image;
        this.detail = detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
