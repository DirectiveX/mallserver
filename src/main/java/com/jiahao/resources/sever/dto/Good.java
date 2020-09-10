package com.jiahao.resources.sever.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "home")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private Integer number;
    private String type;
    private String link;
    private Double price;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "good")
    @JsonIgnore
    private Detail detail;

    public Good (){

    }

    public Good (String name,String image,Integer number,String type,String link,Double price){
        this.name = name;
        this.image = image;
        this.number = number;
        this.type = type;
        this.link = link;
        this.price = price;
    }
}
