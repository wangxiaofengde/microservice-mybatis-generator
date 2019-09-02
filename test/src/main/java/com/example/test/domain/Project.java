package com.example.test.domain;

import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目表
 *
 * @author wangfeng
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Project {
    /**
     */
    private Integer id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 操作人
     */
    private Integer operator;

    /**
     * 项目状态 0 下架 1上架
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 提交时间
     */
    private Date commitTime;

    /**
     * 度假屋介绍
     */
    private String expl;

    /**
     * 1 web  2 h5  3 app
     */
    private Integer productType;

    /**
     * 0 正常 1删除
     */
    private Integer status;
}