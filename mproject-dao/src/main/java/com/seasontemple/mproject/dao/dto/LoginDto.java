package com.seasontemple.mproject.dao.dto;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 用户登录所需信息载体
 * @create: 2020/04/05 01:41:45
 */
@Data
@ApiModel("loginDto")
@Accessors(chain = true)
public class LoginDto {

    private static Log log = Log.get();

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {UserLoginValidatedGroup.class})
    @Length(min = 2, max = 20, message = "密码应介于4到20个字符，其中1个汉字记为2字符", groups = {UserLoginValidatedGroup.class})
//    @Pattern(regexp = "(/^[u4E00-u9FA5]){2,10}$/|(/^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$/)", message = "用户名格式错误,应由8至20个字母加数字或2到10个汉字组成",groups = {UserLoginValidatedGroup.class})
    private String userName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空", groups = {UserLoginValidatedGroup.class})
    @Length(min = 4, max = 20, message = "密码应介于4到20个字符，其中1个汉字记为2字符", groups = {UserLoginValidatedGroup.class})
    private String passWord;

    @ApiModelProperty("邮箱账号")
    @Email(message = "邮箱格式错误，请重新输入", groups = {UserLoginValidatedGroup.class})
    private String email;

    @ApiModelProperty(value = "权限字段", hidden = true)
    private String permission;

    @ApiModelProperty(value = "角色名", hidden = true)
    private String roleName;

    @ApiModelProperty(value = "加密盐", hidden = true)
    private byte[] salt;

    @ApiModelProperty(value = "令牌", hidden = true)
    private String token;

    @ApiModelProperty(value = "登录时间", hidden = true)
    private Date loginTime;

    @ApiModelProperty(value = "退出时间", hidden = true)
    private Date logoutTime;
}
