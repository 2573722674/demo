package com.linghang.demo.controller;

import com.linghang.demo.Form.ActivityForm;
import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.Activity;
import com.linghang.demo.data.Participate;
import com.linghang.demo.service.ActivityService;
import com.linghang.demo.service.ParticipateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    private final ParticipateService participateService;

    @Autowired
    public ActivityController(ActivityService activityService, ParticipateService participateService) {
        this.activityService = activityService;
        this.participateService = participateService;
    }

    @GetMapping("/active")
    public ResultVO<Activity> active() {
        List<Activity> activityList = activityService.findByActivityStatus(0);
        if (activityList.isEmpty()) {
            return new ResultVO<>(0, "无结果", null);
        } else {
            return new ResultVO<>(0, "查询成功", activityList);
        }
    }

    @PostMapping("/post")
    public ResultVO<Activity> post(@RequestParam("user_name") String userName,
                                   @RequestParam("activity_name") String activityName,
                                   @RequestParam("start_time") String startTime,
                                   @RequestParam("end_time") String endTime,
                                   @RequestParam("activity_detail") String activityDetail,
                                   @RequestParam("activity_status") int activityStatus) {
        ActivityForm activityForm = new ActivityForm(userName, activityName,  Date.valueOf(startTime), Date.valueOf(endTime), activityDetail, activityStatus);
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityForm, activity);
        activityService.postActivity(activity);
        return new ResultVO<>(0, "发布活动成功", null);
    }

    @GetMapping("/list")
    public ResultVO<Activity> list(@RequestParam("user_name") String userName) {
        List<Activity> activityList = activityService.findByUserName(userName);
        if (activityList.isEmpty()) {
            return new ResultVO<>(0, "无活动", activityList);
        } else {
            return new ResultVO<>(0, "查询成功", activityList);
        }
    }

    @PostMapping("/delete")
    public ResultVO<Activity> delete(@RequestParam("user_name") String userName,
                                     @RequestParam("activity_id") int activityId) {
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            return new ResultVO<>(1, "活动不存在", null);
        } else if (!activity.getUserName().equals(userName)) {
            return new ResultVO<>(1, "无权操作", null);
        } else {
            return new ResultVO<>(0, "删除成功", null);
        }
    }

    @PostMapping("/update")
    public ResultVO<Activity> update(@RequestParam("activity_id") int activityId,
                                     @RequestParam("activity_name") String activityName,
                                     @RequestParam("start_time") String startTime,
                                     @RequestParam("end_time") String endTime,
                                     @RequestParam("activity_detail") String activityDetail) {
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            return new ResultVO<>(1, "活动不存在", null);
        }
        activity.setActivityName(activityName);
        activity.setStartTime(Date.valueOf(startTime));
        activity.setEndTime(Date.valueOf(endTime));
        activity.setActivityDetail(activityDetail);
        activityService.postActivity(activity);
        return new ResultVO<>(0, "更新活动成功", null);
    }

    @PostMapping("/status")
    public ResultVO<Activity> status(@RequestParam("activity_id") int activityId,
                                     @RequestParam("activity_status") int activityStatus) {
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            return new ResultVO<>(1, "活动不存在", null);
        }
        activity.setActivityStatus(activityStatus);
        activityService.postActivity(activity);
        return new ResultVO<>(0, "更新状态成功", null);
    }

    @PostMapping("/join")
    public ResultVO<Activity> join(@RequestParam("user_name") String userName,
                                   @RequestParam("activity_id") int activityId) {
        if (activityService.findById(activityId) == null) {
            return new ResultVO<>(1, "活动不存在", null);
        }
        participateService.joinActivity(userName, activityId);
        return new ResultVO<>(0, "参加活动成功", null);
    }

    @PostMapping("/cancel")
    public ResultVO<Activity> cancel(@RequestParam("user_name") String userName,
                                     @RequestParam("activity_id") int activityId) {
        Participate participate = participateService.findByUserNameAndActivityId(userName, activityId);
        if (participate == null) {
            return new ResultVO<>(1, "记录不存在", null);
        }
        participateService.deleteById(participate.getParticipateId());
        return new ResultVO<>(0, "取消参加活动成功", null);
    }

    @GetMapping("joined")
    public ResultVO<Activity> joined(@RequestParam("user_name") String userName) {
        List<Participate> participateList = participateService.findByUserName(userName);
        if (participateList.isEmpty()) {
            return new ResultVO<>(0, "无已参加的活动", null);
        } else {
            List<Activity> activityList = new ArrayList<>();
            for (Participate participate: participateList) {
                activityList.add(activityService.findById(participate.getActivityId()));
            }
            return new ResultVO<>(0, "查询成功", activityList);
        }
    }

}
