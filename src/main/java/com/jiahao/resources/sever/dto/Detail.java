package com.jiahao.resources.sever.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "detail")
@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Column(name = "sales_vol")
    private Integer salesVol;
    @Column(name = "origin_price")
    private Double originPrice;
    @Column(name = "describe_score")
    private Double describeScore;
    @Column(name = "price_score")
    private Double priceScore;
    @Column(name = "quantity_score")
    private Double quantityScore;
    @Column(name = "description")
    private String description;

    @JoinTable(name = "detail_safeguard",joinColumns = {
            @JoinColumn(name = "detail_id",referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name = "safeguard_id",referencedColumnName = "uuid")
    })
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JsonIgnoreProperties("details")
    private List<Safeguard> safeguards;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "home_id")
    private Good good;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "detail")
    @JsonIgnoreProperties("detail")
    private List<DetailPic> detailPics;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "detail")
    @JsonIgnoreProperties("detail")
    private List<DescriptionPic> descriptionPics;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "shop_keeper_uuid")
    @JsonIgnoreProperties("details")
    private ShopKeeper shopKeeper;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "detail")
    @JsonIgnoreProperties("detail")
    private Param param;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "detail")
    @JsonIgnoreProperties("detail")
    private List<Comment> comment;

    public Detail() {
    }

    public Detail(String title, Integer salesVol, Double originPrice, Double describeScore, Double priceScore, Double quantityScore, String description) {
        this.title = title;
        this.salesVol = salesVol;
        this.originPrice = originPrice;
        this.describeScore = describeScore;
        this.priceScore = priceScore;
        this.quantityScore = quantityScore;
        this.description = description;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public void setSafeguards(List<Safeguard> safeguards) {
        this.safeguards = safeguards;
    }

    public void setShopKeeper(ShopKeeper shopKeeper) {
        this.shopKeeper = shopKeeper;
    }
}
