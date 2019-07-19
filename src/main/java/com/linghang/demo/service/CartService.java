package com.linghang.demo.service;

import com.linghang.demo.data.Cart;

import java.util.List;

public interface CartService {

    Cart buy(String userName, int goodsId);

    Cart deleteById(int cartId);

    List<Cart> findByUserName(String userName);

    List<Cart> findByGoodsId(int goodsId);

}
