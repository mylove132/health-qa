package com.jd.health.qa.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class BaseEntity implements Serializable {

    /**
     * 创建者
     */
    @ApiModelProperty(value="创建者",name="createBy",example="刘占会")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-12-15 18:03:58",dataType="java.util.Date")
    private Date createTime;

    @ApiModelProperty(value="更新者",name="updateBy",example="lerry")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="更新时间",name="updateTime",example="2018-12-15 18:03:58",dataType="java.util.Date")
    private Date updateTime;

    @ApiModelProperty(value="备注",name="remark")
    private String remark;

    @ApiModelProperty(value="删除标志",name="deleted",example="0=正常,1=删除")
    private String deleted;

    @ApiModelProperty(value="请求参数",name="params")
    private transient Map<String, Object> params;

}
