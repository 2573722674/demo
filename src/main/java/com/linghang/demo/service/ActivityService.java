package com.linghang.demo.service;

import com.linghang.demo.data.Activity;

import java.util.List;

public interface ActivityService {

    Activity postActivity(Activity activity);

    Activity updateActivity(Activity activity);

    Activity deleteActivityById(int activityId);

    Activity findById(int activityId);

    List<Activity> findByUserName(String userName);

    List<Activity> findAll();

    List<Activity> findByActivityStatus(int activityStatus);

    Activity changeActivityStatus(int activityId, int activityStatus);

}
