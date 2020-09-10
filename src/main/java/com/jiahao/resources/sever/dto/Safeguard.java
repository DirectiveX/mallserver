package com.jiahao.resources.sever.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "safeguard")
@Entity
public class Safeguard {
    @Id
    @GeneratedValue(generator="sys-uuid")
    @GenericGenerator(name="sys-uuid",strategy = "uuid")
    private String uuid;

    private String name;

    @ManyToMany(mappedBy = "safeguards")
    private List<Detail> details;

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Safeguard() {
    }

    public Safeguard(String name) {
        this.name = name;
    }
}
