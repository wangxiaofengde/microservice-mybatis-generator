package org.mybatis.generator.ui.bridge;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.DomainObjectRenamingRule;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.ui.model.DatabaseConfig;
import org.mybatis.generator.ui.model.GeneratorConfig;
import org.mybatis.generator.ui.util.DbUtils;
import org.mybatis.generator.ui.util.FilenameUtils;
import org.mybatis.generator.ui.util.StringUtils;

public class MybatisGeneratorBridge {
  private GeneratorConfig generatorConfig;

  private DatabaseConfig selectedDatabaseConfig;

  private ProgressCallback progressCallback;

  public void setGeneratorConfig(GeneratorConfig generatorConfig) {
    this.generatorConfig = generatorConfig;
  }

  public void setDatabaseConfig(DatabaseConfig databaseConfig) {
    this.selectedDatabaseConfig = databaseConfig;
  }

  public void setProgressCallback(ProgressCallback progressCallback) {
    this.progressCallback = progressCallback;
  }

  public void generate() throws Exception {
    Configuration configuration = new Configuration();
    Context context = new Context(ModelType.FLAT);
    configuration.addContext(context);

    context.setId("mysql");
    context.setTargetRuntime("MyBatis3");
    context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "UTF-8");
    context.addProperty("sharding", String.valueOf(this.generatorConfig.isSharding()));

    TableConfiguration tableConfig = new TableConfiguration(context);
    tableConfig.setTableName(this.generatorConfig.getTableName());
    if (this.generatorConfig.getDeleteTablePre() != null
        && this.generatorConfig.getDeleteTablePre().length() > 0) {
      DomainObjectRenamingRule domainObjectRenamingRule = new DomainObjectRenamingRule();
      domainObjectRenamingRule.setSearchString(this.generatorConfig.getDeleteTablePre());
      domainObjectRenamingRule.setReplaceString("");
      tableConfig.setDomainObjectRenamingRule(domainObjectRenamingRule);
    }
    context.addTableConfiguration(tableConfig);

    CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
    commentConfig.addProperty("suppressAllComments", "true");
    commentConfig.addProperty("dateFormat", "yyyy-MM-dd HH:mm:ss");
    context.setCommentGeneratorConfiguration(commentConfig);

    JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
    jdbcConfig.setDriverClass("com.mysql.jdbc.Driver");
    jdbcConfig.setConnectionURL(DbUtils.getConnectionUrlWithSchema(this.selectedDatabaseConfig));
    jdbcConfig.setUserId(this.selectedDatabaseConfig.getUsername());
    jdbcConfig.setPassword(this.selectedDatabaseConfig.getPassword());
    jdbcConfig.addProperty("remarks", "true");
    jdbcConfig.addProperty("useInformationSchema", "true");
    context.setJdbcConnectionConfiguration(jdbcConfig);

    JavaTypeResolverConfiguration javaTypeConfig = new JavaTypeResolverConfiguration();
    javaTypeConfig.addProperty("forceBigDecimals", "true");
    context.setJavaTypeResolverConfiguration(javaTypeConfig);

    JavaModelGeneratorConfiguration modelConfig = new JavaModelGeneratorConfiguration();
    modelConfig.setTargetPackage("package");
    modelConfig.setTargetProject("project");
    modelConfig.addProperty("exampleEnable", String.valueOf(this.generatorConfig.isBuildExample()));
    modelConfig.addProperty("exampleProject",
        FilenameUtils.normalize(this.generatorConfig.getProjectFolder()
            + (StringUtils.isEmpty(this.generatorConfig.getExampleFolder()) ? ""
                : File.separator + this.generatorConfig.getExampleFolder())));
    modelConfig.addProperty("overrideExample",
        String.valueOf(this.generatorConfig.isOverrideExample()));
    modelConfig.addProperty("recordEnable", String.valueOf(this.generatorConfig.isBuildModel()));
    modelConfig.addProperty("recordProject",
        FilenameUtils.normalize(this.generatorConfig.getProjectFolder()
            + (StringUtils.isEmpty(this.generatorConfig.getModelFolder()) ? ""
                : File.separator + this.generatorConfig.getModelFolder())));
    modelConfig.addProperty("overrideRecord",
        String.valueOf(this.generatorConfig.isOverrideModel()));
    modelConfig.addProperty("enableSubPackages", "false");
    modelConfig.addProperty("trimStrings", "false");
    if (this.generatorConfig.getExamplePackage() != null) {
      modelConfig.addProperty("targetExamplePackage", this.generatorConfig.getExamplePackage());
    }
    if (this.generatorConfig.getModelPackage() != null) {
      modelConfig.addProperty("targetRecordPackage", this.generatorConfig.getModelPackage());
    }
    context.setJavaModelGeneratorConfiguration(modelConfig);

    SqlMapGeneratorConfiguration xmlConfig = new SqlMapGeneratorConfiguration();
    xmlConfig.setTargetPackage(!this.generatorConfig.isBuildXML()
        && StringUtils.isEmpty(this.generatorConfig.getXmlPackage()) ? "package"
            : this.generatorConfig.getXmlPackage());
    xmlConfig.setTargetProject(FilenameUtils.normalize(this.generatorConfig.getProjectFolder()
        + (StringUtils.isEmpty(this.generatorConfig.getXmlFolder()) ? ""
            : File.separator + this.generatorConfig.getXmlFolder())));
    xmlConfig.addProperty("enable", String.valueOf(this.generatorConfig.isBuildXML()));
    xmlConfig.addProperty("enableSubPackages", "false");
    context.setSqlMapGeneratorConfiguration(xmlConfig);

    JavaClientGeneratorConfiguration javaClientGeneratorConfig =
        new JavaClientGeneratorConfiguration();
    javaClientGeneratorConfig.setConfigurationType("XMLMAPPER");
    javaClientGeneratorConfig.setTargetPackage("package");
    javaClientGeneratorConfig.setTargetProject("project");
    if (this.generatorConfig.isSharding()) {
      javaClientGeneratorConfig.addProperty("rootInterface",
          "com.github.xionghuicoder.microservice.common.dao.repository.sharding.CrudShardingRepository");
      javaClientGeneratorConfig.addProperty("serviceRootInterface",
          "com.github.xionghuicoder.microservice.common.service.sharding.CrudShardingService");
      javaClientGeneratorConfig.addProperty("serviceImplRootClass",
          "com.github.xionghuicoder.microservice.common.service.sharding.impl.AbstractCrudShardingService");
    } else {
      javaClientGeneratorConfig.addProperty("rootInterface",
          "com.github.xionghuicoder.microservice.common.dao.repository.CrudRepository");
      javaClientGeneratorConfig.addProperty("serviceRootInterface",
          "com.github.xionghuicoder.microservice.common.service.CrudService");
      javaClientGeneratorConfig.addProperty("serviceImplRootClass",
          "com.github.xionghuicoder.microservice.common.service.impl.AbstractCrudService");
    }
    javaClientGeneratorConfig.addProperty("enableRepository",
        String.valueOf(this.generatorConfig.isBuildMapper()));
    javaClientGeneratorConfig.addProperty("mapperProject",
        FilenameUtils.normalize(this.generatorConfig.getProjectFolder()
            + (StringUtils.isEmpty(this.generatorConfig.getMapperFolder()) ? ""
                : File.separator + this.generatorConfig.getMapperFolder())));
    javaClientGeneratorConfig.addProperty("enableService",
        String.valueOf(this.generatorConfig.isBuildService()));
    javaClientGeneratorConfig.addProperty("serviceProject",
        FilenameUtils.normalize(this.generatorConfig.getProjectFolder()
            + (StringUtils.isEmpty(this.generatorConfig.getServiceFolder()) ? ""
                : File.separator + this.generatorConfig.getServiceFolder())));
    javaClientGeneratorConfig.addProperty("enableServiceImpl",
        String.valueOf(this.generatorConfig.isBuildServiceImpl()));
    javaClientGeneratorConfig.addProperty("serviceImplProject",
        FilenameUtils.normalize(this.generatorConfig.getProjectFolder()
            + (StringUtils.isEmpty(this.generatorConfig.getServiceImplFolder()) ? ""
                : File.separator + this.generatorConfig.getServiceImplFolder())));
    javaClientGeneratorConfig.addProperty("overrideMapper",
        String.valueOf(this.generatorConfig.isOverrideMapper()));
    javaClientGeneratorConfig.addProperty("overrideService",
        String.valueOf(this.generatorConfig.isOverrideService()));
    javaClientGeneratorConfig.addProperty("overrideServiceImpl",
        String.valueOf(this.generatorConfig.isOverrideServiceImpl()));
    if (this.generatorConfig.getMapperPackage() != null) {
      javaClientGeneratorConfig.addProperty("targetRepositoryPackage",
          this.generatorConfig.getMapperPackage());
    }
    if (this.generatorConfig.getServicePackage() != null) {
      javaClientGeneratorConfig.addProperty("targetServicePackage",
          this.generatorConfig.getServicePackage());
    }
    if (this.generatorConfig.getServiceImplPackage() != null) {
      javaClientGeneratorConfig.addProperty("targetServiceImplPackage",
          this.generatorConfig.getServiceImplPackage());
    }
    javaClientGeneratorConfig.addProperty("enableSubPackages", "false");
    context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfig);

    List<String> warnings = new ArrayList<>();
    Set<String> fullyqualifiedTables = new HashSet<>();
    Set<String> contexts = new HashSet<>();
    ShellCallback shellCallback = new DefaultShellCallback(false);
    MyBatisGenerator myBatisGenerator =
        new MyBatisGenerator(configuration, shellCallback, warnings);
    // if overrideXML selected, delete oldXML file and generate a new one
    if (this.generatorConfig.isBuildXML() && this.generatorConfig.isOverrideXML()) {
      String mappingXMLFilePath = this.getMappingXMLFilePath(this.generatorConfig);
      File mappingXMLFile = new File(mappingXMLFilePath);
      if (mappingXMLFile.exists()) {
        mappingXMLFile.delete();
      }
    }
    myBatisGenerator.generate(this.progressCallback, contexts, fullyqualifiedTables);
  }

  private String getMappingXMLFilePath(GeneratorConfig generatorConfig) {
    StringBuilder sb = new StringBuilder();
    sb.append(generatorConfig.getProjectFolder()).append(File.separator);
    if (!StringUtils.isEmpty(generatorConfig.getXmlFolder())) {
      sb.append(generatorConfig.getXmlFolder()).append(File.separator);
    }
    sb.append(generatorConfig.getXmlPackage().replace(".", File.separator)).append(File.separator);
    sb.append(JavaBeansUtil.getCamelCaseString(generatorConfig.getTableName(), true));
    if (this.generatorConfig.isSharding()) {
      sb.append("Sharding");
    }
    sb.append("Mapper.xml");
    return FilenameUtils.normalize(sb.toString());
  }
}
