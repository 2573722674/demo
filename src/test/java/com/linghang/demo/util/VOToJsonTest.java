package com.linghang.demo.util;

import com.linghang.demo.VO.GoodsVO;
import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.Goods;
import com.linghang.demo.service.GoodsSevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VOToJsonTest {

    @Autowired
    private GoodsSevice goodsSevice;

    @Test
    public void resultVOToJson() {
        ResultVO resultVO = new ResultVO(39, "error", null);
        System.out.println(VOToJson.resultVOToJson(resultVO));
    }

    @Test
    public void goodsVOToJson() {
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setCode(0);
        goodsVO.setMessage("ok");
        List<Goods> goods = goodsSevice.findByUserName("he");
        if (goods.isEmpty()) {
            goods = null;
        }
        goodsVO.setData(goods);
        System.out.println(VOToJson.goodsVOToJson(goodsVO));
    }
}