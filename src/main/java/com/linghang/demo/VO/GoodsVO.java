package com.linghang.demo.VO;

import com.linghang.demo.data.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVO {

    private int code;

    private String message;

    private List<Goods> data;

}
