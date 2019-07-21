package com.linghang.demo.service.impl;

import com.linghang.demo.data.Cart;
import com.linghang.demo.repository.CartRepository;
import com.linghang.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    @Autowired
    public CartServiceImpl(CartRepository repository) {
        Assert.notNull(repository, "repository must not be null");
        this.repository = repository;
    }

    @Override
    public Cart buy(String userName, int goodsId) {
        Cart cart = new Cart();
        cart.setUserName(userName);
        cart.setGoodsId(goodsId);
        return repository.save(cart);
    }

    @Override
    public Cart deleteById(int cartId) {
        Cart result = repository.findById(cartId).orElse(null);
        if (result != null) {
            repository.delete(result);
        }
        return result;
    }

    @Override
    public List<Cart> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public List<Cart> findByGoodsId(int goodsId) {
        return repository.findByGoodsId(goodsId);
    }

    @Override
    public Cart findByUserNameAndGoodsId(String userName, int goodsId) {
        return repository.findByUserNameAndGoodsId(userName, goodsId);
    }
}
