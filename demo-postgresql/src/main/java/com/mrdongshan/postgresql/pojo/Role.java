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
@TableName("tb_role")
public class Role {
    private Long id;
    private String name;
    private String roleKey;
    private String description;
    private Timestamp createTime;
    private Timestamp updateTime;
}