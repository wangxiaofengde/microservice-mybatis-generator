package com.example.test.data;

import com.example.test.criteria.ProjectExample;
import com.example.test.domain.Project;
import ltd.pdx.commons.mybatis.data.CrudRepository;

/**
 * 项目表 数据访问类
 *
 * @author wangfeng
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProjectRepository extends CrudRepository<Project, ProjectExample, Integer> {
}