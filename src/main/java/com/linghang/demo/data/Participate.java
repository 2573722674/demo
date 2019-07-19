package com.linghang.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participateId;

    private String userName;

    private int activityId;

    private Date registrationTime;

}
