package com.linghang.demo.repository;

import com.linghang.demo.data.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByUserName(String userName);

    List<Activity> findByActivityStatus(int activityStatus);

}
