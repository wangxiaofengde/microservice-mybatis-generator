package com.example.test;

import com.example.test.data.ProjectRepository;
import com.example.test.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {
    @Autowired
    private ProjectRepository repository;
    @Test
    public void contextLoads() {

        Project project = repository.selectById(1);
        System.out.println(project);
    }

}
