package com.seasontemple.mproject.dao.pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


/**
 * @author Season Temple
 * @program: mproject
 * @description: 测试用POJO
 * @create: 2019/12/21 19:16:28
 */
@Data
public class TestBean {
    private int id;
    private String name;
    private String type;
    private int flag;
    @JsonIgnore
    private String code;

}
