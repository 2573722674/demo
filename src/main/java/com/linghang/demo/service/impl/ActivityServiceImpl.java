package com.linghang.demo.service.impl;

import com.linghang.demo.data.Activity;
import com.linghang.demo.repository.ActivityRepository;
import com.linghang.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository repository) {
        Assert.notNull(repository, "repository must not be null");
        this.repository = repository;
    }

    @Override
    public Activity postActivity(Activity activity) {
        return repository.save(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        return repository.save(activity);
    }

    @Override
    public Activity deleteActivityById(int activityId) {
        Activity result = repository.findById(activityId).orElse(null);
        if (result != null) {
            repository.delete(result);
        }
        return result;
    }

    @Override
    public Activity findById(int activityId) {
        return repository.findById(activityId).orElse(null);
    }

    @Override
    public List<Activity> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public List<Activity> findAll() {
        return repository.findAll();
    }

    @Override
    public Activity changeActivityStatus(int activityId, int activityStatus) {
        Activity activity = repository.findById(activityId).orElse(null);
        if (activity == null) {
            return null;
        }
        activity.setActivityStatus(activityStatus);
        repository.save(activity);
        return activity;
    }
}
