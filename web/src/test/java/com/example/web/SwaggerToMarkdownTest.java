package com.example.web;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.nio.file.Paths;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "src/swagger")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SwaggerToMarkdownTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Test() throws Exception{


        //生成markdown的配置
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .build();

        //获取swagger.json文件，输出到outputDir中
        Swagger2MarkupConverter.from(
                new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config).build()
                .toFile(Paths.get("src/doc/api"));
    }
}