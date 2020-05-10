package com.seasontemple.mproject.dao.dto;

import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 账户找回信息载体
 * @create: 2020/05/07 22:02:17
 */
@Data
@ApiModel("passFindDto")
@Accessors(chain = true)
public class PassFindDto {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {UserLoginValidatedGroup.class})
    @Length(min = 2, max = 20, message = "密码应介于4到20个字符，其中1个汉字记为2字符", groups = {UserLoginValidatedGroup.class})
    private String userName;

    @ApiModelProperty("邮箱账号")
    @Email(message = "邮箱格式错误，请重新输入", groups = {UserLoginValidatedGroup.class})
    private String email;

}
