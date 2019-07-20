package com.linghang.demo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    private int code;

    private String message;

    private List<T> data;

}
