package com.seasontemple.mproject.utils.custom;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 统一响应数据实体
 * @create: 2020/04/01 12:10:54
 */
@Data
@Builder
@Accessors(chain = true)
public class ResponseBean {

    private static final Log log = LogFactory.get();

    /**
     * 状态码，默认为失败状态
     */
    private Integer code = ResultCode.ERROR;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public void setData(Object data) {
        if (data == null) {
            data = new ObjectMapper();
        }
        this.data = data;
    }

    /**
     * 成功响应
     */
    public ResponseBean success() {
        this.code = ResultCode.SUCCESS;
        if (StringUtils.isBlank(this.msg)) {
            this.msg = "success.";
        }
        return this;
    }

    /**
     * 请求成功，但业务逻辑处理不通过
     */
    public ResponseBean failed() {
        this.code = ResultCode.ERROR;
        return this;
    }
}

