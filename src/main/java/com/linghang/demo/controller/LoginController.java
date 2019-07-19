package com.linghang.demo.controller;

import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.User;
import com.linghang.demo.service.UserService;
import com.linghang.demo.util.VOToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("user_name") String userName,
                        @RequestParam("password") String password) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        User user = userService.findByName(userName);
        if (user == null) {
            resultVO.setCode(1);
            resultVO.setMessage("用户不存在");
        } else if (!user.getPassword().equals(password)) {
            resultVO.setCode(1);
            resultVO.setMessage("密码错误");
        } else {
            resultVO.setCode(0);
            resultVO.setMessage("登录成功");
        }
        return VOToJson.resultVOToJson(resultVO);
    }

}
