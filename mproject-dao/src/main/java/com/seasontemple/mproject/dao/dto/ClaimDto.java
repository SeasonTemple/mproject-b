package com.seasontemple.mproject.dao.dto;

import lombok.Data;

/**
 * @author Season Temple
 * @program: mproject
 * @description: TokenClaim
 * @create: 2020/04/10 22:42:53
 */
@Data
public class ClaimDto {

    private String userName;

    private String passWord;

    private Integer roleId;

    private String token;

}
