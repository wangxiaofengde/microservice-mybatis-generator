package com.example.test.service;

import com.example.test.criteria.ProjectExample;
import com.example.test.domain.Project;
import ltd.pdx.commons.mybatis.service.CrudService;

/**
 * 项目表 服务接口
 *
 * @author wangfeng
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProjectService extends CrudService<Project, ProjectExample, Integer> {
}