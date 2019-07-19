package com.linghang.demo.service.impl;

import com.linghang.demo.data.Participate;
import com.linghang.demo.repository.ParticipateRepository;
import com.linghang.demo.service.ParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ParticipateServiceImpl implements ParticipateService {

    private final ParticipateRepository repository;

    @Autowired
    public ParticipateServiceImpl(ParticipateRepository repository) {
        Assert.notNull(repository, "repository must not be null");
        this.repository = repository;
    }

    @Override
    public Participate joinActivity(String userName, int activityId) {
        Participate participate = new Participate();
        participate.setUserName(userName);
        participate.setActivityId(activityId);
        return repository.save(participate);
    }

    @Override
    public List<Participate> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public List<Participate> findByActivityId(int activityId) {
        return repository.findByActivityId(activityId);
    }
}
