package com.linghang.demo.Form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ActivityForm {

    private String userName;

    private String activityName;

    private Date startTime;

    private Date endTime;

    private String activityDetail;

    private int activityStatus;
}
