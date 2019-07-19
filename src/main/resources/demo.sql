CREATE TABLE `user`
(
    `user_name`      VARCHAR(16) NOT NULL,
    `password`       VARCHAR(16) NOT NULL,
    `user_image_url` VARCHAR(128),
    PRIMARY KEY (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `goods`
(
    `goods_id`        INT           NOT NULL,
    `user_name`       VARCHAR(16)   NOT NULL,
    `goods_name`      VARCHAR(16)   NOT NULL,
    `goods_price`     DECIMAL(8, 2) NOT NULL,
    `goods_detail`    VARCHAR(128),
    `goods_image_url` VARCHAR(128),
    `create_time`     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `goods_status`    TINYINT(3)    NOT NULL,
    PRIMARY KEY (`goods_id`),
    KEY `idx_user_name` (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `activity`
(
    `activity_id`     INT          NOT NULL AUTO_INCREMENT,
    `user_name`       VARCHAR(16)  NOT NULL,
    `activity_name`   VARCHAR(32)  NOT NULL,
    `start_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `end_time`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `activity_detail` VARCHAR(128) NOT NULL,
    `activity_status` TINYINT(3)   NOT NULL,
    PRIMARY KEY (`activity_id`),
    KEY `idx_user_name` (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `participate`
(
    `participate_id`    INT         NOT NULL AUTO_INCREMENT,
    `user_name`         VARCHAR(16) NOT NULL,
    `activity_id`       INT         NOT NULL,
    `registration_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`participate_id`),
    KEY `idx_user_name` (`user_name`),
    KEY `idx_activity_id` (`activity_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `cart`
(
    `cart_id`   INT         NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(16) NOT NULL,
    `goods_id`  INT         NOT NULL,
    `add_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`cart_id`),
    KEY `idx_user_name` (`user_name`),
    KEY `idx_goods_id` (`goods_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8