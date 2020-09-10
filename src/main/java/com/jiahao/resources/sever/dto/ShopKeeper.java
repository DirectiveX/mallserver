package com.jiahao.resources.sever.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "shopkeeper")
@Entity
public class ShopKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="sys-uuid")
    @GenericGenerator(name="sys-uuid",strategy = "uuid")
    private String uuid;

    private String name;
    @Column(name = "total_sales")
    private Integer totalSales;
    @Column(name = "total_goods")
    private Integer totalGoods;
    @Column(name = "head_sculpture")
    private String headSculpture;

    @OneToMany(mappedBy = "shopKeeper",cascade = CascadeType.ALL)
    private List<Detail> details;

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public ShopKeeper(String name, Integer totalSales, Integer totalGoods, String headSculpture) {
        this.name = name;
        this.totalSales = totalSales;
        this.totalGoods = totalGoods;
        this.headSculpture = headSculpture;
    }

    public ShopKeeper() {
    }

}
