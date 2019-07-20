# API接口



## 登录

#### 请求

> **POST	/user/login**
>
> 
>
> **user_name = 用户名**
>
> **password = 密码**

#### 返回

``` json
{
    "code": 1,
    "message": "密码错误"
}
```



---



## 获取用户头像

#### 请求

> **GET	/user/image?user_name=用户名**

#### 返回

```json
{
    "code": 1,
    "message": "头像地址",
}
```



---



## 注册

#### 请求

> **POST	/user/register**
>
> 
>
> **user_name = 用户名**
>
> **password = 密码**
>
> **user_image=图片文件**

#### 返回

``` json
{
    "code": 2,
    "message": "success"
}
```



---



## 查看在售商品

#### 请求

> **GET	/goods/selling**

#### 返回

``` json
{
    "code": 4,
    "message": "success",
    "data": [
        {
            "goodsId": 12345,
            "userName": "yzy",
            "goodsName": "abc",
            "goodsPrice": 30.50,
            "goodsDetail": "abcdefg",
            "goodsImageUrl": "http://xxx.png",
        },
        {
            
        }
    ]
}
```



---



## 发布物品信息

#### 请求

> **POST	/goods/post**
>
> 
>
> **user_name = 商品发布人姓名**
>
> **goods_name = 商品名称**
>
> **goods_price = 商品价格**
>
> **goods_detail = 商品详细信息**
>
> **goods_status = 商品状态(0, 1)上下架**
>
> **goods_image = 商品图片文件**

#### 返回

``` json
{
    "code": 6,
    "message": "success"
}
```



---



## 更新物品信息

#### 请求

> **POST	/goods/update**
>
> 
>
> **goods_id = 商品id**
>
> **goods_name = 商品名称**
>
> **goods_price = 商品价格**
>
> **goods_detail = 商品详细信息**

#### 返回

``` json
{
    "code": 8,
    "message": "success"
}
```



---



## 删除物品信息

#### 请求

> **GET	/goods/delete?goods_id=商品id**

#### 返回

``` json
{
    "code": 10,
    "message": "success"
}
```



---



## 查看自己发布的物品信息

#### 请求

> **GET	/goods/list?user_name=用户名**

#### 返回

```json
{
    "code": 12,
    "message": "success",
    "data": [
        {
            "goodsId": 12345,
            "goodsName": "abc",
            "goodsPrice": 30.50,
            "goodsDetail": "abcdefg",
            "goodsImageUrl": "http://xxx.png",
            "createTime": "2000-08-01 12:45",
            "updateTime": "2000-08-01 12:45",
            "goodsStatus": 2
        },
        {
            
        }
    ]
}
```



---



## 修改物品状态

#### 请求

> **POST	/goods/status**
>
> 
>
> **goods_id = 商品id**
>
> **goods_status = 商品状态**

#### 返回

``` json
{
    "code": 14,
    "message": "success"
}
```



---



## 购买商品

#### 请求

> **POST	/goods/buy**
>
> 
>
> **user_name = 用户名**
>
> **goods_id = 物品id**

#### 返回

``` json
{
    "code": 16,
    "message": "success"
}
```



---



## 取消购买

#### 请求

> **POST	/goods/cancel**
>
> 
>
> **user_name = 用户名**
>
> **goods_id = 物品id**

#### 返回

```json
{
    "code": 18,
    "message": "success"
}
```



---



## 查询已购买商品

#### 请求

> **GET	/goods/bought?user_name=用户名**

#### 返回

```json
{
    "code": 20,
    "message": "success",
    "data": [
        {
            "goodsId": 12345,
            "goodsName": "abc",
            "goodsPrice": 30.50,
            "goodsDetail": "abcdefg",
            "goodsImageUrl": "http://xxx.png",
            "goodsStatus": 2
        },
        {
            
        }
    ]
}
```



---



## 查看未结束活动

#### 请求

> **GET	/activity/active**

#### 返回

```json
{
    "code": 22,
    "message": "success",
    "data": [
        {
            "activityId": 12345,
            "userName": "yzy",
            "activityName": "activity",
            "startTime": "xxxx-xx-xx xx:xx",
            "endTime": "xxxx-xx-xx xx:xx",
            "activityDetail": "abcde"
        },
        {
            
        }
    ]
}
```



---



## 发布活动

#### 请求

> **POST	/activity/post**
>
> 
>
> **user_name = 用户名**
>
> **activity_name = 活动名称**
>
> **start_time = 开始时间**
>
> **end_time = 结束时间**
>
> **activity_detail = 活动详情**
>
> **activity_status = 活动状态**

#### 返回

``` json
{
    "code": 24,
    "message": "success"
}
```



---



## 查看自己发布的活动

#### 请求

> **GET	/activity/list?user_name=用户名**

#### 返回

``` json
{
    "code": 26,
    "message": "success",
    "data": [
        {
            "activityId": 12345,
            "activityName": "activity",
            "startTime": "xxxx-xx-xx xx:xx",
            "endTime": "xxxx-xx-xx xx:xx",
            "activityDetail": "abcde",
            "activityStatus": 2
        },
        {
            
        }
    ]
}
```



---



## 删除活动

#### 请求

> **POST	/activity/delete**
>
> 
>
> **user_name = 用户名**
>
> **ctivity_id = 活动id**

#### 返回

``` json
{
    "code": 28,
    "message": "success"
}
```



---



## 更新活动

#### 请求

> **POST	/activity/update**
>
> 
>
> **activity_id = 活动id**
>
> **activity_name = 活动名称**
>
> **start_time = 开始时间**
>
> **end_time = 结束时间**
>
> **activity_detail = 活动详情**
>
> **activity_status = 活动状态**

#### 返回

``` json
{
    "code": 30,
    "message": "success"
}
```



---



## 更改活动状态

#### 请求

> **POST	/activity/status**
>
> 
>
> **activity_id = 活动id**
>
> **activity_status = 活动状态**

#### 返回

```json
{
    "code": 32,
    "message": "success"
}
```



---



## 参加活动

#### 请求

> **POST	/activity/join**
>
> 
>
> **user_name = 用户名**
>
> **activity_id = 活动id**

#### 返回

``` json
{
    "code": 34,
    "message": "success"
}
```



---



## 取消参加活动

#### 请求

> **POST	/activity/cancel**
>
> 
>
> **user_name = 用户名**
>
> **activity_id = 活动id**

#### 返回

```json
{
    "code": 36,
    "message": "success"
}
```



---



## 查看已参加活动

#### 请求

> **GET	/activity/joined?user_name=用户名**

#### 返回

``` json
{
    "code": 38,
    "message": "success",
    "data": [
        {
            "activityId": 12345,
            "userName": "abc",
            "activityName": "activity",
            "startTime": "xxxx-xx-xx xx:xx",
            "endTime": "xxxx-xx-xx xx:xx",
            "activityDetail": "abcde",
            "activityStatus": 2
        },
        {
            
        }
    ]
}
```

