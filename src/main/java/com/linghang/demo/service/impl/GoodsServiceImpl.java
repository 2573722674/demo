package com.linghang.demo.service.impl;

import com.linghang.demo.data.Goods;
import com.linghang.demo.repository.GoodsRepository;
import com.linghang.demo.service.GoodsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsSevice {

    private final GoodsRepository repository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository repository) {
        Assert.notNull(repository, "repository must not be null");
        this.repository = repository;
    }

    @Override
    public Goods postGoods(Goods goods) {
        return repository.save(goods);
    }

    @Override
    public Goods updateGoods(Goods goods) {
        return repository.save(goods);
    }

    @Override
    public Goods deleteGoodsById(int goodsId) {
        Goods goods = repository.findById(goodsId).orElse(null);
        repository.deleteById(goodsId);
        return goods;
    }

    @Override
    public Goods deleteGoods(Goods goods) {
        repository.delete(goods);
        return goods;
    }

    @Override
    public Goods findById(int goodsId) {
        return repository.findById(goodsId).orElse(null);
    }

    @Override
    public List<Goods> findByName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public List<Goods> findAll() {
        return repository.findAll();
    }

    @Override
    public Goods changeGoodsStatus(int goodsId, int goodsStatus) {
        Goods goods = repository.findById(goodsId).orElse(null);
        goods.setStatus(goodsStatus);;
        repository.save(goods);
        return goods;
    }
}
