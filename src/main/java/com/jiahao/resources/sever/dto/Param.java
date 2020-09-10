package com.jiahao.resources.sever.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "param")
@Data
public class Param {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String size;
    private String length;
    private String season;
    private String address;
    private String material;
    private String type;
    private String style;
    private String fashion;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    public Param() {
    }

    public Param(String size, String length, String season, String address, String material, String type, String style, String fashion,Detail detail) {
        this.size = size;
        this.length = length;
        this.season = season;
        this.address = address;
        this.material = material;
        this.type = type;
        this.style = style;
        this.fashion = fashion;
        this.detail = detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
