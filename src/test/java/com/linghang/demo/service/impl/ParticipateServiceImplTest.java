package com.linghang.demo.service.impl;

import com.linghang.demo.data.Participate;
import com.linghang.demo.service.ParticipateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticipateServiceImplTest {

    @Autowired
    private ParticipateService participateService;

    @Test
    public void joinActivity() {
        participateService.joinActivity("yzy", 12345);
    }

    @Test
    public void findByUseName() {
    }

    @Test
    public void findByActivityId() {
    }
}