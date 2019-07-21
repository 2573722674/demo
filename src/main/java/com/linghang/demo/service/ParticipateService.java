package com.linghang.demo.service;

import com.linghang.demo.data.Participate;

import java.util.List;

public interface ParticipateService {

    void joinActivity(String userName, int activityId);

    Participate deleteById(int registrationId);

    List<Participate> findByUserName(String userName);

    List<Participate> findByActivityId(int activityId);

    Participate findByUserNameAndActivityId(String userName, int activityId);

}
