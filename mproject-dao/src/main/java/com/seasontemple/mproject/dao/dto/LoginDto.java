package com.seasontemple.mproject.dao.dto;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 用户登录所需信息载体
 * @create: 2020/04/05 01:41:45
 */
@Data
@Accessors(chain = true)
public class LoginDto{

    private static Log log = Log.get();

    private String userName;

    private String passWord;

    private String permission;

    private String roleName;

    private byte[] salt;

    private String token;

    private Date loginTime;

    private Date exitTime;
}
