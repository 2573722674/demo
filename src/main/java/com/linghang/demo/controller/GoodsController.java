package com.linghang.demo.controller;

import com.linghang.demo.DTO.GoodsDTO;
import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.Goods;
import com.linghang.demo.service.GoodsSevice;
import com.linghang.demo.util.VOToJson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsSevice goodsSevice;

    @Autowired
    public GoodsController(GoodsSevice goodsSevice) {
        this.goodsSevice = goodsSevice;
    }

    @PostMapping("/post")
    public String post(@RequestParam("user_name") String userName,
                       @RequestParam("goods_name") String goodsName,
                       @RequestParam("goods_price") BigDecimal goodsPrice,
                       @RequestParam("goods_detail") String goodsDetaill,
                       @RequestParam("goods_status") Integer goodsStatus,
                       @RequestParam("goods_image") MultipartFile file) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setData(null);
        Goods goods = new Goods();
        GoodsDTO goodsDTO = new GoodsDTO(0, userName, goodsName, goodsPrice, goodsDetaill, "http://", goodsStatus);
        BeanUtils.copyProperties(goodsDTO, goods);
        goods = goodsSevice.postGoods(goods);
        if (goods == null) {
            resultVO.setCode(1);
            resultVO.setMessage("发布商品失败");
        } else {
            resultVO.setCode(0);
            resultVO.setMessage("发布商品成功");
        }
        //TODO 文件处理
        return VOToJson.resultVOToJson(resultVO);
    }

    @GetMapping("/selling")
    public String selling() {
        List<Goods> goodsList = goodsSevice.findOnSell();
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMessage("查询成功");
        resultVO.setData(goodsList);
        return VOToJson.resultVOToJson(resultVO);
    }

    @PostMapping("/update")
    public String update(@RequestParam("goods_id") int goodsId) {
        return null;
    }

}
