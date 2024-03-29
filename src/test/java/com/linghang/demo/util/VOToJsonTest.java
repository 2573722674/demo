package com.linghang.demo.util;

import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.User;
import com.linghang.demo.service.GoodsSevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VOToJsonTest {

    @Autowired
    private GoodsSevice goodsSevice;

    @Test
    public void resultVOToJson() {
        ResultVO<User> resultVO = new ResultVO<>(39, "error", null);
        System.out.println(VOToJson.resultVOToJson(resultVO));
    }

}