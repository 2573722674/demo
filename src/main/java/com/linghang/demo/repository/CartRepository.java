package com.linghang.demo.repository;

import com.linghang.demo.data.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUserName(String userName);

    List<Cart> findByGoodsId(int goodsId);

}
