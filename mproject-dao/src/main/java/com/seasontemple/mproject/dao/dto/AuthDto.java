package com.seasontemple.mproject.dao.dto;

import com.seasontemple.mproject.dao.entity.MpMenu;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 权限信息载体类
 */
@Data
@Builder
@ApiModel("权限信息载体类")
public class AuthDto {

    private String roleName;

    private List<RoleAuth> auth;

    private List<MpMenu> menus;

}
