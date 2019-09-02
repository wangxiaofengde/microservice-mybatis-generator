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
package org.mybatis.generator.codegen;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Context;

public abstract class AbstractGenerator {
    protected Context context;
    protected IntrospectedTable introspectedTable;
    protected List<String> warnings;
    protected ProgressCallback progressCallback;
    protected boolean override;
    protected String project;

    public AbstractGenerator() {
        super();
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IntrospectedTable getIntrospectedTable() {
        return this.introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }

    public List<String> getWarnings() {
        return this.warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public ProgressCallback getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isOverride() {
        return this.override;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
