package com.mrdongshan.postgresql.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_user")
public class User {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Long phoneNumber;
    private String description;

    private Timestamp createTime;
    private Timestamp updateTime;
}