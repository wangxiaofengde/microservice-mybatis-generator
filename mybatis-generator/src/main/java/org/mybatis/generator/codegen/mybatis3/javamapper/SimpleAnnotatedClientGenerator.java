/**
 * Copyright 2006-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mybatis.generator.codegen.mybatis3.javamapper;

import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedDeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedInsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectAllMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator;

/**
 * @author Jeff Butler
 *
 */
public class SimpleAnnotatedClientGenerator extends SimpleJavaClientGenerator {

    public SimpleAnnotatedClientGenerator() {
        super(false);
    }

    @Override
    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator =
                    new AnnotatedDeleteByPrimaryKeyMethodGenerator(true);
            this.initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    protected void addInsertMethod(Interface interfaze) {
        if (this.introspectedTable.getRules().generateInsert()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedInsertMethodGenerator(true);
            this.initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator =
                    new AnnotatedSelectByPrimaryKeyMethodGenerator(false, true);
            this.initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    protected void addSelectAllMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectAllMethodGenerator();
        this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    @Override
    protected void addUpdateByPrimaryKeyMethod(Interface interfaze) {
        if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator =
                    new AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator(true);
            this.initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return null;
    }
}
