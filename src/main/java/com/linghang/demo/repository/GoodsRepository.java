package com.linghang.demo.repository;

import com.linghang.demo.data.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    List<Goods> findByUserName(String userName);

}
