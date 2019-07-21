package com.linghang.demo.repository;

import com.linghang.demo.data.Participate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, Integer> {

    List<Participate> findByUserName(String userName);

    List<Participate> findByActivityId(int activityId);

    Participate findByUserNameAndActivityId(String userName, int activityId);

}
