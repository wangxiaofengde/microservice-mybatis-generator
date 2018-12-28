package org.mybatis.generator.codegen.mybatis3.javamapper;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.CustomizationCommentGenerator;
import org.mybatis.generator.ui.util.TablePrimaryKeyUtils;

public class ServiceImplGenerator extends AbstractJavaClientGenerator {

  public ServiceImplGenerator() {
    super(true);
  }

  @Override
  public List<CompilationUnit> getCompilationUnits() {
    this.progressCallback.startTask(getString("Progress.51", //$NON-NLS-1$
        this.introspectedTable.getFullyQualifiedTable().toString()));
    CommentGenerator commentGenerator = this.context.getCommentGenerator();

    FullyQualifiedJavaType type =
        new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3JavaServiceImplType());
    TopLevelClass topLevelClass = new TopLevelClass(type);
    topLevelClass.setVisibility(JavaVisibility.PUBLIC);
    commentGenerator.addJavaFileComment(topLevelClass);

    // add annotations
    FullyQualifiedJavaType slf4jAnnotation =
        new FullyQualifiedJavaType("lombok.extern.slf4j.Slf4j");
    topLevelClass.addImportedType(slf4jAnnotation);
    topLevelClass.addAnnotation("@Slf4j");
    FullyQualifiedJavaType serviceAnnotation =
        new FullyQualifiedJavaType("org.springframework.stereotype.Service");
    topLevelClass.addImportedType(serviceAnnotation);
    topLevelClass.addAnnotation("@Service");

    // add comment
    if (commentGenerator instanceof CustomizationCommentGenerator) {
      ((CustomizationCommentGenerator) commentGenerator).addServiceImplClassComment(topLevelClass,
          this.introspectedTable);
    }

    String superClass = this.introspectedTable
        .getTableConfigurationProperty(PropertyRegistry.SERVICE_IMPL_ROOT_CLASS);
    if (!stringHasValue(superClass)) {
      superClass = this.context.getJavaClientGeneratorConfiguration()
          .getProperty(PropertyRegistry.SERVICE_IMPL_ROOT_CLASS);
    }

    if (stringHasValue(superClass)) {
      FullyQualifiedJavaType originFqjt = new FullyQualifiedJavaType(superClass);
      topLevelClass.addImportedType(originFqjt);
      FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(superClass);
      FullyQualifiedJavaType myBatis3JavaMapperType =
          new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3JavaMapperType());
      topLevelClass.addImportedType(myBatis3JavaMapperType);
      fqjt.addTypeArgument(myBatis3JavaMapperType);
      FullyQualifiedJavaType baseRecordType =
          new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
      topLevelClass.addImportedType(baseRecordType);
      fqjt.addTypeArgument(baseRecordType);
      FullyQualifiedJavaType exampleType =
          new FullyQualifiedJavaType(this.introspectedTable.getExampleType());
      topLevelClass.addImportedType(exampleType);
      fqjt.addTypeArgument(exampleType);
      fqjt.addTypeArgument(TablePrimaryKeyUtils.getPrimaryKeyType(this.introspectedTable));
      topLevelClass.setSuperClass(fqjt);
    }

    String rootInterface = this.introspectedTable.getMyBatis3JavaServiceType();
    if (stringHasValue(rootInterface)) {
      FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
      topLevelClass.addImportedType(fqjt);
      topLevelClass.addSuperInterface(fqjt);
    }

    List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
    answer.add(topLevelClass);

    return answer;
  }

  protected void addCountByExampleMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateCountByExample()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addDeleteByExampleMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateDeleteByExample()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new DeleteByPrimaryKeyMethodGenerator(false);
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addInsertMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateInsert()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addInsertSelectiveMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateInsertSelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new SelectByExampleWithBLOBsMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByExampleWithoutBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new SelectByExampleWithoutBLOBsMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new SelectByPrimaryKeyMethodGenerator(false);
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByExampleSelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new UpdateByExampleSelectiveMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByExampleWithBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new UpdateByExampleWithBLOBsMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByExampleWithoutBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new UpdateByExampleWithoutBLOBsMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new UpdateByPrimaryKeySelectiveMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
    if (this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
      AbstractJavaMapperMethodGenerator methodGenerator =
          new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
      this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }
  }

  protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator,
      Interface interfaze) {
    methodGenerator.setContext(this.context);
    methodGenerator.setIntrospectedTable(this.introspectedTable);
    methodGenerator.setProgressCallback(this.progressCallback);
    methodGenerator.setWarnings(this.warnings);
    methodGenerator.addInterfaceElements(interfaze);
  }

  @Override
  public AbstractXmlGenerator getMatchedXMLGenerator() {
    return new XMLMapperGenerator();
  }
}
