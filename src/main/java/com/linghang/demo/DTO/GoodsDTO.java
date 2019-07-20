package com.linghang.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GoodsDTO {

    private int goodsId;

    private String userName;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsDetail;

    private String goodsImageUrl;

    private int goodsStatus;

}
