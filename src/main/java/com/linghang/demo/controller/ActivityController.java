package com.linghang.demo.controller;

import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.Activity;
import com.linghang.demo.service.ActivityService;
import com.linghang.demo.service.ParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        ResultVO<Activity> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        List<Activity> activityList = activityService.findAll();
        if (activityList.isEmpty()) {
            resultVO.setMessage("无活动");
        } else {
            resultVO.setMessage("查询成功");
        }
        return resultVO;
    }




}
