package com.linghang.demo.controller;

import com.linghang.demo.Form.GoodsForm;
import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.config.GlobalConfig;
import com.linghang.demo.data.Cart;
import com.linghang.demo.data.Goods;
import com.linghang.demo.service.CartService;
import com.linghang.demo.service.GoodsSevice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsSevice goodsSevice;

    private final CartService cartService;

    @Autowired
    public GoodsController(GoodsSevice goodsSevice, CartService cartService) {
        this.goodsSevice = goodsSevice;
        this.cartService = cartService;
    }


    @PostMapping("/post")
    @ResponseBody
    public ResultVO<Goods> post(@RequestParam("user_name") String userName,
                                @RequestParam("goods_name") String goodsName,
                                @RequestParam("goods_price") String goodsPrice,
                                @RequestParam("goods_detail") String goodsDetail,
                                @RequestParam(value = "goods_image", required = false) MultipartFile file) {
        String goodsImageUrl = "http://" + GlobalConfig.nginxHost + "image.jpg";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = UUID.randomUUID().toString() + suffix;
            String filePath = "C:\\Users\\yzy\\Desktop\\DEMO\\src\\main\\resources\\static\\";
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                goodsImageUrl = "http://" + GlobalConfig.nginxHost + fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultVO<>(1, "文件上传出错", null);
            }
        }
        Goods goods = new Goods();
        GoodsForm goodsForm = new GoodsForm(0, userName, goodsName, BigDecimal.valueOf(Double.valueOf(goodsPrice)), goodsDetail, goodsImageUrl, 0);
        BeanUtils.copyProperties(goodsForm, goods);
        goods = goodsSevice.postGoods(goods);
        if (goods == null) {
            return new ResultVO<>(1, "发布商品失败", null);
        } else {
            return new ResultVO<>(0, "发布商品成功", null);
        }
    }

    @GetMapping("/selling")
    public ResultVO<Goods> selling() {
        //TODO 用户头像
        List<Goods> goodsList = goodsSevice.findOnSell();
        if (goodsList.isEmpty()) {
            return new ResultVO<>(0, "无在售商品", goodsList);
        } else {
            return new ResultVO<>(0, "查询成功", goodsList);
        }
    }

    @PostMapping("/update")
    public ResultVO<Goods> update(@RequestParam("goods_id") int goodsId,
                         @RequestParam("goods_name") String goodsName,
                         @RequestParam("goods_price") BigDecimal goodsPrice,
                         @RequestParam("goods_detail") String goodsDetail) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setData(null);
        Goods goods = goodsSevice.findById(goodsId);
        if (goods == null) {
            resultVO.setCode(1);
            resultVO.setMessage("商品不存在");
        } else {
            goods.setGoodsName(goodsName);
            goods.setGoodsPrice(goodsPrice);
            goods.setGoodsDetail(goodsDetail);
            goodsSevice.postGoods(goods);
            resultVO.setCode(0);
            resultVO.setMessage("更新成功");
        }
        return resultVO;
    }

    @PostMapping("/delete")
    public ResultVO<Goods> delete(@RequestParam("goods_id") int goodsId,
                         @RequestParam("user_name") String userName) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setData(null);
        Goods goods = goodsSevice.findById(goodsId);
        if (goods == null) {
            resultVO.setCode(1);
            resultVO.setMessage("商品不存在");
        } else if (!goods.getUserName().equals(userName)){
            resultVO.setCode(1);
            resultVO.setMessage("无权操作");
        } else {
            goodsSevice.deleteGoodsById(goodsId);
            resultVO.setCode(0);
            resultVO.setMessage("删除成功");
        }
        return resultVO;
    }

    @GetMapping("/list")
    public ResultVO<Goods> list(@RequestParam("user_name") String userName) {
        //TODO 用户头像
        ResultVO<Goods> resultVO = new ResultVO<>();
        List<Goods> goodsList = goodsSevice.findByUserName(userName);
        resultVO.setCode(0);
        resultVO.setMessage("查询成功");
        resultVO.setData(goodsList);
        return resultVO;
    }

    @PostMapping("/status")
    public ResultVO<Goods> status(@RequestParam("goods_id") int goodsId,
                         @RequestParam("goods_status") int goodsStatus) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setData(null);
        Goods goods = goodsSevice.findById(goodsId);
        if (goods == null) {
            resultVO.setCode(1);
            resultVO.setMessage("商品不存在");
        } else {
            goods.setGoodsStatus(goodsStatus);
            goodsSevice.postGoods(goods);
            resultVO.setCode(0);
            resultVO.setMessage("状态更改成功");
        }
        return resultVO;
    }

    @PostMapping("/buy")
    public ResultVO<Goods> buy(@RequestParam("user_name") String userName,
                      @RequestParam("goods_id") int goodsId) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setData(null);
        Goods goods = goodsSevice.findById(goodsId);
        if (goods == null) {
            resultVO.setCode(1);
            resultVO.setMessage("商品不存在");
        } else {
            goods.setGoodsStatus(1);
            goodsSevice.postGoods(goods);
            cartService.buy(userName, goodsId);
            resultVO.setCode(0);
            resultVO.setMessage("购买成功");
        }
        return resultVO;
    }

    @PostMapping("/cancel")
    public ResultVO<Goods> cancel(@RequestParam("user_name") String userName,
                                  @RequestParam("goods_id") int goodsId) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        resultVO.setData(null);
        Goods goods = goodsSevice.findById(goodsId);
        Cart cart = cartService.findByUserNameAndGoodsId(userName, goodsId);
        if (goods == null) {
            resultVO.setCode(1);
            resultVO.setMessage("商品不存在");
        } else if(cart == null) {
            resultVO.setCode(1);
            resultVO.setMessage("订单不存在");
        }else {
            goods.setGoodsStatus(0);
            goodsSevice.postGoods(goods);
            cartService.deleteById(cart.getCartId());
            resultVO.setCode(0);
            resultVO.setMessage("取消购买成功");
        }
        return resultVO;
    }

    @GetMapping("/bought")
    public ResultVO<Goods> bought(@RequestParam("user_name") String userName) {
        ResultVO<Goods> resultVO = new ResultVO<>();
        List<Goods> goodsList = new ArrayList<>();
        List<Cart> cartList = cartService.findByUserName(userName);
        if (cartList.isEmpty()) {
            resultVO.setCode(0);
            resultVO.setMessage("无购买商品");
            resultVO.setData(null);
        } else {
            resultVO.setCode(0);
            resultVO.setMessage("查询成功");
            for (Cart cart : cartList) {
                goodsList.add(goodsSevice.findById(cart.getGoodsId()));
            }
            resultVO.setData(goodsList);
        }
        return resultVO;
    }

}
