package com.linghang.demo.service;

import com.linghang.demo.data.Goods;

import java.util.List;

public interface GoodsSevice {

    Goods postGoods(Goods goods);

    Goods updateGoods(Goods goods);

    Goods deleteGoodsById(int goodsId);

    Goods deleteGoods(Goods goods);

    Goods findById(int goodsId);

    List<Goods> findByUserName(String userName);

    List<Goods> findAll();

    List<Goods> findOnSell();

    Goods changeGoodsStatus(int goodsId, int goodsStatus);

}
