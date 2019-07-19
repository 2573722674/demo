package com.linghang.demo.service;

import com.linghang.demo.data.Activity;

import java.util.List;

public interface ActivityService {

    Activity postActivity(Activity activity);

    Activity updateActivity(Activity activity);

    Activity deleteActivity(Activity activity);

    Activity findById(int activityId);

    List<Activity> findByUserName(String userName);

    List<Activity> findAll();

    Activity changeActivityStatus(int acivityId, int activityStatus);

}
