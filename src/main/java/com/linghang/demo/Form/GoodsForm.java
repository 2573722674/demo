package com.linghang.demo.Form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GoodsForm {

    private int goodsId;

    private String userName;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsDetail;

    private String goodsImageUrl;

    private int goodsStatus;

}
