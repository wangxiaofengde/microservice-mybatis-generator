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
package org.mybatis.generator.api;

import org.mybatis.generator.api.dom.java.CompilationUnit;

/**
 * The Class GeneratedJavaFile.
 *
 * @author Jeff Butler
 */
public class GeneratedJavaFile extends GeneratedFile {

    /** The compilation unit. */
    private CompilationUnit compilationUnit;

    /** The file encoding. */
    private String fileEncoding;

    /** The java formatter. */
    private JavaFormatter javaFormatter;

    /** 是否覆盖文件 */
    private boolean override;

    /**
     * Default constructor.
     *
     * @param compilationUnit the compilation unit
     * @param targetProject the target project
     * @param fileEncoding the file encoding
     * @param javaFormatter the java formatter
     * @param override override
     */
    public GeneratedJavaFile(CompilationUnit compilationUnit, String targetProject,
                             String fileEncoding, JavaFormatter javaFormatter, boolean override) {
        super(targetProject);
        this.compilationUnit = compilationUnit;
        this.fileEncoding = fileEncoding;
        this.javaFormatter = javaFormatter;
        this.override = override;
    }

    /**
     * Instantiates a new generated java file.
     *
     * @param compilationUnit the compilation unit
     * @param targetProject the target project
     * @param javaFormatter the java formatter
     * @param override override
     */
    public GeneratedJavaFile(CompilationUnit compilationUnit, String targetProject,
                             JavaFormatter javaFormatter, boolean override) {
        this(compilationUnit, targetProject, null, javaFormatter, override);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mybatis.generator.api.GeneratedFile#getFormattedContent()
     */
    @Override
    public String getFormattedContent() {
        return this.javaFormatter.getFormattedContent(this.compilationUnit);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mybatis.generator.api.GeneratedFile#getFileName()
     */
    @Override
    public String getFileName() {
        return this.compilationUnit.getType().getShortNameWithoutTypeArguments() + ".java"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mybatis.generator.api.GeneratedFile#getTargetPackage()
     */
    @Override
    public String getTargetPackage() {
        return this.compilationUnit.getType().getPackageName();
    }

    /**
     * This method is required by the Eclipse Java merger. If you are not running in Eclipse, or some
     * other system that implements the Java merge function, you may return null from this method.
     *
     * @return the CompilationUnit associated with this file, or null if the file is not mergeable.
     */
    public CompilationUnit getCompilationUnit() {
        return this.compilationUnit;
    }

    /**
     * A Java file is mergeable if the getCompilationUnit() method returns a valid compilation unit.
     *
     * @return true, if is mergeable
     */
    @Override
    public boolean isMergeable() {
        return true;
    }

    /**
     * Gets the file encoding.
     *
     * @return the file encoding
     */
    public String getFileEncoding() {
        return this.fileEncoding;
    }

    public boolean isOverride() {
        return this.override;
    }
}
