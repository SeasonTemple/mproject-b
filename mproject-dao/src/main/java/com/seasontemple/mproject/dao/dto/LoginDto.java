package com.seasontemple.mproject.dao.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 用户登录所需信息载体
 * @create: 2020/04/05 01:41:45
 */
@Data
@Builder
public class LoginDto {

    private String userName;

    private String passWord;

    private String permission;

    private String roleName;

    private String token;

    private Date loginTime;

    private Date exitTime;
}
