package com.example.test.service.impl;

import com.example.test.criteria.ProjectExample;
import com.example.test.data.ProjectRepository;
import com.example.test.domain.Project;
import com.example.test.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import ltd.pdx.commons.mybatis.service.AbstractCrudService;
import org.springframework.stereotype.Service;

/**
 * 项目表 服务实现
 *
 * @author wangfeng
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
public class ProjectServiceImpl extends AbstractCrudService<ProjectRepository, Project, ProjectExample, Integer> implements ProjectService {
    @Override
    protected ProjectExample getPageExample(String fieldName, String keyword) {
        return null;
    }
}