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

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import org.mybatis.generator.api.DAOMethodNameCalculator;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultDAOMethodNameCalculator;
import org.mybatis.generator.internal.ExtendedDAOMethodNameCalculator;
import org.mybatis.generator.internal.ObjectFactory;

/**
 * Base class for all DAO element generators.
 *
 * @author Jeff Butler
 */
public abstract class AbstractDAOElementGenerator extends AbstractGenerator {
    public abstract void addInterfaceElements(Interface interfaze);

    public abstract void addImplementationElements(TopLevelClass topLevelClass);

    protected AbstractDAOTemplate daoTemplate;
    private DAOMethodNameCalculator daoMethodNameCalculator;
    private JavaVisibility exampleMethodVisibility;

    public AbstractDAOElementGenerator() {
        super();
    }

    public void setDAOTemplate(AbstractDAOTemplate abstractDAOTemplate) {
        this.daoTemplate = abstractDAOTemplate;
    }

    public DAOMethodNameCalculator getDAOMethodNameCalculator() {
        if (this.daoMethodNameCalculator == null) {
            String type = this.context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.DAO_METHOD_NAME_CALCULATOR);
            if (stringHasValue(type)) {
                if ("extended".equalsIgnoreCase(type)) { //$NON-NLS-1$
                    type = ExtendedDAOMethodNameCalculator.class.getName();
                } else if ("default".equalsIgnoreCase(type)) { //$NON-NLS-1$
                    type = DefaultDAOMethodNameCalculator.class.getName();
                }
            } else {
                type = DefaultDAOMethodNameCalculator.class.getName();
            }

            try {
                this.daoMethodNameCalculator =
                        (DAOMethodNameCalculator) ObjectFactory.createInternalObject(type);
            } catch (Exception e) {
                this.daoMethodNameCalculator = new DefaultDAOMethodNameCalculator();
                this.warnings.add(getString("Warning.17", type, e.getMessage())); //$NON-NLS-1$
            }
        }

        return this.daoMethodNameCalculator;
    }

    public JavaVisibility getExampleMethodVisibility() {
        if (this.exampleMethodVisibility == null) {
            String type = this.context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.DAO_EXAMPLE_METHOD_VISIBILITY);
            if (stringHasValue(type)) {
                if ("public".equalsIgnoreCase(type)) { //$NON-NLS-1$
                    this.exampleMethodVisibility = JavaVisibility.PUBLIC;
                } else if ("private".equalsIgnoreCase(type)) { //$NON-NLS-1$
                    this.exampleMethodVisibility = JavaVisibility.PRIVATE;
                } else if ("protected".equalsIgnoreCase(type)) { //$NON-NLS-1$
                    this.exampleMethodVisibility = JavaVisibility.PROTECTED;
                } else if ("default".equalsIgnoreCase(type)) { //$NON-NLS-1$
                    this.exampleMethodVisibility = JavaVisibility.DEFAULT;
                } else {
                    this.exampleMethodVisibility = JavaVisibility.PUBLIC;
                    this.warnings.add(getString("Warning.16", type)); //$NON-NLS-1$
                }
            } else {
                this.exampleMethodVisibility = JavaVisibility.PUBLIC;
            }
        }

        return this.exampleMethodVisibility;
    }
}
