package com.linghang.demo.controller;

import com.linghang.demo.VO.ResultVO;
import com.linghang.demo.config.GlobalConfig;
import com.linghang.demo.data.User;
import com.linghang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
                                   @RequestParam("password") String password,
                                   @RequestParam("user_image") MultipartFile file) {
        User user = userService.findByName(userName);
        if (user != null) {
            return new ResultVO<>(1, "用户名已被注册", null);
        }
        String userImageUrl = "http://" + GlobalConfig.nginxHost + "image.jpg";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = UUID.randomUUID().toString() + suffix;
            String filePath = "C:\\Users\\yzy\\Desktop\\DEMO\\src\\main\\resources\\static\\";
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                userImageUrl = "http://" + GlobalConfig.nginxHost + fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultVO<>(1, "文件上传出错", null);
            }
        }
        user = new User(userName, password, userImageUrl);
        userService.register(user);
        return new ResultVO<>(0, "注册成功", null);
    }

    @GetMapping("/image")
    public ResultVO<User> image(@RequestParam("user_name") String userName) {
        User user = userService.findByName(userName);
        if (user == null) {
            return new ResultVO<>(1, "用户不存在", null);
        } else {
            return new ResultVO<>(0, user.getUserImageUrl(), null);
        }
    }

    @PostMapping("/image")
    public ResultVO<User> image(@RequestParam("user_name") String userName,
                                @RequestParam(value = "user_image", required = false) MultipartFile file) {
        User user = userService.findByName(userName);
        if (user == null) {
            return new ResultVO<>(1, "用户不存在", null);
        }
        String userImageUrl = "http://" + GlobalConfig.nginxHost + "image.jpg";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = UUID.randomUUID().toString() + suffix;
            String filePath = "C:\\Users\\yzy\\Desktop\\DEMO\\src\\main\\resources\\static\\";
            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                userImageUrl = "http://" + GlobalConfig.nginxHost + fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultVO<>(1, "文件上传出错", null);
            }
        }
        user.setUserImageUrl(userImageUrl);
        userService.register(user);
        return new ResultVO<>(0, "头像更新成功", null);
    }

}
