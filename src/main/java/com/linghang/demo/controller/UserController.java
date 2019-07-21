package com.linghang.demo.controller;

import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.data.User;
import com.linghang.demo.service.UserService;
import com.linghang.demo.util.VOToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultVO<User> login(@RequestParam("user_name") String userName,
                        @RequestParam("password") String password) {
        ResultVO<User> resultVO = new ResultVO<>();
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
        return resultVO;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultVO<User> register(@RequestParam("user_name") String userName,
                           @RequestParam("password") String password) {
        ResultVO<User> resultVO = new ResultVO<>();
        resultVO.setData(null);
        User user = userService.findByName(userName);
        if (user != null) {
            resultVO.setCode(1);
            resultVO.setMessage("用户名已被注册");
        } else {
            user = new User(userName, password, "http://");
            userService.register(user);
            resultVO.setCode(0);
            resultVO.setMessage("注册成功");
        }
        return resultVO;
    }

}
