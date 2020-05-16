package com.seasontemple.mproject.dao.dto;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.seasontemple.mproject.dao.group.UserDetailValidatedGroup;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户详情(UserDetail)载体类
 *
 * @author SeasonTemple
 */
@SuppressWarnings("serial")
@Data
@ApiModel("用户详情载体")
//@Accessors(chain = true)
public class UserDetail {
    private static final long serialVersionUID = 6302674361342249706L;
    /**
     * 用户ID
     */
    @ApiModelProperty("职工号")
    @NotBlank(message = "职工号不能为空", groups = {UserDetailValidatedGroup.class})
    @Pattern(regexp = "user-[A-Za-z0-9]{24}", message = "职工号格式错误", groups = {UserDetailValidatedGroup.class})
    @TableId
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {UserDetailValidatedGroup.class})
    @Length(min = 4, max = 20, message = "用户名应由8到20个数字加字母组成", groups = {UserDetailValidatedGroup.class})
    private String userName;
    /**
     * 加密密码
     */
    @ApiModelProperty("加密密码")
    @NotBlank(message = "密码不能为空", groups = {UserDetailValidatedGroup.class})
//    @Length(min = 8, max = 20, message = "密码应由8到20个数字加字母组成", groups = {UserDetailValidatedGroup.class})
    private String passWord;
    /**
     * 入职、账号创建时间
     */
    @ApiModelProperty("入职、账号创建时间")
    @NotBlank(message = "入职时间不能为空", groups = {UserDetailValidatedGroup.class})
    private Date createTime;
    /**
     * 最后一次登录时间
     */
    @ApiModelProperty("最后一次登录时间")
    @NotBlank(message = "最后一次登录时间不能为空", groups = {UserDetailValidatedGroup.class})
    private Date lastLogin;
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    @NotBlank(message = "真实姓名不能为空", groups = {UserDetailValidatedGroup.class})
    @Length(min = 2, max = 10, message = "真实姓名应介于2至10个汉字", groups = {UserDetailValidatedGroup.class})
    private String realName;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    @NotBlank(message = "真实姓名不能为空", groups = {UserDetailValidatedGroup.class})
    @Pattern(regexp = "1[34578]\\d{9}", message = "手机号格式有误", groups = {UserDetailValidatedGroup.class})
    private String phone;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    @NotBlank(message = "性别不能为空", groups = {UserDetailValidatedGroup.class})
    private Integer sex;
    /**
     * 职位
     */
    @ApiModelProperty("职位")
    @NotBlank(message = "职位不能为空", groups = {UserDetailValidatedGroup.class})
    private String position;
    /**
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    @NotBlank(message = "身份证号码不能为空", groups = {UserDetailValidatedGroup.class})
    @Pattern(regexp = "((1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-5]|8[1-3])\\d{4})((19|20)\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(\\d{3}([0-9xX]))", message = "身份证号码格式错误", groups = {UserDetailValidatedGroup.class})
    private String idNumber;
    /**
     * 所在项目组ID
     */
    @ApiModelProperty("所在项目组ID")
    private Integer groupId;
    /**
     * 所在部门ID
     */
    @ApiModelProperty("所在部门ID")
    private Integer depId;
    /**
     * 直属领导ID
     */
    @ApiModelProperty("直属领导ID")
    private String leader;
    /**
     * 薪水
     */
    @ApiModelProperty("薪水")
    private Double salary;
    /**
     * 当月出勤日期集
     */
    @ApiModelProperty("当月出勤日期集")
    private String attendance;
    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    @NotBlank(message = "年龄不能为空", groups = {UserDetailValidatedGroup.class})
    private Integer age;
    /**
     * 邮箱地址
     */
    @ApiModelProperty("邮箱地址")
    @NotBlank(message = "邮箱地址不能为空", groups = {UserDetailValidatedGroup.class})
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式错误", groups = {UserDetailValidatedGroup.class})
    private String email;
    /**
     * 籍贯
     */
    @ApiModelProperty("籍贯")
    @NotBlank(message = "籍贯不能为空", groups = {UserDetailValidatedGroup.class})
    private String origin;
    /**
     * 路由地址
     */
    @ApiModelProperty("路由地址")
    private String avatarUrl;
    /**
     * 加密盐
     */
    @ApiModelProperty("加密盐")
    private byte[] salt;


}