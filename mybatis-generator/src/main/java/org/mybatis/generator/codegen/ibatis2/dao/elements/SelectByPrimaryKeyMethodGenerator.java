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
package org.mybatis.generator.codegen.ibatis2.dao.elements;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getSetterMethodName;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * Generates the Select By Primary Key method.
 *
 * @author Jeff Butler
 *
 */
public class SelectByPrimaryKeyMethodGenerator extends AbstractDAOElementGenerator {

    private boolean generateForJava5;

    public SelectByPrimaryKeyMethodGenerator(boolean generateForJava5) {
        super();
        this.generateForJava5 = generateForJava5;
    }

    @Override
    public void addImplementationElements(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);
        if (generateForJava5) {
            method.addAnnotation("@Override"); //$NON-NLS-1$
        }

        // generate the implementation method
        StringBuilder sb = new StringBuilder();

        if (!introspectedTable.getRules().generatePrimaryKeyClass()) {
            // no primary key class, but primary key is enabled. Primary
            // key columns must be in the base class.
            FullyQualifiedJavaType keyType =
                    new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
            topLevelClass.addImportedType(keyType);

            sb.setLength(0);
            sb.append(keyType.getShortName());
            sb.append(" _key = new "); //$NON-NLS-1$
            sb.append(keyType.getShortName());
            sb.append("();"); //$NON-NLS-1$
            method.addBodyLine(sb.toString());

            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
                sb.setLength(0);
                sb.append("_key."); //$NON-NLS-1$
                sb.append(getSetterMethodName(introspectedColumn.getJavaProperty()));
                sb.append('(');
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(");"); //$NON-NLS-1$
                method.addBodyLine(sb.toString());
            }
        }

        FullyQualifiedJavaType returnType = method.getReturnType();

        sb.setLength(0);
        sb.append(returnType.getShortName());
        sb.append(" record = ("); //$NON-NLS-1$
        sb.append(returnType.getShortName());
        sb.append(") "); //$NON-NLS-1$
        sb.append(daoTemplate.getQueryForObjectMethod(introspectedTable.getIbatis2SqlMapNamespace(),
                introspectedTable.getSelectByPrimaryKeyStatementId(), "_key")); //$NON-NLS-1$
        method.addBodyLine(sb.toString());
        method.addBodyLine("return record;"); //$NON-NLS-1$

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = getMethodShell(importedTypes);

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private Method getMethodShell(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType returnType = introspectedTable.getRules().calculateAllFieldsClass();
        method.setReturnType(returnType);
        importedTypes.add(returnType);

        method.setName(getDAOMethodNameCalculator().getSelectByPrimaryKeyMethodName(introspectedTable));

        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            FullyQualifiedJavaType type =
                    new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
            importedTypes.add(type);
            method.addParameter(new Parameter(type, "_key")); //$NON-NLS-1$
        } else {
            for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(type);
                method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty()));
            }
        }

        for (FullyQualifiedJavaType fqjt : daoTemplate.getCheckedExceptions()) {
            method.addException(fqjt);
            importedTypes.add(fqjt);
        }

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        return method;
    }
}
