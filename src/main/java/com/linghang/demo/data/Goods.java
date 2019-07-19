package com.linghang.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsId;

    private String userName;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsDetail;

    private String goodsImageUrl;

    private Date createTime;

    private Date updateTime;

    private int goodsStatus;

}
