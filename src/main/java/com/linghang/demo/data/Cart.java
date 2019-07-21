package com.linghang.demo.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linghang.demo.util.serializer.DateToLongSerializer;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    private String userName;

    private int goodsId;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date addTime;

}
