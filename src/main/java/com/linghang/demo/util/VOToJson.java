package com.linghang.demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linghang.demo.VO.ResultVO;

public class VOToJson {

    public static String resultVOToJson(ResultVO resultVO) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(resultVO);
    }

}
